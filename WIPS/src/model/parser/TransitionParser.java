package model.parser;

import org.w3c.dom.*;

import model.parser.intermediates.GenInter;
import model.parser.intermediates.WorkFlowInter;
import model.wips.State;
import model.wips.Transition;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

public class TransitionParser extends Parser {
	
	
	/**
	 * This contains the workflow intermediate object which contains all the states and entities which 
	 * contains  
	 */
	
	WorkFlowInter wfi; 
	
	
	/**
	 * This is the intermediate object which will store transition objects that have been extracted from the
	 * transitions XML file
	 */
	
	GenInter<Transition> transitions = new GenInter<Transition>();
	
	/**
	 * This is the constructor for the TransitionParser module. This construtor takes two arguments:
	 * A File transitionFile which stores the XML file to be parsed and the WorkFlowInter wfi
	 * object which will be used in the parse() method to reference created states and entities.
	 */
	public TransitionParser(File transitionFile, WorkFlowInter wfi) {
		super(transitionFile);
		this.wfi = wfi; 
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method is responsible for parsing the file that was passed through when this module during
	 * instantiation. This method is also responsible for storing the extracted information into the
	 * GenInter<Transition> object. 
	 */
	
	public void parse() {
		try{
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);
			doc.getDocumentElement().normalize();
			
			Transition transition;
		
			NodeList transitionList = doc.getElementsByTagName("transition");
			
			for(int i = 0; i< transitionList.getLength(); i++) {
				Node tranNode = transitionList.item(i);
				String startStateStr; 
				String endStateStr; 
				
				if(tranNode.getNodeType() == Node.ELEMENT_NODE) {
					Element transElement = (Element) tranNode; 
					
					//This will hold state id. 
					
					startStateStr = transElement.getAttribute("startstate");
					endStateStr = transElement.getAttribute("endstate");
					
					//This contains that actual id values for states 
					
					
					State startState = null;
					State endState = null;
					
					int startStateID = Integer.parseInt(startStateStr);
					int endStateID = Integer.parseInt(endStateStr);
					
					ArrayList<State> temp_states = (ArrayList<State>) wfi.getTempStates();
					
					for(int j = 0; j < temp_states.size(); j++) {
						if(temp_states.get(i).getID() == startStateID) {
							startState= temp_states.get(i);
						} else if(temp_states.get(i).getID() == endStateID) { 
							endState = temp_states.get(i);
						}
					}
					
					if(startState == null || endState == null) {
						//Throw an error stating that 
					}
					
					
					transition = new Transition(startState, endState);
					transitions.getTempAttr().add(transition); 
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns the intermediate object in which the extracted information from the XML
	 * file is stored.
	 * @return The object containing the extracted information 
	 */
	
	public GenInter<Transition> getTransitions() {
		return this.transitions;
	}

}
