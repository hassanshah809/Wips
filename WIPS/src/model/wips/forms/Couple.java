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
	Label l;
	TextField tf;
	/**
	 * It will only contain two columns
	 */
	private GridLayout gridview;
	
	/**
	 * This is the constructor that creates the new couple object
	 */
	public Couple(Label	l, TextField tf) {
		this.l = l;
		this.tf = tf;
		coupleId++;
	}
//	/**
//	 * This method returns the currently selected field in the couple object
//	 * @return Field
//	 */
//	public Field getField(int i) {
//		return fields[i];
//	}
	
//	/**
//	 * This will set the value of the specific field.  For example, if that field is combo box then it will set its content via this method
//	 * @param strings String
//	 */
//	public void setField(String ...strings ) {
//		//maybe
//	}
	
//	public void addField(Field f, int i) {
//		fields[i] = f;
//	}
}
