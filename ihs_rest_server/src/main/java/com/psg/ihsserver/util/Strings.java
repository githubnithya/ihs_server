package com.psg.ihsserver.util;

public class Strings {

	public static final String RESOURCE_SERVER_NAME = "resource_server_name";
	public static final String ACCESS_TOKEN_VALID = "access_token_valid";

	public static final String CLIENT_ID = "SSgpWdw5SSgpWdw6_ID";
	public static final String CLIENT_SECRET = "SSgpWdw5SSgpWdw6_SEKRET";
	public static final String USERNAME = "ihs_userName";
	public static final String PASSWORD = "ihs_password";

	public static final String HEADER_WWW_AUTHENTICATE = "WWW-Authenticate";
	public static final String HEADER_AUTHORIZATION = "Authorization";

	public static final String AUTHORIZATION_CODE = "897d7666602b8143d948ba433f1af518";
	
	public static final String BASE_URI = "http://localhost:8082/ihsserver";
	
	public enum TokenProvider{
		
		ihsdev ("ihs", 
				"http://localhost:8082/ihsserver/auth", 
				"http://localhost:8082/ihsserver/token"),
		
		ihsprod ("ihs", 
				"http://localhost:8082/ihsserver/auth", 
				"http://localhost:8082/ihsserver/token");
		
		private String providerName;
		
		private String authzEndpoint; 
		
		private String tokenEndpoint;
		
		/**
		 * Get the provider name
		 * 
		 * @return Returns the provider name
		 */
		public String getProviderName() {
			return providerName;
		}
		
		/**
		 * Get the authorization endpoint
		 * 
		 * @return Returns the authorization endpoint
		 */
		public String getAuthzEndpoint() {
			return authzEndpoint;
		}
		
		/**
		 * Get the access token endpoint
		 * 
		 * @return Returns the access token endpoint
		 */
		public String getTokenEndpoint() {
			return tokenEndpoint;
		}
		
		/**
		 * Full private constructor
		 * 
		 * @param providerName The provider name
		 * @param authzEndpoint The authorization endpoint
		 * @param tokenEndpoint The token endpoint
		 * @return 
		 */
		private TokenProvider(
				final String providerName, 
				final String authzEndpoint, 
				final String tokenEndpoint) {
			
			this.providerName = providerName;
			this.authzEndpoint = authzEndpoint;
			this.tokenEndpoint = tokenEndpoint;
		}
		
		
	}

}
