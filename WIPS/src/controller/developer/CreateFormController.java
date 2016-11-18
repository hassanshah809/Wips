package controller.developer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateFormController {

	@FXML
	TextArea tedit, dedit;
	
	@FXML
	AnchorPane ap;
	
	@FXML
	Button addbtn;

	@FXML
	VBox vbox;
	
	int temp = 1, temp2 = 1;
	
	public CreateFormController() {
	}
	
	@FXML
	protected void initialize() {
		tedit.setPrefRowCount(1);
		tedit.requestLayout();
		
		
		dedit.setPrefRowCount(1);
		dedit.requestLayout();
   }	
	
	@FXML
	private void textKeyPressed(KeyEvent event)	{
		if (event.getCode() == KeyCode.ENTER) {
			tedit.setPrefRowCount(tedit.getPrefRowCount() + 1);
			tedit.requestLayout();
		} else if (event.getCode() == KeyCode.BACK_SPACE) {
			int num2 = tedit.getText().split("\n").length+1;
			tedit.setPrefRowCount(num2-1);
			tedit.requestLayout();
			temp --;
		}
	}
	
	@FXML
	private void textKeyPressedTwo(KeyEvent event)	{
		if (event.getCode() == KeyCode.ENTER) {
			dedit.setPrefRowCount(tedit.getPrefRowCount() + 1);
			dedit.requestLayout();
		} else if (event.getCode() == KeyCode.BACK_SPACE) {
			int num2 = dedit.getText().split("\n").length+1;
			dedit.setPrefRowCount(num2-1);
			dedit.requestLayout();
			temp2 --;
		}
	}
	
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException {
		Button b = (Button) handler.getSource();
		if (b == addbtn) {
			 GridPane gridpane = new GridPane();
			 TextArea ta = new TextArea();
			 ta.setPrefColumnCount(1);
			
			 HBox hb = new HBox();
			 TextArea ta2 = new TextArea();
			 ta2.setPrefColumnCount(1);
			 hb.getChildren().add(ta2);
			 
			 VBox vCon = new VBox();
			 
			 ToggleButton requiredT = new ToggleButton("Required");
			 requiredT.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	if(requiredT.isSelected()){
		            		requiredT.setStyle("-fx-background-color: #f25c21");
			                requiredT.setText("*Required");
		            	} else {
		            		requiredT.setStyle(null);
			                requiredT.setText("Required");
		            	}
		            }
		        });
			 vCon.getChildren().add(requiredT);

			 
			 Button deleteBtn = new Button("Delete this row");
			 deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						deleteBtn.setStyle("-fx-background-color: #f25c21");
						System.out.println(((GridPane)((Button)event.getSource()).getParent()));
						//int slectedGridView = vBox.getSelectionModel().getSelectedItem();
						vbox.getChildren().remove(( (GridPane) ((Button)event.getSource()).getParent()));
					}
				});
			 
			 vCon.getChildren().add(deleteBtn);

			 
			 ToggleButton addDelField = new ToggleButton("Delete");
			 addDelField.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	if(addDelField.isSelected()){
		            		addDelField.setText("Add");
		            		hb.getChildren().remove(0);
	            		
		            	} else {
		            		TextArea dummy = new TextArea();
		            		dummy.setPromptText("User input");
		        			dummy.setDisable(true);
		        			hb.getChildren().add(dummy);
		            	}
		            }
		        });
			 vCon.getChildren().add(addDelField);

			 //deleteBtn.setOnAction(value);
			 
		     gridpane.add(ta, 1, 1); 
		     gridpane.add(hb, 2, 1);
 			 gridpane.add(vCon, 3, 1);

            
//			Button newBtn = new Button();
//			Button delete = new Button("Delete");
			
			vbox.getChildren().add(gridpane);
			ta.setPromptText("Heading Title: " + vbox.getChildren().size());
			ta2.setPromptText("User inout");
			ta2.setDisable(true);
			
		}
	}

}
