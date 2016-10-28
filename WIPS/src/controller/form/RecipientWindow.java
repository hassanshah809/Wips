package controller.form;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Wips;
import model.user.EndUser;

public class RecipientWindow {

	/**
	* This list should contain all users which the form could be sent to.
	*/
	List<EndUser> allValidUsers;

	/**
	* This is the listview which will be displayed on the UI which will display all valid users the form 
	* could be sent to. 
	*/
	ListView<EndUser> userDisplay;
	
	ListView<String> distinctValues;
	
	//dummy list
	List<String> l = new ArrayList<>();
	
	public ObservableList<String> distinctVal = FXCollections.observableList(l);

	public ObservableList<EndUser> filteredUsers = FXCollections.observableList(allValidUsers);

	/**
	* This should access the WIPS class and the workflow field. Then it will look through all users and 
	* filter them by comparing their role and values with those of the next state’s entity and values. All
	* valid users will then be added to the allValidUser list.
	*/
	public void filterUser(String val) {
		Wips wips = new Wips();
		List<EndUser> allUser = wips.getEndUser();
		for(int i = 0; i<allUser.size(); i++) {
			if(allUser.get(i).checkValue(val))
				allValidUsers.add(allUser.get(i));
		}
	//	populate(distinctValues);
	}

	/**
	* This method populates the ListView in the UI with the allValidUsers list.
	 * @return 
	*/
//	public <T> void populate(ObservableList<T> d, ListView<User> user, List<EndUser> enduser) {
//		d = FXCollections.observableList(user);
//		enduser.setItems(observableList);
//	}

	/**
	* This method will confirm the user’s recipient choice and send the form the the recipient user’s 
	* received queue
	*/
	public void confirm(int i) {
		//Get the end user from the distinctvalues and then add the form to the its received list
		EndUser to = allValidUsers.get(i);
		EndUser from = (EndUser) Wips.currentUser;
		from.send(Wips.currentWorkflow.form, to);
		cancel();
	}

 	/**
 	* This method will return the user to the window for the FormController without making any changes to 
 	* the system. 
 	*/
 	public void cancel(){
 		//Parent p = FXMLLoader.load(getClass().getResource("Path to the view FXML file"));
 		//View Package will have a class called openSCreen and it will have a method called "openScreen"
 		//It will take 3 parameters openScreen(String fxmlfile, ActionEvent e, String title, Parent p);
 	}
}
