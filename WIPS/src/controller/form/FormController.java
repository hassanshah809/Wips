package controller.form;

import java.io.IOException;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.Wips;
import model.user.EndUser;
import model.wips.forms.Form;

public class FormController {
	
	@FXML
	Button sendbtn;
	/**
	* This method will show the form to the user
	*/
	public void showForm(){
		Form form = Wips.getInstance().getCurrentWorkFlow().form;
		//Once we have the form we show the form using GUI
	}

	/**
	* Accesses the static user variable in the WIPS class  and adds this form object to that user’s sent 
	* list. This also accesses the recipient user’s received queue and adds this form to their received   
	* queue. 
//	*/
//	public void send(Form f) {
//		EndUser enduser = (EndUser) Wips.currentUser;
//		//enduser.send(f,); 
//	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == sendbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eselectstates.fxml"));
			OpenScreen.openScreen("eselectstates.fxml", handler, "Select States", e, getClass(),"/view/enduser/eselectstates.css");
		}
	}
	
	
}
