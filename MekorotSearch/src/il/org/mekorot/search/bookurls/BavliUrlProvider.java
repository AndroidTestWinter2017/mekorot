package il.org.mekorot.search.bookurls;

import il.org.mekorot.search.UrlProvider;

public class BavliUrlProvider extends UrlProvider {
	private String massechet = "0";
	private String daf;
	@Override
	public String getUrl() {
		return "http://www.hebrewbooks.org/shas.aspx?mesechta=" + massechet + "&daf=" + daf + "&format=text";
	}

	@Override
	public void reachedLevel(int level, String value) {
		switch(level) {
		case 2:
			massechet = increment(massechet);
			// init daf:
			daf = "1b" ;
			break;
		case 3:
			incrementDaf();
		}

	}

	private void incrementDaf() {
		if(daf.endsWith("b")) {
			daf = increment(removeLastChar(daf));
		} else {
			daf = daf + "b";
		}
	}

}
