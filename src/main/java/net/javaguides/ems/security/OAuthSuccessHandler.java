package net.javaguides.ems.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.repository.UserRepository;
import net.javaguides.ems.utility.JwtUtil;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${frontend.url}")
    private String frontendUrl;

    private final UserRepository userRepository;
private final EmployeeRepository employeeRepository;

    public OAuthSuccessHandler(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
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

//        User user = userRepository.findByEmail(email)
//                .orElseGet(() -> {
//
//                    User newUser = new User();
//                    newUser.setEmail(email);
//                    newUser.setName(name);
//                    newUser.setRole("USER");
//                    return userRepository.save(newUser);
//                });

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setRole("USER");

                    User savedUser = userRepository.save(newUser);

                    Employee employee = new Employee();
                    employee.setUser(savedUser);


                    employeeRepository.save(employee);

                    return savedUser;
                });

        System.out.println(oAuth2User.getAttributes());
        System.out.println("USER EMAIL = " + user.getEmail());
        String token = JwtUtil.generateToken(user.getEmail(),user.getRole());

        String redirectUrl = frontendUrl +"/oauth-success?token=" + token;

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
