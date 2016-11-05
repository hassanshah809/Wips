package controller.form;

import model.Wips;
import model.user.EndUser;
import model.wips.forms.Form;

public class FormController {
	/**
	* This method will show the form to the user
	*/
	public void showForm(){
		Form form = Wips.getInstance().currentWorkflow.form;
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
}
