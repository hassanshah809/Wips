package controller.developer;

import java.io.IOException;
import java.util.List;

import helper.OpenScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Wips;
import model.user.Developer;
import model.user.EndUser;
import model.wips.WorkFlow;

public class AdminHomeController {
	
	@FXML
	Button createWFBtn, logoutBtn;
	
	@FXML
	ListView<WorkFlow> listview;
	
	ListView<WorkFlow> list = new ListView<WorkFlow>();
	ObservableList<WorkFlow> items = FXCollections.observableArrayList();
	
	/**
	 * Running this method will redirect the workflow application developer to the window where 
	 * he or she is able to create the workflow.
	 */
	public void createWorkFlow() {
		//This method will open 
	}
	
	/**
	 * Running this method will redirect the workflow application developer to the window where
	 * the workflow application.
	 */
	public void showFinished() {
		// show all workflows that has been created to list
		CreatedWorkflowController cw = new CreatedWorkflowController();
		items = FXCollections.observableArrayList(cw.getCreatedWorkflow());
		listview.setItems(items);
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == createWFBtn) {
			//If the user is developer then open the following screen
			
		} else if (b== logoutBtn) {
			Parent l = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", l, getClass(),"/view/session/application.css");
		}
	}
}
