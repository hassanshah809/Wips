package model.wips.forms;

import java.io.Serializable;
import java.util.List;

import model.user.EndUser;
import model.wips.Entity;

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
	
	/**
	* This method will add message to the message list
	*/
	public void addMessage(String message) {
		this.message.add(message);
	}

	/**
	 * This a constructor to make the new form object
	 */
	public Form(String formname) {
		this.formName = formname;
		formID++;
		// TODO Auto-generated constructor stub
	}
	
	public void addCouple(Couple c){
		couples.add(c);
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

}
