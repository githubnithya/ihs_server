package com.psg.ihsserver;

import java.io.IOException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.exception.UnAuthorisedRequestException;
import com.psg.ihsserver.oauth2.ResourceEndPoint;
import com.psg.ihsserver.oauth2.Secured;
import com.psg.ihsserver.util.Utils;


@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter{
	private static final Logger logger = Logger.getLogger(AuthenticationFilter.class);

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		final String AUTHENTICATION_HEADER = "Authorization";

		
		logger.info("In Authentication Filter");
		String authorizationHeader = arg0.getHeaderString(AUTHENTICATION_HEADER);
		 
		// Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        
     // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        try {

            // Validate the token
        		validateToken(token);
        	logger.info("token validated");
        } catch (UnAuthorisedRequestException e) {
        	logger.error(e.getMessage());
        	arg0.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }

	}
	
	private boolean validateToken(String token) throws UnAuthorisedRequestException
	{
		return token.equals(Utils.getAuthToken());
	}

}
