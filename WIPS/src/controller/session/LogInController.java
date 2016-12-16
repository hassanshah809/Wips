package controller.session;

import java.io.IOException;

import errors.AbsError;
import errors.AuthenticationError;
import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Wips;
import model.user.EndUser;
import model.user.User;

//@helper: Kenneth Zhang, Deepkumar Patel, Hassan Shah, Kush Oza 


public class LogInController {
	
	@FXML
	Button loginBtn;

	@FXML
	TextField userField, passField;
			 
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
	
	@FXML
	protected void initialize() {
		//Do something once the FXML is done
	}
	/**
	 * This method will compare the username and password with the list of users in wips.
	 * @param user User
	 * @param password String
	 */
	public User authenticate(User user, String password) {
		//after autheticantion
		try {
			Wips.remake();
			Wips w = Wips.getInstance();
			if(w.getUsers().contains(user)){
				User passup = w.getUsers().get(w.getUsers().indexOf(user));
				if(passup.getPassword().equals(password)){
					w.setCurrentUser(w.getUsers().get(w.getUsers().indexOf(user)));
					return passup;
				}
				else	//if incorrect password entered for the user
				{
					e = new AuthenticationError();
					e.addError("Incorrect password for " + user.getUsername() + ".");
					e.handle();
				}
			}
			else	//user does not exist
			{
				e = new AuthenticationError();
				e.addError("Invalid username.");
				e.handle();
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * Returns the user or else it returns null.
	 * @return User
	 */
	public User getUser() {
		return null;
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == loginBtn) {
			//Get Username
			String username = userField.getText();
			User user = new EndUser(username, false);
			//Get Password
			String password = passField.getText();
			//un comment 2 lines below to see serialiazable works
			User realUser = authenticate(user, password);   // username hassan8 pas ahanas262
//			List<Entity> roles = new ArrayList<>();
//			roles.add(new Entity("dev"));
//			
//			List<String> val = new ArrayList<>();
//			val.add("new");
//			Developer admin = new Developer("shah hassan", roles, val);
//			Wips.getInstance().addUser(admin);
			if(realUser != null && realUser.isDeveloper()) {
		//	If the user is developer then open the following screen
				Parent d = FXMLLoader.load(getClass().getResource("/view/developer/dhomescreen.fxml"));
				OpenScreen.openScreen("dhomescreen.fxml", handler, "Developer", d, getClass(),"/view/developer/dhomescreen.css");	
			}			
			//Parent l = FXMLLoader.load(getClass().getResource("/view/developer/dformcreate.fxml"));
			//OpenScreen.openScreen("dformcreate.fxml", handler, "Create Form", l, getClass(),"/view/developer/dformcreate.css");
			
			//If the user is enduser then open the following screen
			if(realUser != null && !realUser.isDeveloper()){
				Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/ehomescreen.fxml"));
				OpenScreen.openScreen("ehomescreen.fxml", handler, "End User", e, getClass(),"/view/enduser/ehomescreen.css");
			}
		}
	}
}
