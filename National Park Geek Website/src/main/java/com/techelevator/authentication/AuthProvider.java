package com.techelevator.authentication;

public interface AuthProvider {
	
	boolean isLoggedIn();
	
	User getCurrentUser();
	
	boolean signIn(String username, String password);
	
	void logOff();
	
	boolean changePassword(String existingPassword, String newPassword);
	
	void register(String username, String password);

}
