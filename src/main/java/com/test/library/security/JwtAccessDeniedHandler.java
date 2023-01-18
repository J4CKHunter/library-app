package com.test.library.security;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    // Authentication başarılı, authorization başarısız yani yetkisi yok.
    // returns 403 Forbidden
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println("\"error\":\"" + accessDeniedException.getMessage() + "\"");
    }
}
