package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.user.EndUser;
import model.user.User;
import model.wips.WorkFlow;
import model.wips.forms.Form;

public class Wips {
	private static final String storeUser = "data";
	
	private static Wips wips = null;
	/**
	 * This list will have all the workflows that ever created.
	 */
	private List<WorkFlow> workflow = new ArrayList<>();
	/**
	 * This list will have all the users that ever created.
	 */
	
	public List<Form> forms; 
	
	private List<User> users;
	/**
	 * Current logged in user will be set to this variable.
	 */
	public User currentUser;
	/**
	* Current workflow the user is working on.
	**/
	private WorkFlow currentWorkflow;
	private int indexOfNextState = -1;
	
	private Wips() {}
	public List<EndUser> getEndUser() {
		List<EndUser> endusers = new ArrayList<EndUser>();  
		for(User u: users){
			if(u instanceof EndUser)
				endusers.add((EndUser)u);
		}
		return endusers;
	}
	
	public void addUser(User user) {
		if(!users.contains(user))
			users.add(user);
	}
	
	public List<WorkFlow> getAllWorkFlows() {
		return this.workflow; 
	}
	
	public void addWorkFlow(WorkFlow f) {
		workflow.add(f);
	}
	public  List<User> getUsers() {
		return users; 
	}
	
	public List<Form> getForms() {
		return forms; 
	}
	
	public void setCurrentWorkFlow(WorkFlow f) {
		this.currentWorkflow = f;
	}
	
	public WorkFlow getCurrentWorkFlow() {
		return currentWorkflow;
	}
	
	public void setIndexOfNextState(int index) {
		indexOfNextState = index;
	}
	
	public int getIndexOfNextState() {
		return indexOfNextState;
	}
	
	public static Wips getInstance() {
		if(wips == null)
			wips = new Wips();
		return wips;
	}

	public void make () throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeUser + File.separator + "wips"));
		oos.writeObject(this);
		oos.close();
	}

	public static Wips remake()throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/wips"));
		 wips = (Wips)ois.readObject();
		 ois.close();
		 return wips; 
	}
}
