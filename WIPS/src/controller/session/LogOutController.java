package controller.session;

import model.Wips;

public class LogOutController {
	
	/**
	 * This method will take the user back to the login screen.
	 */
	public static void logInScreen() {
		//opens Window
		serialize();
		Wips.currentUser = null;
		//Goes back to the login screen
	}
	
	/**
	 * This  method will serialize the wips class before logout. Internally, it will call the login screen. 
	 */
	private static void serialize() {
		
	}
}
