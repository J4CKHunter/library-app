package com.test.library.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.library.service.UserDetailsService;
import com.test.library.utils.TokenGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenGenerator tokenGenerator;

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    public JwtFilter(TokenGenerator tokenGenerator, UserDetailsService userDetailsService, ObjectMapper objectMapper) {
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromHttpHeader(request);
        String username;

        try {
            if(!token.isBlank()){
                username = tokenGenerator.verifyJWT(token).getSubject(); // biz subject'e username koymuştuk
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);

            }
        } catch (Exception e){

            ObjectMapper mapper = new ObjectMapper();

            response.setContentType(MediaType.APPLICATION_JSON.toString());

            Map<String, String> errors = new HashMap<>();
            errors.put("error", e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


            response.getWriter().write(mapper.writeValueAsString(errors));
        }
    }

    private String getTokenFromHttpHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            return "";
        }

        return authorizationHeader.substring(7); // 7 = "Bearer "
    }
}
