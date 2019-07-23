package com.techelevator.authentication;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

public class User {
	
	@NotBlank(message="Username is required")
    private String username;
	
	private long id;
	
	@NotBlank(message="Password is required")
    private String password;
	
    private String confirmPassword;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }
    
    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public String getUsername() {
        return username;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
