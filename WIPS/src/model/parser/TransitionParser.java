package model.parser;

import org.w3c.dom.*;

import model.wips.Transition;

import javax.xml.parsers.*;
import java.io.*;

public class TransitionParser extends Parser {
	
	/**
	 * it creates new transition parser object
	 */
	public TransitionParser(File transitionFile) {
		super(transitionFile);
		
		// TODO Auto-generated constructor stub
	}
	
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
					
					int startStateID = Integer.parseInt(startStateStr);
					int endsStateID = Integer.parseInt(endStateStr);
					
					
					
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
