package controller.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Wips;
import model.wips.State;
import model.wips.intermediates.AbsReq;

public class SelectStatesController {

	private ListView<AbsReq> nextStates;
	private ObservableList<AbsReq> nextStatesOb;
	
	public void show() {
		//add to observable list currenstate.getStartWithMe();
		State currenState = Wips.getInstance().getCurrentWorkFlow().getCurrentState();
		currenState.populate();
		nextStatesOb = FXCollections.observableArrayList(currenState.getStartWithMe());
		nextStates.setItems(nextStatesOb);
	}
	
	public void next() {
		//index of currentState.getStartWithMe();
		Wips.getInstance().setIndexOfNextState(nextStates.getSelectionModel().getSelectedIndex()); // get index from observable;
	}
}
