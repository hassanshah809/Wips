package controller.form;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Wips;
import model.wips.forms.Couple;

public class FormController {
	
	@FXML
	Button sendbtn, backbutton;
	
	@FXML 
	Label title, description;
	
	@FXML
	VBox vbox;

	@FXML
	ScrollPane sp;
	
	
	/**
	* This method will show the form to the user
	*/
	public void showForm(){
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
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		vbox.setFillWidth(true);
		
		//Assume we have couples from the form object
		System.out.println("current work flow in form contrlller " + Wips.getInstance().getCurrentWorkFlow());
		Wips.getInstance().setCurrentWorkFlow(Wips.getInstance().getAllWorkFlows().get(1));
		List<Couple> dummyC = Wips.getInstance().getCurrentWorkFlow().getForm().getCouples();
//		for (int i = 0; i < 20; i++) {
//			boolean req = getRandomBoolean();
//			boolean f = getRandomBoolean();
//			if(f == true && req == true) {
//				f = false;
//			}
//			Couple dummyCouple = new Couple("Title"+"["+i+"]",req, f);
//			dummyC.add(dummyCouple);
//		}
		for (Couple c : dummyC)
		    System.out.println(c.getHeading() + "    " + c.isRequired() + "    " + c.isUserField());
		
		//Generate the form that was send to the user
		//Set the title
		Couple temp = dummyC.get(0);
		title.setText(temp.getHeading());
		//Set the description
		temp = dummyC.get(1);
		description.setText(temp.getHeading());
		for(int i = 2; i < dummyC.size(); i++) {
			vbox.getChildren().add(createCoupleRow(dummyC.get(i)));			
		}

		vbox.setSpacing(15);
		System.out.println(vbox.getChildren().size());
	}
	
	private GridPane createCoupleRow(Couple couple) {
		 GridPane gridpane = new GridPane();
		//If required is true and user field is false
		if(couple.isRequired() && !couple.isUserField()){
			System.out.println("a");
			RowConstraints row1 = new RowConstraints();
 	        row1.setVgrow(Priority.ALWAYS);
 	        
 	        ColumnConstraints col1 = new ColumnConstraints();
 	        col1.setPercentWidth(40);
 	        ColumnConstraints col2 = new ColumnConstraints();
 	        col2.setPercentWidth(50);
 	        ColumnConstraints col3 = new ColumnConstraints();
	        col3.setPercentWidth(10);
	        
	        Label label = new Label(couple.getHeading());
	        label.setFont(new Font ("",15));
	        label.setWrapText(true);
	        
	        
	        TextArea textArea = new TextArea();
			textArea.setWrapText(true);
			textArea.setPrefSize( Double.MIN_VALUE, Double.MIN_VALUE );

			
			Label reqLabel = new Label ("*required");

			gridpane.add(label, 0, 1); 
		    gridpane.add(textArea, 1, 1); 
		    gridpane.add(reqLabel, 2, 1);

	        gridpane.getRowConstraints().addAll(row1);
 	        gridpane.getColumnConstraints().addAll(col1,col2,col3);
 	        
 	        gridpane.setHgap(10);
//			gridpane.setVgap(10);
			gridpane.setPadding(new Insets(5, 20, 5, 20)); //margins around the whole grid

			
		} else if (couple.isUserField() && !couple.isRequired()) {
			System.out.println("b");

			RowConstraints row1 = new RowConstraints();
 	        row1.setVgrow(Priority.ALWAYS);
 	        
 	        ColumnConstraints col1 = new ColumnConstraints();
 	        col1.setPercentWidth(100);
	        
	        Label label = new Label(couple.getHeading());
	        label.setFont(new Font ("",15));
	        label.setWrapText(true);
	        
	        gridpane.add(label, 0, 1); 

	        gridpane.getRowConstraints().addAll(row1);
 	        gridpane.getColumnConstraints().addAll(col1);
 	        
 	        gridpane.setHgap(10);
			//gridpane.setVgap(10);
			gridpane.setPadding(new Insets(5, 20, 5, 20)); //margins around the whole grid

			
		} else if (!couple.isUserField() && !couple.isRequired()) {
			System.out.println("c");

			RowConstraints row1 = new RowConstraints();
 	        row1.setVgrow(Priority.ALWAYS);
 	        
 	        ColumnConstraints col1 = new ColumnConstraints();
 	        col1.setPercentWidth(50);
 	        ColumnConstraints col2 = new ColumnConstraints();
 	        col2.setPercentWidth(50);
	        
	        Label label = new Label(couple.getHeading());
	        label.setFont(new Font ("",15));
	        label.setWrapText(true);
	        
	        
	        TextArea textArea = new TextArea();
			textArea.setWrapText(true);
			textArea.setPrefSize( Double.MIN_VALUE, Double.MIN_VALUE );


			gridpane.add(label, 0, 1); 
		    gridpane.add(textArea, 1, 1); 

	        gridpane.getRowConstraints().addAll(row1);
 	        gridpane.getColumnConstraints().addAll(col1,col2);
 	        
 	        gridpane.setHgap(10);
			//gridpane.setVgap(10);
			gridpane.setPadding(new Insets(5, 20, 5, 20)); //margins around the whole grid

		}
		return gridpane;
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
		} else if (b == backbutton) {
			Parent e = FXMLLoader.load(getClass().getResource("/view/endUser/ehomescreen.fxml"));
			OpenScreen.openScreen("ehomescreen.fxml", handler, "Home", e, getClass(),"/view/enduser/ehomescreen.css");
		}
	}
	
	
}
