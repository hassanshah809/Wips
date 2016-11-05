package model.wips;

import java.io.Serializable;

public class Transition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This field will store the origin of the transition 
	 */
	State startState;
	/**
	 * This field will store the destination of the transition 
	 */
	State endState;
	
	/**
	 * This is the constructor of the Transition module. It will take two arguments, 
	 * which will be the start state and the end state for the transition.
	 * @param startState State
	 * @param endState State
	 */
	public Transition(State startState, State endState) {
		this.startState = startState;
		this.endState = endState;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Transition))
			return false;
		Transition transition = (Transition) o;
		if(this.startState.equals(transition.startState)&& this.endState.equals(transition.endState))
			return true;
		return false;
	}
}
