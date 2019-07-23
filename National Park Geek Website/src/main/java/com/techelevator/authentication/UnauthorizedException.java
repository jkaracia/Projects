package com.techelevator.authentication;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class UnauthorizedException extends Exception {
	
	private static final long serialVersionUID = 1L;


}
