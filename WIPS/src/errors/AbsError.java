package errors;

import java.util.List;

public abstract class AbsError {
	
	public AbsError(List<String> e) {
		
	}
	public abstract void handle();
}
