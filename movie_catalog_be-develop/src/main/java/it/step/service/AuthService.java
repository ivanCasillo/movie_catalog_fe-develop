package it.step.service;

import it.step.entity.AuthRequest;
import it.step.entity.AuthResponse;
import it.step.entity.User;

import java.sql.Date;

public interface AuthService {
	
	AuthResponse login(AuthRequest req);
	AuthResponse registerUser(User user);
	AuthResponse update(User user);
	
	boolean dateCheck(Date date);

    String recoverPassword(String email);
	String recoverPasswordValidation(String token);
	AuthResponse changePassword(String token, String pwd);
	AuthResponse refreshToken(String token);

	Boolean isTokenValid(String token);
}
