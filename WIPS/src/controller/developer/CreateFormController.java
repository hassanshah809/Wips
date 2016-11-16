package controller.developer;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CreateFormController {

	@FXML
	TextArea tedit, dedit;
	
	@FXML
	AnchorPane ap;


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

}
