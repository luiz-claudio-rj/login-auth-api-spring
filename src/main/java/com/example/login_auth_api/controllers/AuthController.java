package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.dto.LoginRequestDTO;
import com.example.login_auth_api.dto.RegisterRequestDTO;
import com.example.login_auth_api.dto.ResponseDTO;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
	User user = repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
	if (passwordEncoder.matches(body.password(), user.getPassword())) {
	  String token = this.tokenService.generateToken(user);
	  return ResponseEntity.ok(
		  new ResponseDTO(user.getName(), token)
	  );
	}
	return ResponseEntity.badRequest().build();
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
	Optional<User> user = repository.findByEmail(body.email());
	if (user.isEmpty()) {

	  User newUser = new User();
	  newUser.setEmail(body.email());
	  newUser.setName(body.name());
	  newUser.setPassword(passwordEncoder.encode(body.password()));
	  this.repository.save(newUser);

	  String token = this.tokenService.generateToken(newUser);
	  return ResponseEntity.ok(
		  new ResponseDTO(newUser.getName(), token)
	  );

	}
	return ResponseEntity.badRequest().build();
  }

}
