package model.wips.intermediates;

import java.util.ArrayList;
import java.util.List;

import model.wips.Transition;

public class AndReq implements AbsReq {

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
			sb.append(t+ " ");
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
}
