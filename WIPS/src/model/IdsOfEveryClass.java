package model;

import java.io.Serializable;

public class IdsOfEveryClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WorkFlowId = 1;
	private int UserId = 1;
	private int formId = 1;
	public int getWorkFlowId() {
		return WorkFlowId++;
	}
	public int getUserId() {
		return UserId++;
	}
	public int getFormId() {
		return formId++;
	}
}
