package model.wips.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Wips;
import model.user.EndUser;
import model.wips.Entity;
import model.wips.WorkFlow;

public class Form implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This list contains all couples that form is made out of
	 */
	private List<Couple> couples;
	/**
	* This List contains the message from all entity
	*/
	private List<String> message;
	/**
	 * This stores the name of the form
	 */
	private String formName;
	/**
	 * This is a unique identifier for the fom
	 */
	private int formID;
	
	private List<Entity> roles;
	private List<EndUser> users;
	private WorkFlow wf;
	
	
	/**
	 * This a constructor to make the new form object
	 */
	public Form(String formname, WorkFlow wf) {
		this.formName = formname;
		couples = new ArrayList<Couple>();
		message = new ArrayList<String>();
		roles = new ArrayList<Entity>();
		users = new ArrayList<EndUser>();
		this.wf = wf;
		formID = Wips.getInstance().getIdsOfEveryClass().getFormId();
		// TODO Auto-generated constructor stub
	}
	
	public WorkFlow getFormWorkFlow() {
		return this.wf;
	}
	/**
	* This method will add message to the message list
	*/
	public void addMessage(String message) {
		this.message.add(message);
	}
	
	public void addCouple(List<Couple> c){
		for(Couple p: c) {
			couples.add(p);	
		}
	}
	
	public List<Couple> getCouples() {
		return couples;
	}

	public void addRoles(Entity role) {
		roles.add(role);
	}
	
	public void addUser(EndUser user) {
		users.add(user);
	}
	
	public void updateUsers() {
		for(EndUser user: users)
			user.update();
	}
	public String getFormName() {
		return formName;
	}

	public boolean isAllowed() {
		for(Couple c: couples) {
			if(!c.isAllowed()) 
				return false;
		}
		return true;
	}
	
	public int getFormId() {
		return formID;
	}
}
