package helper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OpenScreen {

	public OpenScreen() {
		// TODO Auto-generated constructor stub
	}

	public static void openScreen(String ss, ActionEvent e, String title, Parent p) throws IOException {
		Scene s = new Scene(p);
		//s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
		st.setScene(s);
		st.setTitle(title);
		st.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				if (ss.equalsIgnoreCase("dhomescreen.fxml")) {
					//Seralize everything before you close this window
					System.out.println("DEVELOPER HOME SCREEN CLOSED");
				} else if (ss.equalsIgnoreCase("ehomescreen.fxml")) {
					//Seralize everything before you close this window
					System.out.println("ENDUSER HOME SCREEN CLOSED");
				}
			}
		});
		st.setResizable(true);
		st.show();
	}
}
