package il.org.mekorot.search.bookurls;

import il.org.mekorot.search.UrlProvider;

public class TanachUrlProvider extends UrlProvider {
	private String book = "00";
	private String chapter = "00";
	// Exceptions...
	private final String SHEMUEL_A = "שמואל א";
	private final String SHEMUEL_B = "שמואל ב";
	private final String MELACHIM_A = "מלכים א";
	private final String MELACHIM_B = "מלכים ב";
	private final String DIVREY_HAYAMIM_A = "דברי הימים א";
	private final String DIVREY_HAYAMIM_B = "דברי הימים ב";
	private final String EZRA = "עזרא";
	private final String NEHEMYA = "נחמיה";

	
	@Override
	public String getUrl() {
		return "http://www.mechon-mamre.org/i/t/t" + book + chapter + ".htm";
	}

	@Override
	public void reachedLevel(int level, String value) {
		switch(level) {
		case 2:
			chapter = "00";
			// Exceptional cases...
			if(value.equals(SHEMUEL_A)) {
				book = "08a";
				break;
			} else if(value.equals(SHEMUEL_B)) {
				book = "08b";
				break;
			} else if(value.equals(MELACHIM_A)) {
				book = "09a";
				break;
			} else if(value.equals(MELACHIM_B)) {
				book = "09b";
				break;
			} else if(value.equals(DIVREY_HAYAMIM_A)) {
				book = "25a";
				break;
			} else if(value.equals(DIVREY_HAYAMIM_B)) {
				book = "25b";
				break;
			} else if(value.equals(EZRA)) {
				book = "35a";
				break;
			} else if(value.equals(NEHEMYA)) {
				book = "35b";
				break;
			}
			// END Exceptions
			
			book = myIncrement(book);
			break;
		case 3:
			chapter = myIncrement(chapter);
			break;
		}
	}
	private String myIncrement(String value) {
		// remove any trailing 'a' or 'b' if exists
		if(value.endsWith("a") || value.endsWith("b"))
			value = value.substring(0, value.length()-1);
		
		String res;
		if(value.startsWith("0")) { // e.g. "03" is turned into "04"
			Integer newValue = toInt(value.substring(1)) + 1;
			if(newValue < 10)
				res = "0" + newValue.toString();
			else
				res = "10";
		} else { // e.g. value == "17"
			res = increment(value);
		}
		
		return res;
	}


}
