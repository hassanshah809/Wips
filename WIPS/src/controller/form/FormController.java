package controller.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.Wips;
import model.user.EndUser;
import model.wips.forms.Couple;
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
	
	@FXML
	protected void initialize() {
		//Assume we have couples from the form object
		ArrayList<Couple> dummyC = new ArrayList<Couple>();
		for (int i = 0; i < 10; i++) {
			boolean randomTF = getRandomBoolean();
			Couple dummyCouple = new Couple("Title"+"["+i+"]", randomTF, randomTF);
			dummyC.add(dummyCouple);
		}
		for (Couple c : dummyC)
		    System.out.println(c.getHeading() + "    " + c.isIsrequired() + "    " + c.isUserField());
		
		//Generate the form that was send to the user
		
		
		
	}
	
	public boolean getRandomBoolean() {
	    Random random = new Random();
	    return random.nextBoolean();
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == sendbtn) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/eselectstates.fxml"));
			OpenScreen.openScreen("eselectstates.fxml", handler, "Select States", e, getClass(),"/view/enduser/eselectstates.css");
		}
	}
	
	
}
