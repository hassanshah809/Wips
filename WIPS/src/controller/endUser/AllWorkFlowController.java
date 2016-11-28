package controller.endUser;

import java.util.ArrayList;
import java.util.List;

import model.Wips;
import model.wips.WorkFlow;

public class AllWorkFlowController {
	/**
	 * This listview will show the list of all workflows that the user can join
	 */
	private List<WorkFlow> allWorkFlows;
	
	public AllWorkFlowController() {
		allWorkFlows = new ArrayList<WorkFlow>();
	}
	/**
	 * This method will contain the searchworkflows method. The purpose of 
	 * the filter method is to call searchworkflow method and update the list and listview.
	 */
	private void filter() {
		Wips wips = Wips.getInstance();
		for(WorkFlow f: wips.getAllWorkFlows()) {
			System.out.println("in all wf controller " + f.getStartState());
			if(f.getStartState().getEntity().equals(wips.getRoleOfCurrentUser())) {
				allWorkFlows.add(f);
			}
		}
	}
	
	/**
	 * This method will update the listview with the workflows the user can join
	 */
	public List<WorkFlow> getAllWorkFlowsCanJoin() {
		if(allWorkFlows.size() == 0)
			filter();
		return allWorkFlows;
	}
}
