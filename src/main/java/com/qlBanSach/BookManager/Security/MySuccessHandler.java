package com.qlBanSach.BookManager.Security;

import com.qlBanSach.BookManager.Service.User.Oauth2.HandleGoogleLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MySuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy;
    private final HandleGoogleLogin handleGoogleLogin;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            handleGoogleLogin.processGoogleLogin((OAuth2AuthenticationToken) authentication);
        }
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectStrategy.sendRedirect(request, response, "/admin/");
        } else {
            redirectStrategy.sendRedirect(request, response,"/");
        }
    }
}
