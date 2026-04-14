package net.javaguides.ems.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.repository.UserRepository;
import net.javaguides.ems.utility.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public OAuthSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        assert oAuth2User != null;
        String email = (String) oAuth2User.getAttributes().get("email");
        String name = (String) oAuth2User.getAttributes().get("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setRole("USER");
                    return userRepository.save(newUser);
                });

        System.out.println(oAuth2User.getAttributes());
        System.out.println("USER EMAIL = " + user.getEmail());
        String token = JwtUtil.generateToken(user.getEmail(),user.getRole());

        String redirectUrl = "http://localhost:3000/oauth-success?token=" + token;

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
