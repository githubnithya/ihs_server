package com.psg.ihsserver.oauth2;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

import com.psg.ihsserver.daoimpl.PatientDaoImpl;


@Path("/redirect")
public class RedirectUriEndPoint {
	
	private static final Logger logger = Logger.getLogger(RedirectUriEndPoint.class);
	public static String authCode;
	
	@GET
    public Response authorize(@Context HttpServletRequest request)
        throws URISyntaxException, OAuthSystemException {

        logger.info("Request Params:" + request.getParameterMap());
        
        authCode = request.getParameter(ResponseType.CODE.toString());
        logger.info(authCode);
//     // Request to exchange code for access token and id token
//        OAuthClientRequest oauth_request = OAuthClientRequest
//               .tokenProvider(TokenProvider.ihsdev)
//                .setCode(code)
//                .setClientId(Strings.CLIENT_ID)
//                .setClientSecret(Strings.CLIENT_SECRET)
//                .setRedirectURI(UriBuilder.fromUri(Strings.BASE_URI)
//                    .path("redirect").build().toString())
//                    .setGrantType(GrantType.AUTHORIZATION_CODE)
//                    .buildBodyMessage();
//        
        
        return Response.ok().build();
    }
}
