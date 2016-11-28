package controller.endUser;

import java.io.IOException;

import helper.OpenScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
	
	@FXML
	TabPane tabpane;
	
	@FXML
	Tab allworkflows, joinedworkflows, notification;
	
	private ObservableList<WorkFlow> allwflistOb, jwflistOb, notilistOb;
	/**
	 * This method will open “All workflow” tab.
	 */
	public void allWorkFlowController(){
		AllWorkFlowController allWfController = new AllWorkFlowController();
		allwflistOb = FXCollections.observableArrayList(allWfController.getAllWorkFlowsCanJoin());
		System.out.println("all the owrk in end home " + allWfController.getAllWorkFlowsCanJoin());
		allwflist.setItems(allwflistOb);
	}
	
	@FXML
	protected void initialize() {
		populate();
		tabListeners();
		allWorkFlowController();
   }
	public void tabListeners() {
		tabpane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
	        if(newTab.equals(allworkflows)){
	        	allWorkFlowController();
	        } else if (newTab.equals(joinedworkflows)) {
	        	System.out.println("Joined wf");
	        } else if  (newTab.equals(notification)) {
	        	System.out.println("noti");
	        }
	    });
	}
	public void populate() {
		System.out.println("roles of the curren usr " + Wips.getInstance().getCurrentuser().getRoles());
		cbox.getItems().addAll(Wips.getInstance().getCurrentuser().getRoles());
		cbox.setOnAction((event) -> {
			Entity e = cbox.getSelectionModel().getSelectedItem();
			Wips.getInstance().setRoleOfCurrentUser(e);
			System.out.println("in populate " + e);
			System.out.println("in ppulate in woiops " + Wips.getInstance().getRoleOfCurrentUser());
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