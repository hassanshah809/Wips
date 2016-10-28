package model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.Wips;
import model.wips.WorkFlow;
import model.wips.forms.Form;

public class EndUser extends User{

	/**
	* List of forms user has sent
	*/
	List<Form> sent;
	/**
	* List of form end user received
	*/
	Stack<Form> received;

	/**
	* It contains the list of all workflows that user has joined
	*/
	List<WorkFlow> joinedWorkflows;
	/**
	 * creates the new enduser
	 * @param name String 
	 * @param role List of roles
	 */
	public EndUser(String name, List<String> role, String val) {
		// TODO Auto-generated constructor stub
		super(name, role, val);
		sent = new ArrayList<Form>();
	}
	
	/**
	 * This method is responsible for sending the form to next state
	 * @param form Form
	 */
	public void send(Form form, EndUser to) {
		to.recieve(form);
		sent.add(form);
	}
	
	/**
	 * This receives the form sent by the users of previous states
	 */
	public void recieve(Form form) {
		received.push(form);
	}
	
	/**
	 * This methods checks if the current state is satisfied
	 * @return String
	 */
	public String checkState() {
		return "";
	}
	
	/**
	 * It will instantiate the workflow when the user sends the form
	 * @return WorkFlow
	 */
	public WorkFlow instantiate() {
		return new WorkFlow();
	}
	
	/**
	 * This will find all the workflows that a user can join based on their roles
	 */
	public void findWorkFlow() {
		
		WorkFlow extracted_wf;
		
		for(int i = 0; i< Wips.workflow.size(); i++) {
			extracted_wf = Wips.workflow.get(i);
			
			if(this.role.contains(extracted_wf.getStartState().getEntity().getRole())) {
				this.joinedWorkflows.add(extracted_wf);
			}
		}
	}

	/**
	* Returns the list of joined workflows
	*/
	public List<WorkFlow> getJoinedWorkflows(){
		return joinedWorkflows;
	}
	
	/**
	 * This will end the workflow process making the workflow “inactive”
	 */
	private void end() {
		
	}
	
	public boolean checkValue(String value) {
		if(values.contains(value))
			return true;
		return false;
	}

}
