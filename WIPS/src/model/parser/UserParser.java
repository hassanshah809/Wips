package model.parser;

import model.wips.*;
import model.wips.Entity;

import org.w3c.dom.*;

import model.parser.intermediates.GenInter;
import model.parser.intermediates.WorkFlowInter;
import model.user.EndUser;
import model.user.User;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserParser extends Parser {
	
	/**
	 * This list contains all usernames that have been found in the XML parser
	 */
		
	ArrayList<String> userNames;
	
	/**
	 * This hashmap contains a String regarding the "type of" error that was encountered while parsing.
	 */
	
	HashMap<String,Boolean> keyMap; 
	
/**
 * This contains the intermediate object which contains the list of all
 * States and Entities associated with the "to-be-created" workflow.
 */

WorkFlowInter<Entity,State> wfi;

/**
 * This contains the intermediate object which contains all user information
 * parsed from the user xml file.
 */

GenInter<User> usersInter = new GenInter<User>();

/**
 * it creates new user parser object
 */
public UserParser(File userFile, WorkFlowInter wfi) {
	super(userFile);
	this.wfi = wfi;
	keyMap = new HashMap<String,Boolean>();
	keyMap.put("duplicateUserName", false);
	keyMap.put("roleNotFound", false);
	// TODO Auto-generated constructor stub
}




/**
 * This method is responsible for parsing the file containing all information regarding the user and stores the
 * parsed information in a GenInter<User> object to be used later.  
 * 
 */


public void parse() {

	try {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(fileName);
		doc.getDocumentElement().normalize();

		User user;

		NodeList userList = doc.getElementsByTagName("user");

for (int i = 0; i < userList.getLength(); i++) {

	ArrayList<Entity> roleArrayList = new ArrayList<Entity>();
	ArrayList<String> valuesArrayList = new ArrayList<String>();

	Node userNode = userList.item(i);

	String username = "";
	String role = "";

if (userNode.getNodeType() == Node.ELEMENT_NODE) {
	Element userElement = (Element) userNode;
	username = userElement.getAttribute("name");
	
	if(!userNames.contains(username)) {
		userNames.add(username);
	} else {
		keyMap.put("duplicateUserName", true);
	}

	Node node1 = userNode.getFirstChild();
	Node node2 = node1.getNextSibling();

	if (node1.getNodeName().equals("roles")) {
		if (node1.getNodeType() == Node.ELEMENT_NODE) {
			NodeList roleList = node1.getChildNodes();
			Node roleNode;

			for (int j = 0; j < roleList.getLength(); j++) {
				roleNode = roleList.item(j);
				if (roleNode.getNodeType() == Node.ELEMENT_NODE) {
					Element roleElement = (Element) roleNode;
					role = roleElement.getAttribute("value");

					Entity entity;
					ArrayList<Entity> entities = (ArrayList<Entity>) wfi.getTempAttr();
					boolean roleFound = false;
					
					
					for (int k = 0; k < entities.size(); k++) {
						if (entities.get(k).getRole().equals(role)) {
							roleArrayList.add(entities.get(k));
							roleFound = true; 
						} 
					}
					
					if(!roleFound) {
						
					}
				}
			}
		}

		// NOW WE DO VALUES STUFF

		String value;

		if (node2.getNodeType() == Node.ELEMENT_NODE) {
			NodeList valuesList = node2.getChildNodes();
			Node valueNode;

			for (int j = 0; j < valuesList.getLength(); j++) {
				valueNode = valuesList.item(j);
				if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
					Element valueElement = (Element) valueNode;
					value = valueElement.getAttribute("value");
					valuesArrayList.add(value);
				}
			}
		}

		user = new EndUser(username, roleArrayList, valuesArrayList);
		usersInter.addAttr(user);
	} else {

		String value;

		if (node1.getNodeType() == Node.ELEMENT_NODE) {
			NodeList valuesList = node1.getChildNodes();
			Node valueNode;

			for (int j = 0; j < valuesList.getLength(); j++) {
				valueNode = valuesList.item(j);
				if (valueNode.getNodeType() == Node.ELEMENT_NODE) {
					Element valueElement = (Element) valueNode;
					value = valueElement.getAttribute("value");
					valuesArrayList.add(value);
				}
			}
		}

		if (node2.getNodeType() == Node.ELEMENT_NODE) {
			NodeList roleList = node2.getChildNodes();
			Node roleNode;

			for (int j = 0; j < roleList.getLength(); j++) {
				roleNode = roleList.item(j);
				if (roleNode.getNodeType() == Node.ELEMENT_NODE) {
					Element roleElement = (Element) roleNode;
					role = roleElement.getAttribute("value");

					Entity entity;
					ArrayList<Entity> entities = (ArrayList<Entity>) wfi.getTempAttr();

					boolean roleFound = false;
					
					for (int k = 0; k < entities.size(); k++) {
						if (entities.get(k).getRole().equals(role)) {
							roleArrayList.add(entities.get(k));
							roleFound = true; 
						}
					}
					
					if(!roleFound) {
						keyMap.put("roleNotFound", true);
					}
				}
			}
						}
					}

					user = new EndUser(username, roleArrayList, valuesArrayList);
					usersInter.addAttr(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This returns the GenInter<User> object where the users parsed from the user file are stored. 
	 * @return GenInter<User> usersInter.
	 */

	public GenInter<User> getUserInter() {
		return this.usersInter;
	
	}
	
	@Override
	public Object getInters() {
		// TODO Auto-generated method stub
		return this.usersInter;
	}
}
