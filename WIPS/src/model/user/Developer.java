package model.user;

import java.util.List;

import model.wips.WorkFlow;

public class Developer extends User {
	/**
	* Creates the developer object
	*/
	public Developer(String name, List<String> role, String val) {
		super(name, role, val);
		// TODO Auto-generated constructor stub
	}	
	
	/**
	* It deletes a specific workflow based the index provided
	*/
	public void deleteWorkFlow(int index) {
		
	}
	
	public List<WorkFlow> getWorkFlows(){
		return getAllWorkflows();
}

}
