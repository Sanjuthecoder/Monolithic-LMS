package com.dlms.backend.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "username", unique = true, nullable = false)
	String userName;

	@Column(name = "password_hash", nullable = false, length = 512)
	String password; // Mapped to password_hash column

	@Column(name = "email")
	String email;

	@Column(name = "role")
	String role;

	// Removed mobileNo as it's not in the requested schema
}
