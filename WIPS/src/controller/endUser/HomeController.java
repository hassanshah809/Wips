package controller.endUser;

import java.io.IOException;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Wips;
import model.wips.Entity;
import model.wips.WorkFlow;

public class HomeController {

	@FXML
	Button testbtn, logoutbtn;
	
	@FXML
	ListView<WorkFlow> allwflist, jwflist, notilist;
	
	@FXML
	ComboBox<Entity> cbox;
	
	/**
	 * This method will open “All workflow” tab.
	 */
	public void allWorkFlowController(){
		
	}
	
	@FXML
	protected void initialize() {
		populate();
		
   }
	
	public void populate() {
		cbox.getItems().addAll(Wips.getInstance().getCurrentuser().getRoles());
		cbox.setOnAction((event) -> {
			Entity e = cbox.getSelectionModel().getSelectedItem();
			Wips.getInstance().setRoleOfCurrentUser(e);
		});
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
		} else if (b == logoutbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", e, getClass(),"/view/session/application.css");
		}
	}
}
