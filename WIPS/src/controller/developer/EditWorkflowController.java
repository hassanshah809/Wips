package controller.developer;

import java.io.IOException;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import model.wips.State;

public class EditWorkflowController {

	@FXML
	Button addnewuser, backbtn, logoutbtn;
	
	@FXML
	ListView<State> allstates, allusers;
	
	@FXML
	TextArea distinctstatevalues, userroles, uservalues;
	
	@FXML
	TabPane tabpane;
	
	@FXML
	Tab workflowtab, usertab;
	
	@FXML
	protected void initialize() {
		
	}
	
	public EditWorkflowController() {
		// TODO Auto-generated constructor stub
	}
	
	public void tabListeners() {
		tabpane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab.equals(workflowtab)) {
			
			} else if (newTab.equals(usertab)) {
				
			}
		});
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == addnewuser) {
			
		} else if (b == backbtn) {
			Parent l = FXMLLoader.load(getClass().getResource("/view/developer/dhomescreen.fxml"));
			OpenScreen.openScreen("dhomescreen.fxml", handler, "Developer", l, getClass(),"/view/developer/dhomescreen.css");
		} else if (b == logoutbtn) {
			Parent l = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", l, getClass(),"/view/session/application.css");
		}
	}

}
