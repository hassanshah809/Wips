package controller.developer;

import java.io.IOException;

import helper.OpenScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import model.Wips;
import model.user.EndUser;
import model.wips.Entity;
import model.wips.State;

public class EditWorkflowController {

	@FXML
	Button addnewuser, backbtn, logoutbtn;

	@FXML
	ListView<State> allstates;
	@FXML
	ListView<EndUser> allusers;

	@FXML
	TextArea distinctstatevalues, userroles, uservalues;

	@FXML
	TabPane tabpane;

	@FXML
	Tab workflowtab, usertab;

	private ObservableList<State> allstatesOb;
	private ObservableList<EndUser> allusersOb;

	@FXML
	protected void initialize() {
		tabListeners();
	}

	public EditWorkflowController() {
		// TODO Auto-generated constructor stub
	}

	public void tabListeners() {
		tabpane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			if (newTab.equals(workflowtab)) {
				showStates();
			} else if (newTab.equals(usertab)) {
				showUsers();
			}
		});
	}

	public void showUsers() {
		allusersOb = FXCollections.observableArrayList(Wips.getInstance().getEndUser());
		allusers.setItems(allusersOb);
		allusers.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			if (oldVal != null) {
				oldVal.addTempRoles(userroles.getText());
				oldVal.addTempVals(uservalues.getText());
			}
			showRoles(newVal);
			showValues(newVal);
		});
	}

	public void showRoles(EndUser user) {
		StringBuilder sb = new StringBuilder();
		if (user != null) {
			for (Entity e : user.getRoles()) {
				sb.append(e.toString() + ",");
			}
			if (user.getTempRoles().size() != 0) {
				userroles.setText(user.getTempRolesAsString());
			} else {
				userroles.setText(sb.toString());
			}
		}

	}

	public void showValues(EndUser user) {
		StringBuilder sb = new StringBuilder();
		if (user != null) {
			for (String s : user.getVals()) {
				sb.append(s);
				sb.append(", ");
			}
			if (user.getTempVals().size() != 0) {
				uservalues.setText(user.getTempValsAsString());
			} else {
				uservalues.setText(sb.toString());
			}
		}
	}

	public void showStates() {
		allstatesOb = FXCollections.observableArrayList(Wips.getInstance().getCurrentWorkFlow().getState());
		allstates.setItems(allstatesOb);
		allstates.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			if (oldVal != null) {
				oldVal.addTempDistinctVals(distinctstatevalues.getText());
			}
			distinctVals(newVal);

		});
	}

	public void distinctVals(State state) {
		StringBuilder sb = new StringBuilder();
		if (state != null) {
			for (String dVal : state.getDistinctValues()) {
				sb.append(dVal);
				sb.append(", ");
			}
			if (state.getTempDistVals().size() != 0) {
				distinctstatevalues.setText(state.getTempDistValsAsString());
			} else {
				distinctstatevalues.setText(sb.toString());
			}
		}
	}

	public void confirm() {
		for (State s : Wips.getInstance().getCurrentWorkFlow().getState()) {
			s.finalizeDistinctVals();
		}
		
		for(EndUser user: Wips.getInstance().getEndUser()){
			user.finalizeUsers();
		}
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == addnewuser) {

		} else if (b == backbtn) {
			confirm();
			Parent l = FXMLLoader.load(getClass().getResource("/view/developer/dhomescreen.fxml"));
			OpenScreen.openScreen("dhomescreen.fxml", handler, "Developer", l, getClass(),
					"/view/developer/dhomescreen.css");
		} else if (b == logoutbtn) {
			Parent l = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", l, getClass(), "/view/session/application.css");
		}
	}

}
