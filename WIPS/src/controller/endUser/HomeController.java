package controller.endUser;

import java.io.IOException;

import controller.session.LogOutController;
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
import model.user.EndUser;
import model.wips.Entity;
import model.wips.WorkFlow;
import model.wips.forms.Form;

public class HomeController {

	@FXML
	Button notibtn, logoutbtn, allwfbtn;

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
	public void allWorkFlowController() {
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
	//	notif();
	}

	public void tabListeners() {
		tabpane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab.equals(allworkflows)) {
				allWorkFlowController();
			} else if (newTab.equals(joinedworkflows)) {
				allJoinedWorkFlows();
				System.out.println("Joined wf");
			} else if (newTab.equals(notification)) {
				System.out.println("noti");
				notif();
			}
		});
	}

	public void notif() {
		EndUser u = (EndUser) Wips.getInstance().getCurrentuser();
		System.out.println("befor if sixe of stack " + u.getRecievedForm().size());
		if (u.getRecievedForm().size() > 0) {
			System.out.println("sixe of stack " + u.getRecievedForm().size());
			notilistOb = FXCollections.observableArrayList(u.getRecievedForm().peek().getFormWorkFlow());
			notilist.setItems(notilistOb);
		}
	}
	
	public void allJoinedWorkFlows() {
		EndUser u = (EndUser) Wips.getInstance().getCurrentuser();
		if (u.getAllWorkflows().size() > 0 ) {
			jwflistOb = FXCollections.observableArrayList(u.getAllWorkflows());
			jwflist.setItems(jwflistOb);
		}
	}

	public void populate() {
		System.out.println("roles of the curren usr " + Wips.getInstance().getCurrentuser().getRoles());
		cbox.getItems().addAll(Wips.getInstance().getCurrentuser().getRoles());
		cbox.setOnAction((event) -> {
			Entity e = cbox.getSelectionModel().getSelectedItem();
			Wips.getInstance().setRoleOfCurrentUser(e);
			allWorkFlowController();
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
		if (b == notibtn) {
			Wips.getInstance().setCurrentWorkFlow(notilist.getSelectionModel().getSelectedItem());
			System.out.println("ntoi button workflow " + Wips.getInstance().getCurrentWorkFlow().getCurrentStates());
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eformgen.fxml"));
			OpenScreen.openScreen("eformgen.fxml", handler, "Sign in form", e, getClass(),
					"/view/enduser/eformgen.css");
		} else if (b == logoutbtn) {
			LogOutController.logInScreen();
			Parent e = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", e, getClass(), "/view/session/application.css");
		} else if (b == allwfbtn) {
			WorkFlow wf = (WorkFlow) clone();
			Wips.getInstance().setCurrentWorkFlow(wf);
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eformgen.fxml"));
			OpenScreen.openScreen("eformgen.fxml", handler, "Form", e, getClass(), "/view/endUser/eformgen.css");
		}
	}
	
	@Override
	public Object clone() {
		WorkFlow wf = allwflist.getSelectionModel().getSelectedItem();
		WorkFlow newWf = new WorkFlow(wf.getState(), wf.getEntity(), wf.getTransition(), 0);
		newWf.setWorkFlowName(wf.getWorkFlowName());
		Form f = wf.getForm();
		f.clear();
		newWf.setForm(f);
		newWf.setCurrentState(wf.getCurrentStates());
		newWf.setStartState(wf.getStartState());
		return newWf;
	}
}