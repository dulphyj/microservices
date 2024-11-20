package com.dlph.microservicio_contactos.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

import static com.dlph.microservicio_contactos.util.Constants.CLAVE;
import static com.dlph.microservicio_contactos.util.Constants.TIEMPO_VIDA;

@RestController
@AllArgsConstructor
public class AuthController {
    AuthenticationManager manager;

    @PostMapping(value = "login", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login (@RequestParam("user") String user,@RequestParam("password") String pwd){
        try {
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user, pwd));
            return new ResponseEntity<>(getToken(authentication), HttpStatus.OK);
        } catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private String getToken(Authentication authentication) {
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_VIDA))
                .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
                .compact();
        return token;
    }
}

