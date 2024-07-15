package com.dexter.attendanceSystem.config.jwtConfig;


import com.dexter.attendanceSystem.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        // set request header
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userId;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        try{

            jwtToken = authHeader.substring(7);
            userId = jwtService.extractUsername(jwtToken); // extract user Id from JWT token
            if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userId);
                if(jwtService.isTokenValid(jwtToken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);

        }catch (Exception exception){

            String errorMessage =  exception instanceof ExpiredJwtException ? "Token expired": "Invalid token";
            ApiResponse errorResponse = ApiResponse.builder()
                    .data(errorMessage)
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .isSuccessful(false)
                    .path(request.getRequestURI())
                    .build();
            response.setContentType(MediaType.APPLICATION_NDJSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().print(objectMapper.writeValueAsString(errorResponse));


        }
        //get the token from the header

    }
}
