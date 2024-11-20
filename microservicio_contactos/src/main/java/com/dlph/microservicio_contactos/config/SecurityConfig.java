package com.dlph.microservicio_contactos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    AuthenticationManager manager;
   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager() throws  Exception{
        List<UserDetails> user = List.of(
                User.withUsername("user1")
                        .password("{noop}user1")
                        .roles("USERS")
                        .build(),
                User.withUsername("user2")
                        .password("{noop}user2")
                        .roles("OPERATOR")
                        .build(),
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles("USERS", "ADMIN")
                        .build());
        return new InMemoryUserDetailsManager(user);
    }*/

    /*@Bean //encriptado contraseñas
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }*/


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        manager = configuration.getAuthenticationManager();
        return manager;
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("5768012");
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(ds);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user, password, enabled from users where user=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user, rol from roles where user=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cus -> cus.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/contactos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/contactos/**").hasRole("ADMIN")
                        .requestMatchers("/contactos").authenticated().anyRequest().permitAll())
                //.httpBasic(Customizer.withDefaults());
                .addFilter(new JWTAuthorizationFilter(manager));
        return http.build();
    }

}
