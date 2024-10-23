package com.qlBanSach.BookManager.Service.User.Oauth2;

import com.qlBanSach.BookManager.Model.Entity.UserEntity;
import com.qlBanSach.BookManager.Repository.IUserRepository;
import com.qlBanSach.BookManager.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Component
public class HandleGoogleLogin {
    private final IUserRepository userRepository;
    private final RoleRepository roleRepository;

    public void processGoogleLogin(OAuth2AuthenticationToken authentication) {
        String email = authentication.getPrincipal().getAttribute("email");
        String firstname = authentication.getPrincipal().getAttribute("given_name");
        String lastname = authentication.getPrincipal().getAttribute("family_name");

        UserEntity user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setEmail(email);
                    newUser.setFirstname(firstname);
                    newUser.setLastname(lastname);
                    newUser.setPassword("");
                    newUser.setCreatedBy("SYSTEM");
                    newUser.setRoles(new ArrayList<>());
                    newUser.getRoles().add(roleRepository.findByCode("USER"));
                    newUser.setStatus("ACTIVE");
                    return userRepository.save(newUser);
                });

        List<? extends GrantedAuthority> roles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .toList();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
