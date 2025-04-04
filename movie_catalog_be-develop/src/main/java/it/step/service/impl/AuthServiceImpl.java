package it.step.service.impl;

import it.step.configuration.JwtService;
import it.step.entity.AuthRequest;
import it.step.entity.AuthResponse;
import it.step.entity.User;
import it.step.repository.UserRepository;
import it.step.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final MailService mailService;
	private final PasswordRecoveryToken passwordRecoveryToken;
	
	@Override
	public AuthResponse login(AuthRequest req) throws RuntimeException{

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							req.getEmail(),
							req.getPassword()
					)
			);

		User user = repo.findByEmail(req.getEmail()).orElseThrow();
		if(Objects.nonNull(user.getDisabledAt())) throw new RuntimeException("User Disabled");

		String jwtToken = jwtService.generateToken(user);
		String jwtRefreshToken = jwtService.generateRefreshToken(user);
		return AuthResponse.builder()
				.user(user)
				.msg("User Logged Successfully")
				.token(jwtToken)
				.refreshToken(jwtRefreshToken)
				.build();
	}

	@Override
	public AuthResponse registerUser(User user) throws RuntimeException{
		if(Objects.nonNull(user)) {
			if(dateCheck(user.getBirthdate())) {
				if(repo.findByEmail(user.getEmail()).isEmpty()) {
					if(Objects.isNull(repo.findByCf(user.getCf()))) {
						var userRes = User.builder()
								.name(user.getName())
								.surname(user.getSurname())
								.cf(user.getCf())
								.birthdate(user.getBirthdate())
								.email(user.getEmail())
								.password(passwordEncoder.encode(user.getPassword()))
								.role(user.getRole())
								.passReset(false)
								.build();
						repo.save(userRes);
						var jwtToken = jwtService.generateToken(userRes);
						var jwtRefreshToken = jwtService.generateRefreshToken(userRes);
						return AuthResponse.builder()
								.user(userRes)
								.msg("User Created")
								.token(jwtToken)
								.refreshToken(jwtRefreshToken)
								.build();
					}
					else throw new RuntimeException("CF Already Used");
				}
				else throw new RuntimeException("Email Already Used");
			}
			else throw new RuntimeException("Invalid Age");
		}
		else throw new RuntimeException("Invalid Input");
	}
	
	@Override
	public AuthResponse update(User user) throws RuntimeException{
		if(Objects.isNull(user)) throw new RuntimeException("Invalid Input");
		if(!dateCheck(user.getBirthdate())) throw new RuntimeException("Invalid Age");
		
		User userRes = repo.findByEmail(user.getEmail()).orElseThrow( () -> new UsernameNotFoundException("Username not found!"));
		userRes.setName(user.getName());
		userRes.setSurname(user.getSurname());
		userRes.setBirthdate(user.getBirthdate());
		userRes.setRole(user.getRole());

		return new AuthResponse(userRes, "Updated", "token", "RefreshToken");
	}

	@Override
	public boolean dateCheck(Date date){
		LocalDate now = LocalDate.now();
		int age = Period.between(date.toLocalDate(), now).getYears();

		if(age < 18 || age > 120) return false;
		return true;
	}

	@Override
	public String recoverPassword(String email) {
		if(repo.findByEmail(email).isPresent()){
			try{
				String token = passwordRecoveryToken.generateToken(email);
				mailService.sendEmail(email, token);
				User userRes = repo.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("Username not found!"));;
				userRes.setPassReset(true);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else throw new RuntimeException("User not found");

		return "Recovery email sent";
	}

	@Override
	public String recoverPasswordValidation(String token) {
		token = passwordRecoveryToken.decodeToken(token);
		if(passwordRecoveryToken.isTokenValid(token, passwordRecoveryToken.getEmailFromToken(token))){
			return "Allowed";
		}
		else throw new RuntimeException("Invalid Token");
	}

	@Override
	public AuthResponse changePassword(String token, String pwd) {
		token = passwordRecoveryToken.decodeToken(token);
		if(isTokenValid(token)){
			String email = passwordRecoveryToken.getEmailFromToken(token);
			User userRes = repo.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("Username not found!"));
			if(userRes.getPassReset()){
				userRes.setPassReset(false);
				userRes.setPassword(passwordEncoder.encode(pwd));
				repo.save(userRes);

				return AuthResponse.builder()
						.user(userRes)
						.msg("Success")
						.build();

			}else throw new RuntimeException("Cannot change password of a User who did not ask for it!");

		} else throw new RuntimeException("Invalid Token");
	}

	@Override
	public AuthResponse refreshToken(String token) {
		AuthResponse res = null;
		String mail = jwtService.extractUsername(token);
		User user = repo.findByEmail(mail).orElse(null);
		if(user != null){
			if(jwtService.isTokenValid(token, user)){
				res = new AuthResponse();
				res.setUser(user);
				res.setToken(jwtService.generateToken(user));
				res.setRefreshToken(jwtService.generateRefreshToken(user));
			}
		}

		return res;
	}

	@Override
	public Boolean isTokenValid(String token) {
		return passwordRecoveryToken.isTokenValid(token, passwordRecoveryToken.getEmailFromToken(token));
	}
}
