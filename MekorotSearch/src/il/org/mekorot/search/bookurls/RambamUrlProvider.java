package il.org.mekorot.search.bookurls;

import il.org.mekorot.search.UrlProvider;

public class RambamUrlProvider extends UrlProvider {
	/**
	 * E.g., Ahava, Mada...
	 */
	private String BOOK = "0";
	private String HALACHOT = "";
	private String CHAPTER = "";
	private String FIRST_BOOK_IN_SEFER_TAHARA = "טומאת מת";
	private String FIRST_BOOK_IN_SEFER_NEZIKIN = "נזיקי ממון";
	private String FIRST_BOOK_IN_SEFER_KINYAN = "מכירה";
	private String FIRST_BOOK_IN_SEFER_MISHPATIM = "שכירות";
	private String FIRST_BOOK_IN_SEFER_SHOFTIM = "סנהדרין והעונשין המסורין להן";
	private String HALACHOT_MEGILA = "מגלה וחנוכה";
	/**
	 * First books in a sefer, when reached increment BOOK
	 */
	private static final String[] FIRST_BOOKS = new String[] { "יסודי התורה", "קרית שמע", "שבת", "אישות",
		"איסורי ביאה", "שבועות", "כלאים", "בית הבחירה", "קרבן פסח", "טומאת מת", "נזיקי ממון", "מכירה",
		"שכירות", "סנהדרין והעונשין המסורין להן" };
	
	
	@Override
	public String getUrl() {
		return "http://www.mechon-mamre.org/i/" + BOOK + HALACHOT + CHAPTER + ".htm";
	}

	@Override
	public void reachedLevel(int level, String value) {
		switch(level) {
		case 2: // increment BOOK (when reached first HALACHOT in a book) and HALACHOT
			if(isInArray(FIRST_BOOKS, value)) {
				// handling exceptions...
				if(value.equals(FIRST_BOOK_IN_SEFER_TAHARA))
					BOOK = "a";
				else if(value.equals(FIRST_BOOK_IN_SEFER_NEZIKIN))
					BOOK = "b";
				else if(value.equals(FIRST_BOOK_IN_SEFER_KINYAN))
					BOOK = "c";
				else if(value.equals(FIRST_BOOK_IN_SEFER_MISHPATIM))
					BOOK = "d";
				else if(value.equals(FIRST_BOOK_IN_SEFER_SHOFTIM))
					BOOK = "e";
				else
					BOOK = increment(BOOK);
				
				HALACHOT = "0"; // reset HALACHOT when a new BOOK is reached
			}
			if(value.equals(HALACHOT_MEGILA)) // exception, the only case where we have ten halachot in a sefer...
				HALACHOT = "a";
			else
				HALACHOT = increment(HALACHOT);
			// reset CHAPTER
			CHAPTER = "00";
			break;
		case 3:
			if(CHAPTER.startsWith("0")) {
				String newChapter = increment(removeFirstChar(CHAPTER));
				if(toInt(newChapter) == 10)
					CHAPTER = "10";
				else // < 10
					CHAPTER =  "0" + newChapter;
			} else {
				CHAPTER = increment(CHAPTER);
			}
			break;
		}

	}

}
