package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${secret}")
    private String secret;

    public Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetailsDemo userDetailsDemo) {

        Map<String,Object> donnees = new HashMap<>();

        donnees.put("id",userDetailsDemo.getUtilisateur().getId());

        String listeDroit = userDetailsDemo
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(","));

        donnees.put("droits", listeDroit);

        Calendar dateAujourdhui = Calendar.getInstance();
        long dateAujourdhuiEnMilliseconde = dateAujourdhui.getTimeInMillis();
        Date dateExpiration = new Date(dateAujourdhuiEnMilliseconde + (4 * 60 * 1000));

        donnees.put("numeroToken", userDetailsDemo.getUtilisateur().getNumeroToken());

        return Jwts.builder()
                .setClaims(donnees)
                //.setExpiration(dateExpiration) //Décommenter la ligne pour activer l'expiration
                .setSubject(userDetailsDemo.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValide(String token, UserDetailsDemo userDetails) {

        Claims claims = getTokenBody(token);

        boolean utilisateurValide = claims.getSubject().equals(userDetails.getUsername());

        boolean numeroTokenValide = claims
                .get("numeroToken")
                .equals(userDetails.getUtilisateur().getNumeroToken());

        return utilisateurValide && numeroTokenValide;
    }
}
