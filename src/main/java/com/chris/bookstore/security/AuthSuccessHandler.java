/**
 * Chris Book Store.
 */


package com.chris.bookstore.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author chris
 *
 */
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
    	request.getParameter("username");
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("{\"status\": \"OK\", \"message\": \"Welcome, "+authentication.getName()+"\"}");
        response.getWriter().flush();
    }
    
    

    
}