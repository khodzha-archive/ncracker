import ru.ncedu.java.tasks.Checker;

import java.util.List;
import java.util.regex.*;

public class CheckerImpl implements Checker {

	public Pattern getPLSQLNamesPattern() {
		return Pattern.compile("[a-zA-Z][a-zA-Z\\d_$]{0,29}");
	}
	
	public Pattern getHrefURLPattern() {
		return Pattern.compile("(?<=href[=\\s]{0,10000000})[\\w\\.]+");
	}
	
	public Pattern getEMailPattern() {
		return Pattern.compile("[a-zA-Z0-9](|[\\w-_\\.]{0,20}[a-zA-Z0-9])@([a-zA-Z0-9](|[\\w-]*[\\w])\\.){1,100000000}(ru|com|net|org)");
	}

	public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
		if (inputString == null && pattern == null) {
			return true;
		}
		else if ( inputString != null && pattern != null) {
			return pattern.matcher(inputString).matches();
		} else {
			throw new IllegalArgumentException();
		}
	}
}