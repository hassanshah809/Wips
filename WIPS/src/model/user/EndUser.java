package model.user;

import java.util.ArrayList;
import java.util.List;

import model.wips.Entity;
import model.wips.WorkFlow;
import model.wips.forms.Form;

public class EndUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * List of forms user has sent
	 */
	private List<Form> sent;
	/**
	 * List of form end user received
	 */
	private List<WorkFlow> received;

	private int lastSizeOfStack = 0;
	int numOfUdates = 0;
	int numberOfNewNotifications = 0;

	/**
	 * creates the new enduser
	 * 
	 * @param name
	 *            String
	 * @param role
	 *            List of roles
	 * @param val
	 *            list of string as values
	 */
	public EndUser(String name, List<Entity> role, List<String> val) {
		// TODO Auto-generated constructor stub
		super(name, role, val);
		sent = new ArrayList<Form>();
		received = new ArrayList<WorkFlow>();
	}

	public EndUser(String username, boolean b) {
		super(username, b);
		received = new ArrayList<WorkFlow>();
	}

	/**
	 * This method is responsible for sending the form to next state
	 * 
	 * @param form
	 *            Form
	 * @param to
	 *            EndUser who recieves the form
	 */
	public void send(Form form, EndUser to) {
		to.recieve(form);
		sent.add(form);
	}

	/**
	 * This receives the form sent by the users of previous states
	 */
	public void recieve(Form form) {
		if (received.contains(form.getFormWorkFlow())) {
			int index = received.indexOf(form.getFormWorkFlow());
			received.remove(index);
			received.add(form.getFormWorkFlow());
		} else {
			received.add(form.getFormWorkFlow());
		}
	}

	public List<WorkFlow> getRecievedForm() {
		return this.received;
	}

	/**
	 * This methods checks if the current state is satisfied
	 * 
	 * @return boolean
	 */
	public boolean checkState() {
		return false;
	}

	/**
	 * It will instantiate the workflow when the user sends the form
	 * 
	 * @return WorkFlow
	 */
	public WorkFlow instantiate() {
		return new WorkFlow();
	}

	/**
	 * Returns the list of joined workflows
	 */
	public List<WorkFlow> getJoinedWorkflows(Entity role) {
		return allworkflows;
	}

	/**
	 * This will end the workflow process making the workflow “inactive”
	 */
	private void end() {

	}

	public void update() {
		numOfUdates = 0;
		numberOfNewNotifications = 0;
		for (WorkFlow f : allworkflows) {
			if (f.getHasUpdate(this))
				numOfUdates++;
		}
		for (WorkFlow s : received) {
			if (s.getHasUpdate(this))
				numberOfNewNotifications++;
		}
	}

	public int getNumOfUpdates() {
		return numOfUdates;
	}

	public int getNumOfNotif() {
		return numberOfNewNotifications;
	}

	/**
	 * compares the given value to the user's value list
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public boolean checkValue(String value) {
		if (values.contains(value))
			return true;
		return false;
	}

	/**
	 * @return list of entity. all the roles that user plays
	 */
	public List<Entity> getRoles() {
		return roles;
	}

}
