package controller.developer;

import java.util.List;

import errors.AbsError;
import javafx.scene.control.ListView;
import model.wips.State;

public class StatePermissionController {
	/**
	 * This is the object which will display all states that are associated with 
	 * this workflow. Each state listed in this view will be associated with the 
	 * state’s unique id.
	 */
	ListView<State> states;
	/**
	 * This is list of states that will help the listview
	 */
	List<State> state;
	/**
	 * This will store the user inputted value for required value. 
	 */
	int requiredValue;
	/**
	 * This will hold the stateID of the state chosen.  
	 */
	int stateId;
	/**
	 * This object will handle all the errors
	 */
	AbsError e;
	
	/**
	 * This method will get all the transitions associated with the chosen state 
	 * and update the label’s value. This method will access the TransitionIntermediateModel 
	 * class and iterate through the list to find all transitions who have a destination 
	 * state which matches the chosen state.
	 */
	public void getTransitions() {
		//gets all the transitions of each state
	}
	
	/**
	 * Displays in the window all information associated with the chosen state. 
	 */
	public void displayInfo() {
		//number of transition coming in
	}
	
	/**
	 * This updates the required value field in the chosen State object
	 */
	public void updateReq() {
		//update the required field in each state
	}
	
	/**
	 * Checks the if the inputted value is less than or equal to the number of transitions 
	 * which lead to that state
	 */
	public void check() {
		//handles errors
	}

	/**
	 * This method will be called when theres user errors
	 */
	public void getError(){	
	}
}
