package model.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import errors.AbsError;
import errors.ParserError;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * @author Deep, Hassan, Kenneth
 *
 */
public abstract class Parser {
	
	/**
	 * This will contain the File object that will be parsed. 
	 */
	
	File fileName; 
	
	/**
	 * This will contain all accepted tags associated with this parser. 
	 * Each specific parser class will have a list of accepted tags that should be encountered in the  XML document.
	 */
	List<String> tagTable;
	/**
	 * This hashmap will have a keyset where each element in the set will be an accepted attribute name that will be encountered in the XML document. 
	 * The values stored will contain the values of the extracted from each line in the XML document.
	 */
	HashMap<String, String> attributes;
	/**
	 * This is the array that will initially store all possible tags that will be encountered in the XML document. 
	 * All contents from this array will be transferred from this array to the list during the constructor call. 
	 */
	String[] symbols;
	/**
	 * This is the array that will initially store all possible attribute names that will be encountered in the XML document. 
	 * All contents from this array will become elements in the keyset for the hashmap. 
	 */
	String[] keys;
	/**
	 * This  list will store keys for error methods which will be then be accessed by the error classes to generate the
	 * corresponding error message for the error encountered
	 */
	List<String> tempError;
	/**
	 * it helps to create concrete parser objects.
	 */
	public Parser(File f) {
		this.fileName = f; 
		tagTable = new ArrayList<String>();
		attributes = new HashMap<String, String>();
		for(String s: symbols)
			addSymbol(s);
		
		for(String s: keys)
			addKeys(s);
	}

	/**
	 * This will begin the process for parsing the XML file. Takes a file as an argument, which will be the file that will be parsed.
	 */
	
	public abstract void parse();
	
	/**
	 * Check if the tag encountered in the XML document is found in the list of accepted tags for this parser. 
	 */
	private void searchKeys(){
		
	}
	
	/**
	 * Check if the attribute encountered in the line in the XML document is a valid attribute name by cross referencing it 
	 * with the elements in the keyset of the attributes hashmap.
	 */
	private void searchAttributes() {
		
	}
	
	/**
	 * This will transfer all values from the validTags array into the tagTable list.
	 * @param symbol String
	 */
	public void addSymbol(String symbol){
		tagTable.add(symbol);
	}
	
	/**
	 * This will generate a keyset for the attributes hashmap using the elements in the attributesTable array. 
	 * @param key String
	 */
	public void addKeys(String key) {
		attributes.keySet().add(key);
	}
	
	/**
	 * This is a method that will store a particular value in that key in the hashmap.
	 * @param key String
	 * @param value String
	 */
	public void addValue(String key, String value) {
		attributes.put(key, value);
	}
	
	/**
	 * If any error is found while parsing the file. 
	 * @param errors List of String
	 * @return ParserError
	 */
	public AbsError getError(List<String> errors) {
		return new ParserError(errors);
	}
	
	/**
	 * Takes all the values stored in the hashmap and passes those values into the 
	 * respective object constructor and stores the newly created object in the respective intermediate model. 
	 */
	public abstract void store();
}
