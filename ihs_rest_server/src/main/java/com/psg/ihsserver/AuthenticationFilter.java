package com.psg.ihsserver;

import java.io.IOException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.psg.ihsserver.exception.UnAuthorizedRequestException;
import com.psg.ihsserver.oauth2.ResourceEndPoint;
import com.psg.ihsserver.oauth2.Secured;
import com.psg.ihsserver.util.Utils;


@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		final String AUTHENTICATION_HEADER = "Authorization";

		
		System.out.println("In Authentication Filter");
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

        } catch (UnAuthorizedRequestException e) {
        	arg0.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }

	}
	
	private boolean validateToken(String token) throws UnAuthorizedRequestException
	{
		return token.equals(Utils.getAuthToken());
	}

}
