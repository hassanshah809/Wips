package model.parser;

import org.w3c.dom.*;

import model.user.User;

import javax.xml.parsers.*;
import java.io.*;

public class UserParser extends Parser {

	/**
	 * it creates new user parser object
	 */
	public UserParser(File userFile) {
		super(userFile);
		// TODO Auto-generated constructor stub
	}
	
	public void parse() {
		try{
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);
			doc.getDocumentElement().normalize();
			
			User user;
			
			NodeList userList = doc.getElementsByTagName("user");
			
			for(int i = 0; i<userList.getLength(); i++) {
				Node userNode = userList.item(i);
				
				String name = "";
				String role = ""; 
				
				if(userNode.getNodeType() == Node.ELEMENT_NODE) {
					Element userElement = (Element) userNode;
					name = userElement.getAttribute("name");
					
					Node node1 = userNode.getFirstChild();
					Node node2 = node1.getNextSibling();
					
					if(node1.getNodeName().equals("role")) {
						//Do stuff for roles. 
					} else {
						//Do stuff for values. 
					}
					//Roles are to be stored in a list. 
					
//					user = new User(name,role);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void store() {
		// TODO Auto-generated method stub
		
	}

}
