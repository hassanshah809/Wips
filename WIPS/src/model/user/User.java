package model.user;

import java.util.ArrayList;
import java.util.List;

import model.wips.Entity;
import model.wips.WorkFlow;

public abstract class User {
	/**
	 * This stores the roles that user plays in the workflow
	 */
	protected List<Entity> roles;
	/**
	 * This is the name of the user
	 */
	protected String name;
	/**
	 * username of this user
	 */
	protected String username;
	/**
	 * password of this user
	 */
	protected String password;
	
	/**
	* This list will contain all the workflows of that particular user.
	*/
	List<WorkFlow> allworkflows;
	/**
	 * This is the unique id for the user
	 */
	List<String> values;
	/**
	 * unique identifier for each users
	 */
	private static int id = 0;
	
	/**
	 * This is the constructor that will be call by the concrete classes
	 * @param name String
	 * @param roles list of Entity
	 * @param value list of String
	 */
	public User(String name, List<Entity> roles, List<String> value) {
		this.name = name;
		this.roles = roles;
		values = new ArrayList<String>();
		values.addAll(value);
		generateUsername();
		generatePassword();
		id++;
	}
	
	/**
	 * This method simply returns the id of the user
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	* This method will add the workflow to list of all workflows
	*/
	public void setWorkflow(WorkFlow workflow){
		allworkflows.add(workflow);
	}

	/**
	* This method will return all the workflows of the current user
	*/
	public List<WorkFlow> getAllWorkflows(){
		return allworkflows;
	}
	
	/**
	 * it generates a alphanumeric string to be the username
	 */
	public void generateUsername() {
		String[] first = name.split(" ");
		username = first[0]+ id;
	}
	
	/**
	 * it generates a random alphanumeric string as password
	 */
	public void generatePassword() {
		String[] first = name.split(" ");
		int len = first[0].length();
		char[] name = first[0].toCharArray();
		StringBuilder build = new StringBuilder();
		for(int i = 0; i < len; i++) {
			int ran = (int) (Math.random()*len);
			build.append(name[ran]);
		}
		build.append(Math.random()*1000);
		password = build.toString();
	}

}
