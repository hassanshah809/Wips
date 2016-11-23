package controller.endUser;

import java.io.IOException;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HomeController {

	@FXML
	Button testbtn;
	
	/**
	 * This method will open “All workflow” tab.
	 */
	public void allWorkFlowController(){
		
	}
	
	/**
	 * This method will open “Active Workflow” tab.
	 */
	public void activeWorkFlows() {
		
	}
	
	/**
	 * This method will open “Notifications” tab.
	 */
	public void notifController() {
		
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == testbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eformgen.fxml"));
			OpenScreen.openScreen("eformgen.fxml", handler, "Sign in form", e, getClass(),"/view/enduser/eformgen.css");
		}
	}
}
