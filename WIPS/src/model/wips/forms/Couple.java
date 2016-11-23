package model.wips.forms;

import java.awt.GridLayout;
import java.io.Serializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.wips.forms.fields.Field;
import model.wips.forms.fields.Text;

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
	
	/**
	 * This is the constructor that creates the new couple object
	 */
	public Couple(String heading, boolean isrequired, boolean userField) {
		this.heading = heading;
		this.isrequired = isrequired;
		this.userField = userField;
		//coupleId++;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public boolean isIsrequired() {
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
	
	
}
