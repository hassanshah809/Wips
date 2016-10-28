package model.wips;

public class State {

	/**
	 * This variable will contain a unique id for the state, which will be unique in the workflow.
	 */
	private static int stateID;
	/**
	 * This will be set to true for the start state, otherwise it will be set to false. Therefore, 
	 * no other start will be a start state except the first state.
	 */
	private boolean startState;
	/**
	 * This boolean will be set to true if the workflow is in this current state. Otherwise it will be false.
	 */
	private boolean currentState;
	/**
	 * This variable stores the entity in which the state resides in. 
	 */
	private Entity entity;
	/**
	 * If the condition of the state is satisfied then it moves to the next state by setting the condition value to true.
	 */
	private boolean condition = false;	
	
	/**
	 * This constructor sets the unique id for the state.
	 * @param id int
	 */
	public State(int id, boolean startState, Entity entity) {
		this.stateID = id;
	}
	
	
	/**
	 * This method checks whether the condition for the current state satisfies or not. If it does satisfies 
	 * then it returns true otherwise it returns false.
	 * @return boolean
	 */
	private boolean isAllowed(){
		if(currentState) {
			return true;
		} else {
		return false;
		}
	}
	
	public void setStartState(boolean tf) {
		this.startState = tf;
	}
	
	public boolean getStartState() {
		return startState;
	}
	
	public void setCurrentState(boolean tf) {
		this.currentState = tf;
	}
	
	public boolean getcurrentState() {
		return currentState;
	}
	
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setCondition (boolean tf) {
		this.condition = tf;
	}
	
	public boolean getCondition () {
		return condition;
	}
	//Maybe List: Transitions list, keeps track
	//of how many transitions are going to this state.
	//2 transitions means 2 approvals to make this state active.
	//Everytime this state is accessed by another state, then increment approval++
	//if #transitions = approvals, then active state 
}
