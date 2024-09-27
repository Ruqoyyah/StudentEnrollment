package com.school.sport_enrollment.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.school.sport_enrollment.Utils.CustomUserDetailService;
import com.school.sport_enrollment.Utils.JwtRequestFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //http = http.cors().and().csrf().disable();
         http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + ex.getMessage() + "\"}");
                }
            )
            .and();
        http
                .csrf().disable()  // Disable CSRF for REST APIs
                .cors().and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                "/api/sport/create_sport",
                                "/api/user/create_super_admin",
                                "/v3/api-docs/**",
                                "/api/user/create_student",
                                "/api/user/get_all_user",
                                "/swagger-ui/**", 
                                "/swagger-ui.html",
                                "/api/user/get_all_User_by_type/{userType}",
                                "/api/user/delete_user/{id}",
                                "/api/user/update_user/{id}",
                                "/api/user/get_user_by_id/{id}",
                                "/api/user/signin",  // Public endpoints
                                "/api/user/update_user_with_sport/{userId}/{sportId}",
                                "/api/user/get_user_by_sportid/{sportid}",
                                "api/user/update_user/{userid}/{sportid}"
                        ).permitAll()  // Allow listed endpoints without authentication
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Make the app stateless

        // Add JwtRequestFilter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Implement your UserDetailsService to load users from the database
        return new CustomUserDetailService();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*") // Adjust allowed origins as needed
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
