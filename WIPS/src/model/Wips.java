package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.user.EndUser;
import model.user.User;
import model.wips.WorkFlow;
import model.wips.forms.Form;

public class Wips {
	/**
	 * This list will have all the workflows that ever created.
	 */
	public static List<WorkFlow> workflow;
	/**
	 * This list will have all the users that ever created.
	 */
	
	public static List<Form> forms; 
	
	private Set<User> users;
	/**
	 * Current logged in user will be set to this variable.
	 */
	public static User currentUser;
	/**
	* Current workflow the user is working on.
	**/
	public static WorkFlow currentWorkflow;
	/**
	 * Current logged in user will be set to this variable.
	 * @param sa String array
	 */
	public static void main(String[] sa) {
		
	}
	
	public List<EndUser> getEndUser() {
		List<EndUser> endusers = new ArrayList<EndUser>();  
		for(User u: users){
			if(u instanceof EndUser)
				endusers.add((EndUser)u);
		}
		return endusers;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public List<WorkFlow> getAllWorkFlows() {
		return this.workflow; 
	}
	
	public  Set<User> getUsers() {
		return users; 
	}
	
	public List<Form> getForms() {
		return forms; 
	}
}
