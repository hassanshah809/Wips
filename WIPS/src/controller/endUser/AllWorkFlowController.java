package controller.endUser;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ListView;
import model.wips.WorkFlow;

public class AllWorkFlowController {
	/**
	 * This listview will show the list of all workflows that the user can join
	 */
	ListView<WorkFlow> allWorkFlows;
	
	/**
	 * This method will contain the searchworkflows method. The purpose of 
	 * the filter method is to call searchworkflow method and update the list and listview.
	 */
	public void filter() {
		searchWorkFlows(); // set it to listview
	}
	
	/**
	 * This method will update the listview with the workflows the user can join
	 */
	public void updateListView() {
		
	}
	
	/**
	 * The user will be able to select a workflow from the list of workflows to 
	 * instantiate, and depending on the selection , the listview will return 
	 * the index of the particular workflow. The create method will use the index of the listview to create an instance of that particular workflow.
	 * @param index int
	 */
	public void create(int index) {
		
	}
	
	/**
	 * This method compares the role of the current user will the roles that are set in all 
	 * workflows, this way we can show the workflows that only relates to him/her depending 
	 * on the role. After it compares the role, this method will return the list of the Workflow.
	 * @return list of workflows
	 */
	public List<WorkFlow> searchWorkFlows() {
		return new ArrayList<WorkFlow>();
	}
}
