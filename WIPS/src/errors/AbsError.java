package errors;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsError {
	
	protected List<String> keyList;
	
	public AbsError() {
		keyList = new ArrayList<String>();
	}
	
	public void addError(String newError) 
	{
		keyList.add(newError);
	}
	
	public void handle()
	{
		//Print all errors in the list
		for (String currentError : keyList)
		{
			//print line/error number + current error
			//Pops.pop(Line number of error, error itself)
		}
	}
}
