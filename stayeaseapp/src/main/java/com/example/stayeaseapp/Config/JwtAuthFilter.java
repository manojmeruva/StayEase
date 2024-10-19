package com.example.stayeaseapp.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.stayeaseapp.Services.UserDetailsServiceImpl;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthFilter(JWTUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token = parseJwt(request);

                if (token != null && jwtUtils.validateJwtToken(token)) {
                    String username = jwtUtils.getUserNameFromJwtToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
                    // Build the authentication token with the user details
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
            
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
                    // Set authentication context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            
                filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
