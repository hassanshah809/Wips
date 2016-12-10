package controller.session;

import java.io.IOException;

import model.Wips;

public class LogOutController {
	
	/**
	 * This method will take the user back to the login screen.
	 */
	public static void logInScreen() {
		//opens Window
		Wips.getInstance().setCurrentUser(null);
		Wips.getInstance().setRoleOfCurrentUser(null);
		serialize();
		//Goes back to the login screen
	}
	
	/**
	 * This  method will serialize the wips class before logout. Internally, it will call the login screen. 
	 */
	private static void serialize() {
		try {
			Wips.getInstance().make();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
