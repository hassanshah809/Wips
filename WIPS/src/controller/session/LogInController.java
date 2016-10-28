package controller.session;

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
	public void authenticate(User user, String password) {
		//after autheticantion
		Wips.currentUser = getUser();
	}
	
	/**
	 * Returns the user or else it returns null.
	 * @return User
	 */
	public User getUser() {
		return null;
	}
}
