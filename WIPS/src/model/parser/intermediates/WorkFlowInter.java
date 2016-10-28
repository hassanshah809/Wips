package model.parser.intermediates;

import java.util.List;

public class WorkFlowInter<T, S> extends Intermediate<T> { // T = entity, S = state
	/**
	 * This will be the list that stores all of the State objects which have been passed from the WorkFlowParser class
	 */
	private List<S> tempStates;
	
	/**
	 * it adds the states to the temporary states list
	 * @param s typs S
	 */
	public boolean addStates(S s) {
		if(!tempStates.contains(s)){
			tempStates.add(s);
			return true;
		}
		return false;
	}

	/**
	 * This will return the list of states stored in this class
	 * @return list of states
	 */
	public List<S> getTempStates() {
		return tempStates;
	}
}
