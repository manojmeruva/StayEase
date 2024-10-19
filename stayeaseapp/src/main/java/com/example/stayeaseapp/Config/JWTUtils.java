package com.example.stayeaseapp.Config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.stayeaseapp.Models.UserDetailsImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

@Component
public class JWTUtils {

    
     private final SecretKey jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

     public JWTUtils() {
        // Generate a secure key for HS512
        this.jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Map<String, Object> claims = new HashMap<>();
    // Adding roles to the claims, so that it can be used later
    claims.put("roles", userPrincipal.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(jwtSecret, SignatureAlgorithm.HS512)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return (Jwts.parserBuilder().setSigningKey(jwtSecret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> getRolesFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return claims.get("roles", List.class);  // Extract roles from JWT claims
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(jwtSecret) // Set the key
            .build()                         // Build the parser
            .parseClaimsJws(authToken); 
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}