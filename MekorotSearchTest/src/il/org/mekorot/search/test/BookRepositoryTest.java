package il.org.mekorot.search.test;

import java.util.Arrays;

import il.org.mekorot.search.Book;
import il.org.mekorot.search.BookRepository;
import android.test.AndroidTestCase;

public class BookRepositoryTest extends AndroidTestCase {
	private BookRepository bookRepository;
	private Book tanachBook;
	private Book bavliBook;
	private Book rambamBook;
	private Book nevochimBook;
	private Book shulchanAruchBook;
	private static final String TORAH_BOOK_NAME = "תנ\"ך";
	private static final String BAVLI_BOOK_NAME = "תלמוד בבלי";
	private static final String RAMBAM_BOOK_NAME = "משנה תורה להרמב\"ם";
	private static final String NEVOCHIM_BOOK_NAME = "מורה הנבוכים";
	private static final String SHULCHAN_ARUCH_BOOK_NAME = "שולחן ערוך";
	
	public void setUp() {
		bookRepository = BookRepository.instance(getContext());
		tanachBook = bookRepository.getBook(TORAH_BOOK_NAME);
		bavliBook = bookRepository.getBook(BAVLI_BOOK_NAME);
		rambamBook = bookRepository.getBook(RAMBAM_BOOK_NAME);
		nevochimBook = bookRepository.getBook(NEVOCHIM_BOOK_NAME);
		shulchanAruchBook = bookRepository.getBook(SHULCHAN_ARUCH_BOOK_NAME);
	}
	
	public void test_getNumberOfBooks() {
		assertEquals(5, bookRepository.getNumberOfBooks());
	}
	public void test_getBook() {
		// for a non-existing book we expect an empty book, i.e., with a name ""
		Book book = bookRepository.getBook("bla bla");
		assertNull(book);
		
		// now we check our Tanach book
		assertEquals("תנ\"ך", tanachBook.getName());		
		assertEquals(39, tanachBook.getChildren(new String[]{"תנ\"ך"}).length);
		
		assertEquals(50, tanachBook.getChildren(new String[]{"תנ\"ך", "בראשית"}).length);
		assertEquals(40, tanachBook.getChildren(new String[]{"תנ\"ך", "שמות"}).length);
		assertEquals(27, tanachBook.getChildren(new String[]{"תנ\"ך", "ויקרא"}).length);
		assertEquals(36, tanachBook.getChildren(new String[]{"תנ\"ך", "במדבר"}).length);
		assertEquals(34, tanachBook.getChildren(new String[]{"תנ\"ך", "דברים"}).length);
		assertTrue(Arrays.equals(new String[]{}, tanachBook.getChildren(new String[]{"בלהבלה", "במדבר"})));
		assertTrue(Arrays.equals(new String[]{}, tanachBook.getChildren(new String[]{"תורה", "כשגכשדגכ"})));
		assertTrue(Arrays.equals(new String[]{}, tanachBook.getChildren(new String[]{"תורה", "בראשית", "פרק א"})));
	}
	
	public void test_getDepth() {
		assertEquals(3, tanachBook.getDepth());
	}
	public void test_isLegalPath() {
		assertFalse(tanachBook.isLegalPath(new String[]{}));
		assertFalse(tanachBook.isLegalPath(new String[]{"תנ\"ך", "בראשית"}));
		assertTrue(tanachBook.isLegalPath(new String[]{"תנ\"ך", "בראשית", "פרק א"}));
	}
	public void test_getUrl() {
		assertEquals("http://www.mechon-mamre.org/i/t/t0101.htm", tanachBook.getUrl(new String[]{"תנ\"ך", "בראשית", "פרק א"}));

		assertEquals("http://www.hebrewbooks.org/shas.aspx?mesechta=1&daf=2b&format=text", 
				bavliBook.getUrl(new String[]{"תלמוד בבלי", "ברכות", "דף ב:"}));
		assertEquals("http://www.hebrewbooks.org/shas.aspx?mesechta=12&daf=29&format=text", 
				bavliBook.getUrl(new String[]{"תלמוד בבלי", "מועד קטן", "דף כט."}));
		
		assertEquals("http://www.mechon-mamre.org/i/1104.htm",
				rambamBook.getUrl(new String[]{"משנה תורה להרמב\"ם", "יסודי התורה", "פרק ד"}));
		assertEquals("http://www.mechon-mamre.org/i/9410.htm",
				rambamBook.getUrl(new String[]{"משנה תורה להרמב\"ם", "שגגות", "פרק י"}));
		assertEquals("http://www.mechon-mamre.org/i/e512.htm",
				rambamBook.getUrl(new String[]{"משנה תורה להרמב\"ם", "מלכים ומלחמות", "פרק יב"}));
		assertEquals("http://www.mechon-mamre.org/i/3a04.htm",
				rambamBook.getUrl(new String[]{"משנה תורה להרמב\"ם", "מגלה וחנוכה", "פרק ד"}));
		
		assertEquals("http://press.tau.ac.il/perplexed/chapters/chap_2_08.htm",
				nevochimBook.getUrl(new String[]{"מורה הנבוכים", "חלק שני", "פרק ח"}));
		assertEquals("http://press.tau.ac.il/perplexed/chapters/chap_3_00L.htm",
				nevochimBook.getUrl(new String[]{"מורה הנבוכים", "חלק שלישי", "פרק הקדמה"}));
		
		assertEquals("http://he.wikisource.org/wiki/" + "שולחן_ערוך_אורח_חיים_א",
				shulchanAruchBook.getUrl(new String[]{"שולחן ערוך", "אורח חיים", "סימן א"}));
	}
}
