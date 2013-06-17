package il.org.mekorot.search.bookurls;

import il.org.mekorot.search.UrlProvider;

public class ShulhanAruchUrlProvider extends UrlProvider {
	private static final String ORACH_CHAIM = "שולחן_ערוך_אורח_חיים_";
	private static final String YORE_DEA = "שולחן_ערוך_יורה_דעה_";
	private static final String EVEN_HAEZER = "שולחן_ערוך_אבן_העזר_";
	private static final String CHOSHEN_MISHPAT = "שולחן_ערוך_חושן_משפט_";
	private String book;
	private String siman;
	

	@Override
	public String getUrl() {
		return "http://he.wikisource.org/wiki/" + book + siman;
	}

	@Override
	public void reachedLevel(int level, String value) {
		switch(level) {
		case 2:
			if(book == null)
				book = ORACH_CHAIM;
			else if(book.equals(ORACH_CHAIM))
				book = YORE_DEA;
			else if(book.equals(YORE_DEA))
				book = EVEN_HAEZER;
			else if(book.equals(EVEN_HAEZER))
				book = CHOSHEN_MISHPAT;
			
			siman = null;
			break;
		case 3:
			if(siman == null)
				siman = "א";
			else
				siman = incrementHebrew(siman);
			break;
		}
		

	}

}
