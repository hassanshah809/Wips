package model.wips.forms;

import java.io.Serializable;

import javafx.scene.control.TextArea;

public class Couple implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This will be a unique id for the couple object
	 */
	private int coupleId;
	
	String heading;
	boolean isrequired;
	boolean userField;
	private transient TextArea content;
	String contentOfTextArea;
	
	/**
	 * This is the constructor that creates the new couple object
	 */
	public Couple(String heading, boolean isrequired, boolean userField) {
		this.heading = heading;
		this.isrequired = isrequired;
		this.userField = userField;
		content = new TextArea();
		//coupleId++;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public boolean isRequired() {
		return isrequired;
	}

	public void setIsrequired(boolean isrequired) {
		this.isrequired = isrequired;
	}

	public boolean isUserField() {
		return userField;
	}

	public void setUserField(boolean userField) {
		this.userField = userField;
	}
	
	public boolean isAllowed() {
		if(isRequired() && !content.getText().isEmpty()) {
			contentOfTextArea = content.getText();
			return true;
		}
		if(!isRequired()) {
			contentOfTextArea = content.getText();
			return true;
		}
		return false;
	}
}
