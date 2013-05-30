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
	 * in one.
	 * @param value
	 * @return
	 */
	protected String increment(String value) {
		Integer result = toInt(value) + 1;
		return result.toString();
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
	
}
