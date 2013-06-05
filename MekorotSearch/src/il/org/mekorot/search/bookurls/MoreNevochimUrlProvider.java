package il.org.mekorot.search.bookurls;

import il.org.mekorot.search.UrlProvider;

public class MoreNevochimUrlProvider extends UrlProvider {
	private String PART = "0";
	private String CHAPTER;
	boolean exception = false;
	private String exceptionUrl;
	
	@Override
	public String getUrl() {
		if(exception)
			return exceptionUrl;
		else
			return "http://press.tau.ac.il/perplexed/chapters/chap_" + PART + "_" + CHAPTER + ".htm";
	}

	@Override
	public void reachedLevel(int level, String value) {
		switch(level) {
		case 2:
			PART = increment(PART);
			// init CHAPTER
			CHAPTER = "00";
			break;
		case 3:
			// first elements of each PART are exceptions, see book file
			if(value.equals("פרק האגרת אל התלמיד")) {
				exception = true;
				exceptionUrl = "http://press.tau.ac.il/perplexed/chapters/chap_1_00E.htm";
			} else if(value.equals("פרק פתיחה")) {
				exception = true;
				exceptionUrl = "http://press.tau.ac.il/perplexed/chapters/chap_1_00P.htm";
			} else if(value.equals("פרק הנחות")) {
				exception = true;
				exceptionUrl = "http://press.tau.ac.il/perplexed/chapters/chap_2_00H.htm";
			} else if(value.equals("פרק הקדמה")) {
				exception = true;
				exceptionUrl = "http://press.tau.ac.il/perplexed/chapters/chap_3_00L.htm";
			} else {
				exception = false;
				CHAPTER = increment(CHAPTER);
			}
		}
			

	}

}
