package model.wips.intermediates;

import java.io.Serializable;

import model.wips.Transition;

public class OrReq implements AbsReq, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Transition t;
	
	public OrReq(Transition t) {
		this.t = t;
	}
	@Override
	public boolean isAllowed() {
		return (t.getIsActive() ? true : false);
	}

	@Override
	public void add(Transition t) {
		this.t = t;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Transition getTransition() {
		return t;
	}
	@Override
	public void markedSend() {
		t.setIsActive(true);
	}
	
	@Override
	public String toString() {
		return t.toString();
	}
}
