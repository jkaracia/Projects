package com.techelevator.authentication;

import java.util.List;

public interface UserDao {
	
	public User saveUser(String userName, String password);
	
	public void changePassword(User user, String newPassword);
	
	public User getValidUserWithPassword(String userName, String password);
	
	public List<User> getAllUsers();

}
