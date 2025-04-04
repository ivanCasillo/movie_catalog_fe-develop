package it.step.configuration;

import it.step.entity.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITELIST = {
            "/v2/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers("/movies/add").hasAnyAuthority(Roles.ADMIN.getCode(), Roles.SUPER_ADMIN.getCode())
                .anyRequest().hasAnyAuthority(Roles.PUBLIC.getCode(), Roles.ADMIN.getCode(), Roles.SUPER_ADMIN.getCode())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
