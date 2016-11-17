package controller.form;

import model.Wips;

public class SelectStatesController {

	public void show() {
		//add to observable list currenstate.getStartWithMe();
	}
	
	public void next() {
		//index of currentState.getStartWithMe();
		Wips.getInstance().setIndexOfNextState(-1); // get index from observable;
	}
}
