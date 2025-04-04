package it.step.service.impl;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class PasswordRecoveryToken {

    String generateToken(String email){

        String token = email + "&" + Instant.now().toString();
        token = Base64.getEncoder().encodeToString(token.getBytes());
        return token;
    }

    String decodeToken(String token){
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            return new String (decodedBytes);
    }

    String getEmailFromToken(String token) {
        List<String> tok = Arrays.asList(token.split("&"));
        return tok.get(0);
    }

    Boolean isTokenValid(String token, String email){
        List<String> tok = Arrays.asList(token.split("&"));

        String emailRes = tok.get(0);
        Instant dateRes = Instant.parse(tok.get(1));

        if (emailRes.equals(email)){
            if(ChronoUnit.SECONDS.between(dateRes, Instant.now()) < 3600){
                return true;
            }
        }
        return false;
    }
}
