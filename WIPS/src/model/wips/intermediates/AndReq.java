package model.wips.intermediates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.wips.Entity;
import model.wips.Transition;

public class AndReq implements AbsReq, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Transition> and;
	
	public AndReq() {
		and = new ArrayList<Transition>();
	}
	
	public void add(Transition t) {
		and.add(t);
	}

	@Override
	public boolean isAllowed() {
		for(Transition t: and) {
			if(!t.getIsActive())
				return false;
		}
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Transition t: and)
			sb.append(t+ ",\n");
		return sb.toString();
	}

	@Override
	public int size() {
		return and.size();
	}
	
	public List<Transition> getAndTransitions() {
		return and;
	}

	@Override
	public void markedSend() {
		for(Transition t: and)
			t.setIsActive(true);
	}

	@Override
	public Entity getEntity() {
		Entity e = null;
		if(and.size() > 0)
			e = and.get(0).getEndState().getEntity();
		return e;
	}
}
