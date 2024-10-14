package com.qlBanSach.BookManager.Security;

import com.qlBanSach.BookManager.Model.Entity.UserEntity;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if(email == null || password == null){
            throw new BadCredentialsException("Invalid email or password");
        }
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataInvalidException("Invalid email or password"));
        String status = user.getStatus();
        if(status == null || status.equals("INACTIVE")){
            throw new DataInvalidException("This account is lock now!!");
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Invalid email or password!");
        }
        List<? extends GrantedAuthority> roles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .toList();
        return new UsernamePasswordAuthenticationToken(email, password, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
