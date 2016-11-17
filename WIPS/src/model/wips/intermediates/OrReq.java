package model.wips.intermediates;

import model.wips.Transition;

public class OrReq implements AbsReq{

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

}
