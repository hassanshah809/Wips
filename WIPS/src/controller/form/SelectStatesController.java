package controller.form;


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
import model.Wips;
import model.wips.State;
import model.wips.intermediates.AbsReq;

public class SelectStatesController {

	@FXML
	Button backbtn, nextbtn, logoutbtn;
	
	@FXML
	ListView<AbsReq> listview;
	
	private ListView<AbsReq> nextStates;
	private ObservableList<AbsReq> nextStatesOb;
	
	@FXML
	protected void initialize() {
		show();
	}
	
	public void show() {
		//add to observable list currenstate.getStartWithMe();
		Wips wips = Wips.getInstance();
		State[] s = {wips.getCurrentWorkFlow().getStartState()};
		wips.getCurrentWorkFlow().setCurrentState(s);
		State currenState = wips.getCurrentWorkFlow().getCurrentState(wips.getRoleOfCurrentUser());
		currenState.populate();
		nextStatesOb = FXCollections.observableArrayList(wips.getCurrentWorkFlow().getStartState().getAllStartWithMe());
		listview.setItems(nextStatesOb);
	}
	
	public void next() {
		//index of currentState.getStartWithMe();
		Wips.getInstance().setIndexOfNextState(listview.getSelectionModel().getSelectedIndex()); // get index from observable;
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == backbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eformgen.fxml"));
			OpenScreen.openScreen("eformgen.fxml", handler, "Form", e, getClass(),"/view/enduser/eformgen.css");
		} else if (b == nextbtn) {
			next();
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/erecipient.fxml"));
			OpenScreen.openScreen("erecipient.fxml", handler, "Recipient Window", e, getClass(),"/view/enduser/erecipient.css");
		} else if (b == logoutbtn) {
		}
	}
}
