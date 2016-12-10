package model.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.*;

import errors.AbsError;
import errors.ParserError;
import model.wips.State;
import model.parser.intermediates.WorkFlowInter;
import model.wips.Entity;

import javax.xml.parsers.*;
import java.io.*;

public class WorkFlowParser extends Parser {
	
	
	HashMap<String,Boolean> keyMap; 
	WorkFlowInter<Entity, State> wfi = new WorkFlowInter<>();
	
	/**
	 * it creates the new workflow parser object
	 */
	public WorkFlowParser(File workflowFile) {
		super(workflowFile);
		keyMap = new HashMap<String,Boolean>(); 
		keyMap.put("startStateError", false);
		keyMap.put("incorrectStateTag", false);
		keyMap.put("incorrectEntityTag",false);
		keyMap.put("incorrectValueTag", false);
		keyMap.put("endStateError", false); 
	}

	/**
	 * Stores the intermediate object where the values parsed from the XML files will be located. 
	 */
	
	
	
	@Override
	
	public void parse() {
		
		AbsError error; 
		State stateObj; 
		Entity entityObj;
		ArrayList<String> errorKeys = new ArrayList<String>();
		
		try{
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);
			doc.getDocumentElement().normalize();
			ArrayList<String> values;
			
			NodeList entityList = doc.getElementsByTagName("entity");
			
			wfi = new WorkFlowInter<Entity, State>();
			extract(entityList);
		
			if (keyMap.containsValue(true)) {
				List<String> errors = new ArrayList<String>();
				if (keyMap.get("startStateError"))
					errors.add("There is a start state error in the Workflow XML file.");
				if (keyMap.get("incorrectStateTag"))
					errors.add("There is an incorrect state tag in the Workflow XML file.");
				if (keyMap.get("incorrectEntityTag"))
					errors.add("There is an incorrect entity in the Workflow XML file.");
				if (keyMap.get("incorrectValueTag"))
					errors.add("There is an incorrect value in the Workflow XML file.");
				if (keyMap.get("endStateError")) 
					errors.add("There is an end state error in the Workflow XML file.");
				
				this.getError(errors).handle();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void extract(NodeList entityList) {
		
		Entity entityObj;		
		
		for(int i = 0; i < entityList.getLength(); i++) {
			
			String role = "";
			Node entityNode = entityList.item(i);
			

			if(entityNode.getNodeType() == Node.ELEMENT_NODE) {
				
				/*
				if(!entityNode.equals("entity")) {
					keyMap.put("incorrectEntityTag", true);
					
				}
				*/
				NodeList stateList = entityNode.getChildNodes();
				Element entityElement = (Element) entityNode; 
				role = entityElement.getAttribute("role").toString(); 
				entityObj = new Entity(role);
				wfi.addAttr(entityObj);
			
				//Add to intermediate model class
				
				addToStateInter(stateList, entityObj);
			}
		}
	}
	
	private void addToStateInter(NodeList stateList, Entity entityObj) {
		
		State stateObj;
		
		ArrayList<String> values;
		
		for(int j = 0; j < stateList.getLength(); j++) {
			Node stateNode = stateList.item(j); 
			
			String idAttrVal = "";
			String startStateVal = "";
			String stateNameVal = ""; 
			String endStateVal = ""; 
			
			boolean startState = true;
			boolean endState = false;
			int id; 
			
			if(stateNode.getNodeType() == Node.ELEMENT_NODE) {
				
				if(!stateNode.getNodeName().equals("state")) {
					keyMap.put("incorrectStateTag", true);
				}
				
				NodeList valueList = stateNode.getChildNodes();
				Element stateElement = (Element) stateNode;
				idAttrVal = stateElement.getAttribute("id").toString();
				startStateVal = stateElement.getAttribute("start").toString();
				stateNameVal = stateElement.getAttribute("name").toString();
				endStateVal = stateElement.getAttribute("end").toString();
				
				
				//Translating 
				
				id = Integer.parseInt(idAttrVal);
				
				if(startStateVal.toLowerCase().equals("true")) {
					startState = true;
				} else if(startStateVal.toLowerCase().equals("false")) {
					startState = false; 
				} else {
					keyMap.put("startStateError", true);
				}
				
				
				if(endStateVal.toLowerCase().equals("true")) {
					endState = true;
				} else if(endStateVal.toLowerCase().equals("false")) {
					endState = false;
				} else {
					keyMap.put("endStateError", true);
				}
				
				values = new ArrayList<String> (); 
				values = extractValues(valueList, values);
				
				stateObj = new State(id, startState, entityObj);
				stateObj.setName(stateNameVal);
				stateObj.setEndState(endState);
				stateObj.addDistintVals(values);
				wfi.addStates(stateObj);		
			}
		}
	}

	private ArrayList<String> extractValues(NodeList valueList, ArrayList<String> values) {
		
		for(int k = 0; k < valueList.getLength(); k++) {
			Node valueNode = valueList.item(k);
			if(valueNode.getNodeType() == Node.ELEMENT_NODE) {
				
				if(!valueNode.getNodeName().equals("value")) {
					keyMap.put("incorrectValueTag", true);
				}
				Element valElement = (Element) valueNode;
				String value = valueNode.getTextContent();
				values.add(value);
			}
		}
		
		return values;
	}
	
	@Override
	public Object getInters() {
		// TODO Auto-generated method stub
		return this.wfi;
	}
}
