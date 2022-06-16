package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecuriteDemo extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceDemo userDetailsServiceDemo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsServiceDemo)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/connexion","/inscription","/").permitAll()
                    //.antMatchers("/supprimer-utilisateur/**").hasRole("SUPPRIME_UTILISATEUR")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").hasAnyRole("USER","ADMIN")
                .and().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource configurationCrossOrigin() {
        CorsConfiguration maConfiguration = new CorsConfiguration();

        maConfiguration.setAllowedOrigins(List.of("*"));
        maConfiguration.setAllowedMethods(List.of("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        maConfiguration.setAllowedHeaders(List.of(
                "Access-Control-Allow-Origin",
                "Authorization",
                "Cache-Control",
                "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", maConfiguration);
        return source;
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthentifactionManager() throws Exception {
        return super.authenticationManager();
    }

}
