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
	
	public void show() {
		//add to observable list currenstate.getStartWithMe();
		Wips wips = Wips.getInstance();
		State currenState = wips.getCurrentWorkFlow().getCurrentState(wips.getRoleOfCurrentUser());
		currenState.populate();
		nextStatesOb = FXCollections.observableArrayList(currenState.getAllStartWithMe());
		nextStates.setItems(nextStatesOb);
	}
	
	public void next() {
		//index of currentState.getStartWithMe();
		Wips.getInstance().setIndexOfNextState(nextStates.getSelectionModel().getSelectedIndex()); // get index from observable;
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == backbtn) {
		} else if (b == nextbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/erecipient.fxml"));
			OpenScreen.openScreen("erecipient.fxml", handler, "Recipient Window", e, getClass(),"/view/enduser/erecipient.css");
		} else if (b == logoutbtn) {
		}
	}
}
