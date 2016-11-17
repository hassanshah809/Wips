package model.wips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Wips;
import model.wips.intermediates.AbsReq;
import model.wips.intermediates.AndReq;
import model.wips.intermediates.OrReq;

public class State implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This variable will contain a unique id for the state, which will be unique in the workflow.
	 */
	private int stateID;
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
	
	private Set<String> distinctVals; 
	
	private List<AbsReq> startWithMe = null;
	private List<AbsReq> endWithMe = null;
	
	/**
	 * This constructor sets the unique id for the state.
	 * @param id int
	 */
	public State(int id, boolean startState, Entity entity) {
		this.stateID = id;
		this.entity = entity;
	}
	
	public void populate() {
		startWithMe = new ArrayList<AbsReq>();
		AbsReq andS = new AndReq();
		endWithMe = new ArrayList<AbsReq>();
		AbsReq andE = new AndReq();
		for(Transition t: Wips.getInstance().getCurrentWorkFlow().getTransition()) {
			if(this.equals(t.getStartState()) && t.getReq()){
				andS.add(t);
			} else {
				AbsReq or = new OrReq(t);
				startWithMe.add(or);
			}
				
			if(this.equals(t.getEndState()) && t.getReq()){
				andE.add(t);
			}else {
				AbsReq or = new OrReq(t);
				endWithMe.add(or);
			}
		}
	}
	/**
	 * This method checks whether the condition for the current state satisfies or not. If it does satisfies 
	 * then it returns true otherwise it returns false.
	 * @return boolean
	 */
	private boolean isAllowedtoSend(){
		if(startWithMe == null && endWithMe == null)
			populate();
		for(AbsReq a: endWithMe){
			if(a.isAllowed())
				return true;
		}
		return false;
	}
	
	/**
	 * sets the start state
	 * @param tf boolean
	 */
	public void setStartState(boolean tf) {
		this.startState = tf;
	}
	
	/**
	 * tells whether a state is start state or not
	 * @return boolean 
	 */
	public boolean isStartState() {
		return startState;
	}
	
	/**
	 * sets the current state
	 * @param tf boolean
	 */
	public void setCurrentState(boolean tf) {
		this.currentState = tf;
	}
	
	/**
	 * checks whether this state is current state or not
	 * @return boolean
	 */
	public boolean isCurrentState() {
		return currentState;
	}
	
	/**
	 * sets the role of the state
	 * @param entity Entity
	 */
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	/**
	 * @return the role of this state
	 */
	public Entity getEntity() {
		return entity;
	}
	
	/**
	 * it sets the condition
	 * @param tf boolean
	 */
	public void setCondition (boolean tf) {
		this.condition = tf;
	}
	
	/**
	 * returns the condition
	 * @return boolean
	 */
	public boolean getCondition () {
		return condition;
	}
	//Maybe List: Transitions list, keeps track
	//of how many transitions are going to this state.
	//2 transitions means 2 approvals to make this state active.
	//Everytime this state is accessed by another state, then increment approval++
	//if #transitions = approvals, then active state 


	/**
	 * @return the stated id
	 */
	public int getID() {
		return stateID;
	}
	
	public void addDistintVals(List<String> vals) {
		for(String s: vals){
			distinctVals.add(s);
		}
	}
	
	public List<AbsReq> getStartWithMe() {
		return startWithMe;
	}
	
	public List<AbsReq> getEndState() {
		return endWithMe;
	}
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof State))
			return false;
		State state = (State) o;
		if(this.getID() == state.getID())
			return true;
		return false;
	}
	@Override
	public String toString() {
		return getEntity().getRole() + " " + getID();
	}
}
