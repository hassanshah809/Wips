package controller.developer;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Wips;
import model.user.Developer;
import model.user.EndUser;
import model.wips.WorkFlow;

public class AdminHomeController {
	
	ListView<WorkFlow> list = new ListView<WorkFlow>();
	ObservableList<WorkFlow> items = FXCollections.observableArrayList();
	
	/**
	 * Running this method will redirect the workflow application developer to the window where 
	 * he or she is able to create the workflow.
	 */
	public void createWorkFlow() {
		//This method will open 
	}
	
	/**
	 * Running this method will redirect the workflow application developer to the window where
	 * the workflow application.
	 */
	public void showFinished() {
		// show all workflows that has been created to list
		CreatedWorkflowController cw = new CreatedWorkflowController();
		items = FXCollections.observableArrayList(cw.getCreatedWorkflow());
		list.setItems(items);
	}
}
