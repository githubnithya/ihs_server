package com.psg.ihsserver.oauth2;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.psg.ihsserver.util.Strings;

@Path("/token")
public class TokenEndPoint {
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response authorize(@Context HttpServletRequest request) throws OAuthSystemException {

		OAuthTokenRequest oauthRequest = null;
		System.out.println("oauthRequest.getGrantType()1 ");

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		try {
			
		//	 String test = request.getInputStream().lines().collect(Collectors.joining(System.lineSeparator()));
		//	 System.out.println(test);
			oauthRequest = new OAuthTokenRequest(request);

			System.out.println("oauthRequest.getGrantType() 2 " + oauthRequest.getGrantType());

			// check if clientid is valid
			if (!Strings.CLIENT_ID.equals(oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID))) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT).setErrorDescription("client_id not found")
						.buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}

			// do checking for different grant types
			if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
				// if
				// (!RedirectUriEndPoint.authCode.equals(oauthRequest.getParam(OAuth.OAUTH_CODE)))
				// {
				System.out
						.println("oauthRequest.getParam(OAuth.OAUTH_CODE) " + oauthRequest.getParam(OAuth.OAUTH_CODE));
				if (!getAuthCode().equals(oauthRequest.getParam(OAuth.OAUTH_CODE))) {
					OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
							.setError(OAuthError.TokenResponse.INVALID_GRANT)
							.setErrorDescription("invalid authorization code").buildJSONMessage();
					return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
				}
			} else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString())) {
				if (!Strings.PASSWORD.equals(oauthRequest.getPassword())
						|| !Strings.USERNAME.equals(oauthRequest.getUsername())) {
					OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
							.setError(OAuthError.TokenResponse.INVALID_GRANT)
							.setErrorDescription("invalid username or password").buildJSONMessage();
					return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
				}
			} else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.REFRESH_TOKEN.toString())) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_GRANT)
						.setErrorDescription("invalid username or password").buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}

			OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(oauthIssuerImpl.accessToken()).setExpiresIn("3600").buildJSONMessage();

			return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
			OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
					.buildJSONMessage();
			return Response.status(res.getResponseStatus()).entity(res.getBody()).build();
		} 
	}

	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response authorizeGet(@Context HttpServletRequest request) throws OAuthSystemException {
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
				.setAccessToken(oauthIssuerImpl.accessToken()).setExpiresIn("3600").buildJSONMessage();

		return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
	}

	private String getAuthCode() {

		String authBae64 = Base64.getEncoder()
				.encodeToString(new String(Strings.CLIENT_ID + ":" + Strings.CLIENT_SECRET).getBytes());
		System.out.println("authBae64 " + authBae64);
		return authBae64;

	}
}
