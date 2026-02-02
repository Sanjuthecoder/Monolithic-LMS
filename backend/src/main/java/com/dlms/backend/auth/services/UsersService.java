package com.dlms.backend.auth.services;

import java.util.List;
import com.dlms.backend.auth.dto.LoginResponse;
import com.dlms.backend.auth.dto.UsersDTO;
import com.dlms.backend.auth.dto.UserResponseDTO;

public interface UsersService {
	public LoginResponse authenticate(UsersDTO dto);

	public boolean registerUser(UsersDTO dto);

	// Admin user management methods
	public List<UserResponseDTO> getAllUsers();

	public UserResponseDTO getUserById(Long id);

	public UserResponseDTO createUserWithRole(UsersDTO dto, String role);

	public UserResponseDTO updateUserRole(Long id, String newRole);

	public boolean deleteUser(Long id);
}
