package com.aidar.security;

import com.aidar.enums.UserStatus;
import com.aidar.model.User;
import com.aidar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paradise on 12.04.16.
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User principal = userRepository.findOneByEmail(email);
        if (principal == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (principal.getStatus() == UserStatus.BANNED) {
            throw new DisabledException("Sorry, but you are banned");
        }
        String password = authentication.getCredentials().toString();
        if (!encoder.matches(password, principal.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(principal.getRole().toString()));
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
