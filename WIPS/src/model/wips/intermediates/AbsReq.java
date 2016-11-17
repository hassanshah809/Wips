package model.wips.intermediates;

import model.wips.Transition;

public interface AbsReq {
	
	boolean isAllowed();
	void add(Transition t);
	int size();
}
