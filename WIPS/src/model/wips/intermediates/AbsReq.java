package model.wips.intermediates;

import java.io.Serializable;

import model.wips.Entity;
import model.wips.Transition;

public interface AbsReq extends Serializable{
	
	boolean isAllowed();
	void add(Transition t);
	int size();
	void markedSend();
	Entity getEntity();
}
