
package net.javaguides.ems.config;

import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.ems.security.OAuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import net.javaguides.ems.security.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final OAuthSuccessHandler successHandler;

    public SecurityConfig(OAuthSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login**", "/oauth2/**").permitAll()
                        .requestMatchers("/api/user/**").authenticated()
                        .requestMatchers("/api/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, exx) -> {
                            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        })
                )
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class) // 🔥 IMPORTANT
                .oauth2Login(oauth -> oauth
                        .successHandler(successHandler)
                );

        return http.build();
    }
}