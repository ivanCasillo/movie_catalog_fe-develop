package it.step.controller;

import io.swagger.annotations.Api;
import it.step.entity.AuthRequest;
import it.step.entity.AuthResponse;
import it.step.entity.User;
import it.step.service.AuthService;
import it.step.service.impl.PasswordRecoveryToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Authorization controller")
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;

	@PostMapping(value = "/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
		try {
			AuthResponse res = service.login(req);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			AuthResponse res = AuthResponse.builder()
					.msg(e.getMessage())
					.build();
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) {
		try {
			AuthResponse res = service.registerUser(user);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			AuthResponse res = AuthResponse.builder()
					.msg(e.getMessage())
					.build();
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<AuthResponse> update(@RequestBody User user){
		ResponseEntity<AuthResponse> response;
		try {
			AuthResponse res = service.update(user);
			response = new ResponseEntity<>(res, HttpStatus.OK);
		}catch (Exception e) {
			AuthResponse res = new AuthResponse(null, e.getMessage(), "","");
			response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PostMapping("recover-password")
	public ResponseEntity<AuthResponse> recoverPassword(@RequestBody String email){

		try{
			String res = service.recoverPassword(email);
			return new ResponseEntity<AuthResponse>(AuthResponse.builder().msg(res).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<AuthResponse>(AuthResponse.builder().msg(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("recover-password/{token}")
	public ResponseEntity<AuthResponse> recoverPasswordTok(@PathVariable("token") String token){
		try {
			service.recoverPasswordValidation(token);
			return new ResponseEntity<>(AuthResponse.builder().msg("Allowed").build(), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(AuthResponse.builder().msg("Invalid Token").build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("recover-password/{token}")
	public ResponseEntity<AuthResponse> changePassword(@PathVariable("token")String token,
													   @RequestBody String pwd){
		try{
			AuthResponse res = service.changePassword(token, pwd);
			return new ResponseEntity<AuthResponse>(AuthResponse.builder().user(res.getUser()).msg(res.getMsg()).build(), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<AuthResponse>(AuthResponse.builder().msg(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("refresh/{token}")
	public ResponseEntity<AuthResponse> refreshToken(@PathVariable("token")String token){
		try{
			AuthResponse res = service.refreshToken(token);
			return  new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<AuthResponse>(AuthResponse.builder().msg(e.getMessage()).build(), HttpStatus.NO_CONTENT);
		}
	}
}
