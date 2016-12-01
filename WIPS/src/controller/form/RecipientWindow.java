package controller.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Wips;
import model.user.EndUser;
import model.wips.State;
import model.wips.forms.CoupleForSending;
import model.wips.forms.Form;
import model.wips.intermediates.AbsReq;
import model.wips.intermediates.AndReq;
import model.wips.intermediates.OrReq;

public class RecipientWindow {

	@FXML
	Button sendbutton, backbutton;

	@FXML
	VBox vbox;

	@FXML
	ScrollPane sp;

	private CoupleForSending[] coupleForSending;
	private AbsReq selectedStates = null;

	@FXML
	protected void initialize() {
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Wips wips = Wips.getInstance();
		coupleForSending = new CoupleForSending[wips.getCurrentWorkFlow().getStartState().getAllStartWithMe().size()];
		show();
		// assume the size is 10
		for (int i = 0; i < coupleForSending.length; i++) {

			GridPane gridpane = new GridPane();

			RowConstraints row1 = new RowConstraints();
			row1.setVgrow(Priority.SOMETIMES);

			ColumnConstraints col1 = new ColumnConstraints();
			col1.setPercentWidth(50);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(50);

			gridpane.add(coupleForSending[i].getdisVal(), 0, 1);
			gridpane.add(coupleForSending[i].getfilUser(), 1, 1);

			gridpane.getRowConstraints().addAll(row1);
			gridpane.getColumnConstraints().addAll(col1, col2);

			gridpane.setHgap(10);
			gridpane.setVgap(10);
			gridpane.setPrefHeight(150);
			gridpane.setPadding(new Insets(0, 10, 0, 10));
			vbox.getChildren().add(gridpane);

		}
	}

	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == sendbutton) {
			Wips wips = Wips.getInstance();
			send();
			setCurrents();
			wips.getCurrentuser().setWorkflow(wips.getCurrentWorkFlow());
			Parent l = FXMLLoader.load(getClass().getResource("/view/endUser/ehomescreen.fxml"));
			OpenScreen.openScreen("ehomescreen.fxml", handler, "Home Screen", l, getClass(),
					"/view/endUser/ehomescreen.css");
		//	LogOutController.logInScreen();
		} else if (b == backbutton) {
			Parent l = FXMLLoader.load(getClass().getResource("/view/endUser/eselectstates.fxml"));
			OpenScreen.openScreen("eselectstates.fxml", handler, "Select States", l, getClass(),
					"/view/endUser/eselectstates.css");
		}
	}

	public void show() {
		Wips wips = Wips.getInstance();
		int indexOfNextStates = wips.getIndexOfNextState();
		selectedStates = wips.getCurrentWorkFlow().getCurrentState(wips.getRoleOfCurrentUser()).getAllStartWithMe()
				.get(indexOfNextStates);
		coupleForSending = new CoupleForSending[selectedStates.size()];
		System.out.println("size of selected satees " + selectedStates.size());
		if (selectedStates.size() >= 1 && selectedStates instanceof AndReq) {
			AndReq andSelected = (AndReq) selectedStates;
			for (int i = 0; i < coupleForSending.length; i++) {
				coupleForSending[i] = new CoupleForSending(
						andSelected.getAndTransitions().get(i).getEndState().getDistinctValues());
			}
		} else {
			OrReq orselectedStates = (OrReq) selectedStates;
			coupleForSending[0] = new CoupleForSending(
					orselectedStates.getTransition().getEndState().getDistinctValues());
		}
	}

	public List<EndUser> compileListOfUsers() {
		List<EndUser> endUsers = new ArrayList<>();
		System.out.println("lengt of cople for sending in recpine " + coupleForSending.length);
			for (int i = 0; i < coupleForSending.length; i++) {
				EndUser u = coupleForSending[i].getEndUser();
				if(u != null)
					endUsers.add(u);
		//		System.out.println("end use rin recipent " + coupleForSending[i].getEndUser().getUsername());
			}
		return endUsers;
	}

	public void send() {
		Wips wips = Wips.getInstance();
		State state = wips.getCurrentWorkFlow().getCurrentState(wips.getRoleOfCurrentUser());
		if (state.isAllowedtoSend()) {
			EndUser endUser = (EndUser) Wips.getInstance().getCurrentuser();
			Form form = wips.getCurrentWorkFlow().getForm();
			form.addUser(endUser);
			List<EndUser> compileUsers = compileListOfUsers();
			System.out.println("compile of users " + compileUsers.size());
			System.out.println("\n current user in recepiend window " + endUser);
			if (compileUsers.size() == coupleForSending.length) {
				for (EndUser user : compileListOfUsers()) {
					endUser.send(form, user);
					form.addUser(user);
				}
				form.updateUsers();
				selectedStates.markedSend();
			//	state.
				System.out.println("success for sending the form.....");
			}
		}
	}

	public void setCurrents() {
		State[] s = new State[selectedStates.size()];
		Wips wips = Wips.getInstance();
		if (selectedStates instanceof AndReq) {
			AndReq and = (AndReq) selectedStates;
			for (int i = 0; i < and.size(); i++) {
				s[i] = and.getAndTransitions().get(i).getEndState();
			}
			wips.getCurrentWorkFlow().setCurrentState(s);
			wips.getCurrentWorkFlow().getForm().setWorkFlow(wips.getCurrentWorkFlow());
		}
		if(selectedStates instanceof OrReq) {
			OrReq or = (OrReq) selectedStates;
			s[0] = or.getTransition().getEndState();
			wips.getCurrentWorkFlow().setCurrentState(s);
			wips.getCurrentWorkFlow().getForm().setWorkFlow(wips.getCurrentWorkFlow());
		}
	}
}
