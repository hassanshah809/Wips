package model.wips;

import java.util.List;

import model.user.User;
import model.wips.forms.Form;

public class WorkFlow {
	/**
	 * This is the workflows unique id. This will help the system identify this particular workflow. 
	 */
	private static int id;
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
	/**
	 * This will store the form associated with the workflow
	 */
	public Form form;

	private State startState;
	/**
	 *The empty constructor
	 */
	public WorkFlow() {
		
	}

	/**
	 * This is the constructor for the workflow object. It will set the active boolean 
	 * field to true. It will also store all passed through lists in their respective lists.
	 * @param id int
	 * @param states list of States
	 * @param entities list of Entities
	 * @param transition list of Transtions
	 * @param form list of Forms
	 */
	public WorkFlow(int id, List<State> states, List<Entity> entities, List<Transition> transition, Form form) {
		this.id = id;
		this.state = states;
		this.entity = entities;
		this.transition = transition;
		this.form = form;
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

	public State getStartState() {
		return this.startState;
	}
}
