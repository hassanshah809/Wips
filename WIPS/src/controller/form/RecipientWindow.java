package controller.form;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Wips;
import model.user.EndUser;
import model.wips.forms.CoupleForSending;
import model.wips.forms.Form;
import model.wips.intermediates.AbsReq;
import model.wips.intermediates.AndReq;
import model.wips.intermediates.OrReq;

public class RecipientWindow {

	@FXML
	VBox vbox;
	
	@FXML
	ScrollPane sp;
	
	private CoupleForSending[] coupleForSending;
	private AbsReq selectedStates = null;

	@FXML
	protected void initialize() {
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		//assume the size is 10
		for (int i = 0; i < 10; i++) {
			CoupleForSending cs = new CoupleForSending();
			GridPane gridpane = new GridPane();
			
			RowConstraints row1 = new RowConstraints();
	 	    row1.setVgrow(Priority.SOMETIMES);
	 	        
	 	    ColumnConstraints col1 = new ColumnConstraints();
	 	    col1.setPercentWidth(50);
	 	    ColumnConstraints col2 = new ColumnConstraints();
	 	    col2.setPercentWidth(50);
	 	     
	 	    gridpane.add(cs.getdisVal(), 0, 1); 
		    gridpane.add(cs.getfilUser(), 1, 1);
		    
		    gridpane.getRowConstraints().addAll(row1);
 	        gridpane.getColumnConstraints().addAll(col1,col2);
 	        
 	        gridpane.setHgap(10);
			gridpane.setVgap(10);
			gridpane.setPrefHeight(150);
			gridpane.setPadding(new Insets(0, 10, 0, 10));
			vbox.getChildren().add(gridpane);
			
		}
	}
	
	public void show() {
		Wips wips = Wips.getInstance();
		int indexOfNextStates = wips.getIndexOfNextState();
		selectedStates = wips.getCurrentWorkFlow().getCurrentState().getStartWithMe().get(indexOfNextStates);
		coupleForSending = new CoupleForSending[selectedStates.size()];

		if (selectedStates.size() > 1) {
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
		if (selectedStates != null) {
			for (int i = 0; i < coupleForSending.length; i++) {
				endUsers.add(coupleForSending[i].getEndUser());
			}
		}
		return endUsers;
	}

	public void send() {
		if (Wips.getInstance().getCurrentWorkFlow().getCurrentState().isAllowedtoSend()) {
			EndUser endUser = (EndUser) Wips.getInstance().getCurrentuser();
			Form form = new Form("dummy");
			for (EndUser user : compileListOfUsers()) {
				endUser.send(form, user);
				form.addUser(user);
			}
			form.updateUsers();
			selectedStates.markedSend();
		}
	}

	/**
	 * This method will return the user to the window for the FormController
	 * without making any changes to the system.
	 */
	public void cancel() {
		// Parent p = FXMLLoader.load(getClass().getResource("Path to the view
		// FXML file"));
		// View Package will have a class called openSCreen and it will have a
		// method called "openScreen"
		// It will take 3 parameters openScreen(String fxmlfile, ActionEvent e,
		// String title, Parent p);
	}
}
