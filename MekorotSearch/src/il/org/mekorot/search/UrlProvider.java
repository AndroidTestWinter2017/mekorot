package il.org.mekorot.search;

/**
 * Helper class in the process of parsing a Book file. The class
 * helps in tracking the exact URL of each leaf node. Each book, e.g.,
 * the Tanach, should extend this class. Basically, a subclass should
 * set an initial value for the URL and then update that value when
 * the handlers are notified. 
 * @author oren
 *
 */
public abstract class UrlProvider {
	public abstract String getUrl();
	/**
	 * A handler notifying that the parsing of a line in level x is
	 * starting.
	 * @param level e.g., 1, 2, 3 (the number of '*' at the beginning of the line).
	 * @param value the value of the line that is being parsed.
	 */
	public abstract void reachedLevel(int level, String value);
	
	/**
	 * Gets a number in a string representation and increment it
	 * in one. If value starts with 0 e.g. "04" then "05" is
	 * returned, unless value is "09" and then "10" is returned. 
	 * @param value
	 * @return
	 */
	protected String increment(String value) {
		String result;
		
		if(value.length() == 2 && value.startsWith("0")) { // we assume value has two chars...
			result = increment(removeFirstChar(value));
			if(toInt(result) == 10)
				result = "10";
			else // < 10
				result =  "0" + result;
		} else { // e.g. "13", "145"
			Integer num = toInt(value) + 1;
			result = num.toString();
		}
		
		return result;
	}
	/**
	 * Gets a sequence in Hebrew and increments it, e.g.,
	 * א => ב
	 * קיא => קיב
	 * @param seq
	 * @return
	 */
	protected String incrementHebrew(String seq) {
		return HebrewRange.incrementSequence(seq);
	}
	protected Integer toInt(String value) {
		return Integer.parseInt(value);
	}
	protected String removeLastChar(String str) {
		if(str == null)
			return null;
		else
			return str.substring(0, str.length() - 1);
	}
	protected String removeFirstChar(String str) {
		if(str == null)
			return null;
		else
			return str.substring(1, str.length());
	}
	protected Boolean isInArray(Object[] array, Object value) {
		for(Object elem : array) {
			if(elem.equals(value))
				return true;
		}
		return false;
	}
	
}
