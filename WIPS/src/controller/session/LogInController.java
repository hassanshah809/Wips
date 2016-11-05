package controller.session;

import java.io.IOException;

import errors.AbsError;
import model.Wips;
import model.user.User;

public class LogInController {
	/**
	 * 	This will contain the current user information
	 */
	User user;
	/**
	 * Wips will contain the information about the users, so that when the user logs 
	 * in we can compare the current username and password with the list of users and 
	 * password.
	 */
	Wips wips;
	/**
	 * Shows an error if the username or password is wrong.
	 */
	AbsError e;
	/**
	 * This method will compare the username and password with the list of users in wips.
	 * @param user User
	 * @param password String
	 */
	public boolean authenticate(User user, String password) {
		//after autheticantion
		try {
			Wips.remake();
			Wips w = Wips.getInstance();
			if(w.getUsers().contains(user)){
				if(w.getUsers().get(w.getUsers().indexOf(user)).getPassword().equals(password))
					return true;
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * Returns the user or else it returns null.
	 * @return User
	 */
	public User getUser() {
		return null;
	}
}
