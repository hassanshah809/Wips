package controller.endUser;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Wips;
import model.wips.WorkFlow;

public class AllWorkFlowController {
	/**
	 * This listview will show the list of all workflows that the user can join
	 */
	private ListView<WorkFlow> allWorkFlows;
	private ObservableList<WorkFlow> allWorkFlowsOb;
	
	/**
	 * This method will contain the searchworkflows method. The purpose of 
	 * the filter method is to call searchworkflow method and update the list and listview.
	 */
	public void filter() {
		Wips wips = Wips.getInstance();
		allWorkFlowsOb = FXCollections.observableArrayList();
		for(WorkFlow f: wips.getAllWorkFlows()) {
			if(f.getStartState().getEntity().equals(wips.getRoleOfCurrentUser())) {
				allWorkFlowsOb.add(f);
			}
		}
		allWorkFlows.setItems(allWorkFlowsOb);
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
}
