package com.psg.ihsserver.exception;

public class UnAuthorizedRequestException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedRequestException(String message)
	{
		super(message);
	}

}
