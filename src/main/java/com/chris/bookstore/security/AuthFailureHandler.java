/**
 * Chris Book Store.
 */


package com.chris.bookstore.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author chris
 *
 */


@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
    	
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print("{\"status\": \""+HttpServletResponse.SC_UNAUTHORIZED+"\",\"message\": \""+exception.getMessage()+"\" }");
        response.getWriter().flush();
    }
}