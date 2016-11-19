package model.wips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.user.EndUser;
import model.user.User;
import model.wips.forms.Form;

public class WorkFlow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This is the workflows unique id. This will help the system identify this particular workflow. 
	 */
	private int id;
	/**
	 * This is the field which informs the system that this workflow is active. True if active. False if not active.
	 */
	private boolean isActive;
	/**
	 *  This list stores all states associated with the workflow.
	 */
	private List<State> state;
	/**
	 * This list stores all transitions associated with the workflow.
	 */
	private List<Transition> transition;
	/**
	 * This list stores all entities associated with the workflow.
	 */
	private List<Entity> entity;
	
	private List<EndUser> users;
	/**
	 * This will store the form associated with the workflow
	 */
	public Form form;

	/**
	 * this stores the start 
	 */
	private State startState;
	private State currentState;
	/**
	 *The empty constructor
	 */
	public WorkFlow() {
		id++;;
		this.state = new ArrayList<State>();
		this.entity = new ArrayList<Entity>();
		this.transition = new ArrayList<Transition>();
		this.users = new ArrayList<EndUser>();
	}

	/**
	 * This is the constructor for the workflow object. It will set the active boolean 
	 * field to true. It will also store all passed through lists in their respective lists.
	 * @param id int
	 * @param states list of States
	 * @param entities list of Entities
	 * @param transition list of Transtions
	 * @param form  Form
	 */
	public WorkFlow(List<State> states, List<Entity> entities, List<Transition> transition) {
		id++;
		this.state = states;
		this.entity = entities;
		this.transition = transition;
		this.form = form;
		this.users = new ArrayList<EndUser>();
	}
	
	/**
	 * Changes the active boolean value from true to false, telling the system that this workflow is inactive
	 * @param b boolean
	 */
	public void setActive(boolean b) {
		this.isActive = b;
	}

	/**
	 * Returns the states list.
	 * @return list of states
	 */
	public List<State> getState() {
		return state;
	}

	/**
	 * Returns the transition list
	 * @return list of transitions
	 */
	public List<Transition> getTransition() {
		return transition;
	}

	/**
	 * Returns the entities list.
	 * @return list of entities
	 */
	public List<Entity> getEntity() {
		return entity;
	}

	/**
	 * Returns the forms.
	 * @return forms
	 */
	public Form getForm() {
		return form;
	}

	/**
	 * @return the start state
	 */
	public State getStartState() {
		return this.startState;
	}
	
	/**
	 * checks if given entity belongs to this workflow
	 * @param e Entity
	 * @return boolean
	 */
	public boolean hasRole(Entity e) {
		if(entity.contains(e))
			return true;
		return false;
	}
	
	public void setForm(Form form) {
		this.form = form;
	}
	
	public void addUser(List<User> user) {
		for(User u: user){
			if(u instanceof EndUser)
				users.add((EndUser)u);
		}
	}
	
	public List<EndUser> getUsers() {
		return users;
	}
	
	public int getID() {
		return id;
	}
	
	public void setCurrentState(State s) {
		this.currentState = s;
	}
	
	public State getCurrentState() {
		return this.currentState;
	}
}
