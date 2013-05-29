package il.org.mekorot.search.test;

import java.util.Arrays;

import il.org.mekorot.search.Book;
import il.org.mekorot.search.BookRepository;
import android.test.AndroidTestCase;

public class BookRepositoryTest extends AndroidTestCase {
	private BookRepository bookRepository;
	private Book torahBook;
	private static final String TORAH_BOOK_NAME = "תורה";
	
	public void setUp() {
		bookRepository = BookRepository.instance(getContext(), true);
		torahBook = bookRepository.getBook(TORAH_BOOK_NAME);
	}
	
	public void test_getNumberOfBooks() {
		assertEquals(1, bookRepository.getNumberOfBooks());
	}
	public void test_getBook() {
		// for a non-existing book we expect an empty book, i.e., with a name ""
		Book book = bookRepository.getBook("bla bla");
		assertNull(book);
		
		// now we check our Torah book
		assertEquals("תורה", torahBook.getName());
		String[] res = torahBook.getChildren(new String[]{"תורה"});
		
		assertTrue(Arrays.equals(new String[]{"בראשית", "שמות", "ויקרא", "במדבר", "דברים"}, res));
		
		assertEquals(50, torahBook.getChildren(new String[]{"תורה", "בראשית"}).length);
		assertEquals(40, torahBook.getChildren(new String[]{"תורה", "שמות"}).length);
		assertEquals(27, torahBook.getChildren(new String[]{"תורה", "ויקרא"}).length);
		assertEquals(36, torahBook.getChildren(new String[]{"תורה", "במדבר"}).length);
		assertEquals(34, torahBook.getChildren(new String[]{"תורה", "דברים"}).length);
		assertTrue(Arrays.equals(new String[]{}, torahBook.getChildren(new String[]{"בלהבלה", "במדבר"})));
		assertTrue(Arrays.equals(new String[]{}, torahBook.getChildren(new String[]{"תורה", "כשגכשדגכ"})));
		assertTrue(Arrays.equals(new String[]{}, torahBook.getChildren(new String[]{"תורה", "בראשית", "פרק א"})));
	}
	
	public void test_getAllBooks() {
		assertEquals(4, bookRepository.getAllBooks().length);
	}
	
	public void test_getDepth() {
		assertEquals(3, torahBook.getDepth());
	}
	public void test_isLegalPath() {
		assertFalse(torahBook.isLegalPath(new String[]{}));
		assertFalse(torahBook.isLegalPath(new String[]{"תורה", "בראשית"}));
		assertTrue(torahBook.isLegalPath(new String[]{"תורה", "בראשית", "פרק א"}));
	}
	public void test_getUrl() {
		assertEquals("http://www.mechon-mamre.org/i/t/t0101.htm", torahBook.getUrl(new String[]{"תורה", "בראשית", "פרק א"}));
		//add more tests and improve design.
	}
}
