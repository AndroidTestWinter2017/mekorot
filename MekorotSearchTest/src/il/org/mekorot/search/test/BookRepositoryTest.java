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
		System.out.println(res);
		assertTrue(Arrays.equals(new String[]{"בראשית", "שמות", "ויקרא", "במדבר", "דברים"}, res));
		assertTrue(Arrays.equals(new String[]{"פרק א", "פרק ב", "פרק ג", "פרק ד"}, torahBook.getChildren(new String[]{"תורה", "ויקרא"})));
		assertTrue(Arrays.equals(new String[]{"פרק א", "פרק ב"}, torahBook.getChildren(new String[]{"תורה", "במדבר"})));
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
}
