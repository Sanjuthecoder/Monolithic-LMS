package com.dlms.backend.auth.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dlms.backend.auth.dto.LoginResponse;
import com.dlms.backend.auth.dto.UsersDTO;
import com.dlms.backend.auth.dto.UserResponseDTO;
import com.dlms.backend.auth.entity.Users;
import com.dlms.backend.auth.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtil jwtUtil;

	public UsersServiceImpl() {
		super();
	}

	@Override
	public LoginResponse authenticate(UsersDTO dto) {
		Optional<Users> opUsers = usersRepository.findByUserName(dto.getUserName());
		if (opUsers.isPresent()) {
			Users dbUsers = opUsers.get();
			if (passwordEncoder.matches(dto.getPassword(), dbUsers.getPassword())) {
				String token = jwtUtil.generateToken(dto.getUserName(), dbUsers.getRole());
				return new LoginResponse(token, dbUsers.getRole(), dbUsers.getId(), dbUsers.getUserName(),
						dbUsers.getEmail());
			}
		}
		throw new RuntimeException("Invalid Credentials");
	}

	@Override
	public boolean registerUser(UsersDTO dto) {
		if (usersRepository.existsByUserName(dto.getUserName())) {
			return false;
		}
		Users objUsers = new Users();
		objUsers.setUserName(dto.getUserName());
		objUsers.setEmail(dto.getEmail());
		// Set default role to STUDENT if not provided
		objUsers.setRole(dto.getRole() != null && !dto.getRole().isEmpty()
				? dto.getRole()
				: "STUDENT");
		// Hash password
		objUsers.setPassword(passwordEncoder.encode(dto.getPassword()));
		usersRepository.save(objUsers);
		return true;
	}

	// Admin user management methods
	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<Users> users = usersRepository.findAll();
		return users.stream()
				.map(UserResponseDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		Optional<Users> user = usersRepository.findById(id);
		if (user.isPresent()) {
			return new UserResponseDTO(user.get());
		}
		throw new RuntimeException("User not found with id: " + id);
	}

	@Override
	public UserResponseDTO createUserWithRole(UsersDTO dto, String role) {
		if (usersRepository.existsByUserName(dto.getUserName())) {
			throw new RuntimeException("Username already exists");
		}

		Users newUser = new Users();
		newUser.setUserName(dto.getUserName());
		newUser.setEmail(dto.getEmail());
		newUser.setRole(role);
		newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

		Users savedUser = usersRepository.save(newUser);
		return new UserResponseDTO(savedUser);
	}

	@Override
	public UserResponseDTO updateUserRole(Long id, String newRole) {
		Optional<Users> userOpt = usersRepository.findById(id);
		if (userOpt.isPresent()) {
			Users user = userOpt.get();
			user.setRole(newRole);
			Users updatedUser = usersRepository.save(user);
			return new UserResponseDTO(updatedUser);
		}
		throw new RuntimeException("User not found with id: " + id);
	}

	@Override
	public boolean deleteUser(Long id) {
		if (usersRepository.existsById(id)) {
			usersRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
