package controller.developer;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import errors.AbsError;
import helper.OpenScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Wips;
import model.parser.Parser;
import model.parser.TransitionParser;
import model.parser.UserParser;
import model.parser.WorkFlowParser;
import model.parser.intermediates.GenInter;
import model.parser.intermediates.WorkFlowInter;
import model.user.User;
import model.wips.Entity;
import model.wips.State;
import model.wips.Transition;
import model.wips.WorkFlow;

public class CreateWorkFlowController {
	
	@FXML
	Button wBrowse, tBrowse, uBrowse, backBtn, nextBtn, logoutBtn;
	
	@FXML
	Label wFileName, tFileName, uFileName;
	
	/**
	 * Abstract Error class that maybe called during workflow, transition, user errors.
	 */
	AbsError e;
	/**
	 * This parser will be used for workflows, transitions, and users to parse the xml file.
	 */
	Parser parser;
	
	WorkFlowInter<Entity, State> wfi = null;
	GenInter<User> users;
	GenInter<Transition> transitions;
	
	@FXML
	protected void initialize() {
		wfi = new WorkFlowInter<Entity, State>();
		//Do something once the FXML is done
		//Enable this in the final product
		//enableDisableBtn(false, true, true, true);
	}
	
	private void enableDisableBtn (boolean wbtn, boolean tBtn, boolean uBtn, boolean nxtBtn) {
		wBrowse.setDisable(wbtn);
		tBrowse.setDisable(tBtn);
		uBrowse.setDisable(uBtn);
		nextBtn.setDisable(nxtBtn);
	}
	
	public CreateWorkFlowController() {

	}
	/**
	 * This will pass the file argument to the Workflow parser class to be parsed 
	 * @param file File
	 */
	public void workFlowXml(File file){
		//uploadworkflow button
		parser = new WorkFlowParser(file);
		parser.parse();
		Object o = parser.getInters();
		if(o instanceof WorkFlowInter)
			wfi = (WorkFlowInter<Entity, State>)o;
	}
	
	/**
	 * This will pass the file argument to the TransitionParser class to be parsed
	 * @param file File
	 */
	public void transitionXml(File file) {
		//upload transition button
		parser = new TransitionParser(file,wfi);
		parser.parse();
		Object o = parser.getInters();
		transitions = (o instanceof GenInter ? (GenInter<Transition>) o : null);	
		System.out.println("size of transitions in creat wf contrl   " + transitions.getTempAttr().size());
	}
	
	/**
	 * This will redirect the workflow application developer to the window where 
	 * he/she can generate forms
	 */
	public void generateForms() {
		//chodu will on it
	}
	
	/**
	 * This will pass the file argument into the userParser class
	 * @param file File
	 */
	public void userXmlParser(File file) {
		UserParser up = new UserParser(file, wfi);
		up.parse();
		Object o = up.getInters();
		users = (o instanceof GenInter ? (GenInter<User>) o : null);
		addUsersToWips(users.getTempAttr());
	}
	
	public void addUsersToWips(List<User> users) {
		for(User user: users) {
			Wips.getInstance().addUser(user);
		}
	}
	/**
	 * This will redirect the workflow application developer to the window where 
	 * he/she  can set permissions for states.
	 */
	public void setStatePermission() {
		//open new windows
	}
	
	/**
	 * This method will finalize all changes made to this workflow and generate a
	 * workflow object. It will access the intermediate model classes as well as 
	 * add all forms created and users associated with this workflow and store the 
	 * Workflow object in the workflows list in the workflow application’s class. 
	 */
	public void finish() {
		System.out.println(wfi.getTempAttr().size());
		WorkFlow wf = new WorkFlow(wfi.getTempStates(), wfi.getTempAttr(), transitions.getTempAttr());
	//	Wips.getInstance().getAllWorkFlows().add(wf);
		Wips.getInstance().setCurrentWorkFlow(wf);
		//wf.addUser(users.getTempAttr());
		reset();
		
	}

	public void reset() {
		e = null;
		users = null;
		transitions = null;
	}
	/**
	* This method will get the xml file and returns the file object
	*/
	public File getFile(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select an XML File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		//fileChooser.getExtensionFilters()
			//	.addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		File file = fileChooser.showOpenDialog(st);
		return file;
	}
		
	public void handle(ActionEvent handler) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
		Button b = (Button) handler.getSource();
		if (b == wBrowse) {
			File f = getFile(handler);
			if(f != null){
				wFileName.setText(f.getName());
				System.out.println(f.getName());
				workFlowXml(f);
				enableDisableBtn(false, false, true, true);

				//If file exist then call workflowxml parser
				//workFlowXml(f);
			}			
		} else if (b == tBrowse) {
			File f = getFile(handler);
			if(f != null){
				tFileName.setText(f.getName());
				enableDisableBtn(false, false, false, true);

				//If file exist then call trnasition parser
				transitionXml(f);
			}			
		} else if (b == uBrowse) {
			File f = getFile(handler);
			if(f != null){
				uFileName.setText(f.getName());
				enableDisableBtn(false, false, false, false);

				//If file exist then call userxml parser
				userXmlParser(f);
			} 			
		} else if (b == backBtn) {
			//Goes back to the admin home screen
			Parent l = FXMLLoader.load(getClass().getResource("/view/developer/dhomescreen.fxml"));
			OpenScreen.openScreen("dhomescreen.fxml", handler, "Developer", l, getClass(),"/view/developer/dhomescreen.css");
		} else if (b == nextBtn) {
			//Goes to state permission window
			finish();
			Parent l = FXMLLoader.load(getClass().getResource("/view/developer/dstatepscreen.fxml"));
			OpenScreen.openScreen("dstatepscreen.fxml", handler, "State Permission", l, getClass(),"/view/developer/dstatepscreen.css");
		} else if (b == logoutBtn) {
			//Goes back to the user login window
			Parent l = FXMLLoader.load(getClass().getResource("/view/session/userlogin.fxml"));
			OpenScreen.openScreen("userlogin.fxml", handler, "Log in", l, getClass(),"/view/session/application.css");
		}
	}
}
