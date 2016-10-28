package model.user;

import java.util.ArrayList;
import java.util.List;

import model.wips.WorkFlow;

public abstract class User {
	/**
	 * This stores the roles that user plays in the workflow
	 */
	protected List<String> role;
	/**
	 * This is the name of the user
	 */
	protected String name;
	protected String username;
	protected String password;
	
	/**
	* This list will contain all the workflows of that particular user.
	*/
	List<WorkFlow> allworkflows;
	/**
	 * This is the unique id for the user
	 */
	List<String> values;
	private static int id = 0;
	
	/**
	 * This is the constructor that will be call by the concrete classes
	 * @param name String
	 * @param role String
	 */
	public User(String name, List<String> role, String value) {
		this.name = name;
		this.role = role;
		values = new ArrayList<String>();
		values.add(value);
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
	
	public void generateUsername() {
		String[] first = name.split(" ");
		username = first[0]+ id;
	}
	
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
