package controller.endUser;

import java.io.IOException;
import java.util.Arrays;

import controller.session.LogOutController;
import helper.OpenScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Wips;
import model.user.EndUser;
import model.wips.Entity;
import model.wips.Transition;
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
	
	@FXML
	Label statuslabel;

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
	//	disabler(true);
		jwflistOb = FXCollections.observableArrayList(Wips.getInstance().getCurrentuser().getAllWorkflows());
		jwflist.setItems(jwflistOb);
		jwflist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkFlow>() {

			@Override
			public void changed(ObservableValue<? extends WorkFlow> observable, WorkFlow oldValue, WorkFlow newValue) {
				int indexOfJoinedWorkFlow = jwflist.getSelectionModel().getSelectedIndex();
				if(indexOfJoinedWorkFlow >= 0){
					Wips.getInstance().getCurrentuser().getAllWorkflows().get(indexOfJoinedWorkFlow).setHasUpdate((EndUser)Wips.getInstance().getCurrentuser(), false);
					status(Wips.getInstance().getCurrentuser().getAllWorkflows().get(indexOfJoinedWorkFlow).getForm());
					EndUser u = (EndUser) Wips.getInstance().getCurrentuser();
					u.update();
					
					System.out.println("new work flow iupdae size" + Wips.getInstance().getCurrentuser().getAllWorkflows().size());
					System.out.println("form lsize  of  obsrvabel " + jwflistOb.size());
			//	allJoinedWorkFlows();
					jwflist.setItems(null);
					jwflistOb = FXCollections.observableArrayList(Wips.getInstance().getCurrentuser().getAllWorkflows());
					jwflist.setItems(jwflistOb);
					updates();
				}
			}
		});
		
		updates();
		populate();
		tabListeners();
	//	allWorkFlowController();
	//	notif();
	}

	public void updates() {
		EndUser user = (EndUser) Wips.getInstance().getCurrentuser();
		if(user.getNumOfUpdates() >= 0) {
			joinedworkflows.setText("Joined WorkFlows ( " + user.getNumOfUpdates() + " )");
		} else {
			joinedworkflows.setText("Joined WorkFlows");
		}
		
		if(user.getNumOfNotif() >= 0) {
			notification.setText("Notifications ( " + user.getNumOfNotif() + " )");
		} else {
			notification.setText("Notificationsk");
		}
	}

	public void tabListeners() {
		tabpane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab.equals(allworkflows)) {
				allWorkFlowController();
			} else if (newTab.equals(joinedworkflows)) {
				jwflist.getSelectionModel().clearSelection();
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
			notilistOb = FXCollections.observableArrayList(u.getRecievedForm());
			notilist.setItems(notilistOb);
		}
	}
	
	public void allJoinedWorkFlows() {
		EndUser u = (EndUser) Wips.getInstance().getCurrentuser();
//		if (u.getAllWorkflows().size() > 0 ) {
			jwflistOb = FXCollections.observableArrayList(Wips.getInstance().getCurrentuser().getAllWorkflows());
			jwflist.setItems(jwflistOb);
	//	}
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
//			disabler(false);
//			updates();
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
	
	public void status(Form f) {
		StringBuilder sb = new StringBuilder();
		for(Entity e : f.getRoles()) {
			sb.append(e.getRole() +" ");
		}
		statuslabel.setText("Status: " + sb.toString());
	}
	
	public void disabler(boolean b) {
		tabpane.setDisable(b);
		allwfbtn.setDisable(b);
	}
	@Override
	public Object clone() {
		WorkFlow wf = allwflist.getSelectionModel().getSelectedItem();
		WorkFlow newWf = new WorkFlow(wf.getState(), wf.getEntity(), wf.getTransition(), 0);
		newWf.setWorkFlowName(wf.getWorkFlowName());
		Form f = new Form(wf.getForm().getFormName(), newWf);
		f.addCouple(wf.getForm().getCouples());
//		f.clear();
		newWf.setForm(f);
		System.out.println();
		System.out.println("current states in clone " + Arrays.asList(wf.getCurrentStates()));
		newWf.setCurrentState(wf.getCurrentStates());
		System.out.println("start state in clone " + wf.getStartState());
		newWf.setStartState(wf.getStartState());
		return newWf;
	}
}