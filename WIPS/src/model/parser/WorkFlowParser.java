package model.parser;

import java.util.HashMap;
import org.w3c.dom.*;

import model.wips.State;
import model.parser.intermediates.WorkFlowInter;
import model.wips.Entity;

import javax.xml.parsers.*;
import java.io.*;

public class WorkFlowParser extends Parser {
	
	/**
	 *it stores the entities for the workflow. 
	 *Assuming that the inherited attributes hashmap is for states
	 */
	HashMap<String, String> enAttr;
	/**
	 * it creates the new workflow parser object
	 */
	public WorkFlowParser(File workflowFile) {
		super(workflowFile);
	}

	/**
	 * Stores the intermediate object where the values parsed from the XML files will be located. 
	 */
	
	WorkFlowInter<Entity, State> wfi;
	
	@Override
	
	public void parse() {
		
		
		State stateObj; 
		Entity entityObj;
		
		
		try{
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);
			doc.getDocumentElement().normalize();
			
			NodeList entityList = doc.getElementsByTagName("entity");
						
			wfi = new WorkFlowInter<Entity, State>();
			
		
			//This loop should go through all the entities and extract information to be stored in intermediate model classes
			
			//Attributes that should be available: id
			
			
			for(int i = 0; i < entityList.getLength(); i++) {
				
				String role = "";
				Node entityNode = entityList.item(i);
				

				if(entityNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList stateList = entityNode.getChildNodes();
					Element entityElement = (Element) entityNode; 
					role = entityElement.getAttribute("role").toString(); 
					entityObj = new Entity(role);
					wfi.addAttr(entityObj);
				
					//Add to intermediate model class
					
					for(int j = 0; j < stateList.getLength(); j++) {
						Node stateNode = stateList.item(j); 
						String idAttrVal = "";
						String startStateVal = "";
						
						boolean startState = true;
						int id; 
						
						if(stateNode.getNodeType() == Node.ELEMENT_NODE) {
							Element stateElement = (Element) stateNode;
							idAttrVal = stateElement.getAttribute("id").toString();
							startStateVal = stateElement.getAttribute("start").toString();
							
							//Translating 
							
							id = Integer.parseInt(idAttrVal);
							
							if(startStateVal.toLowerCase().equals("true")) {
								startState = true;
							} else if(startStateVal.toLowerCase().equals("false")) {
								startState = false; 
							} else {
								// throw error
							}
							
							stateObj = new State(id, startState, entityObj);
							wfi.addStates(stateObj);
							//add to intermediate model class.
					
						}
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public WorkFlowInter getIntermediate() {
		return this.wfi; 
	}
}
