package controller.developer;

import java.io.File;

import errors.AbsError;
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
		Object o = parser.getInters();
		users = (o instanceof GenInter ? (GenInter<User>) o : null);
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
		WorkFlow wf = new WorkFlow(wfi.getTempStates(), wfi.getTempAttr(), transitions.getTempAttr(),null);
		wf.addUser(users.getTempAttr());
		
	}

	/**
	* This method will get the xml file and returns the file object
	*/
	public File getFile() {
		return null;
	}
}
