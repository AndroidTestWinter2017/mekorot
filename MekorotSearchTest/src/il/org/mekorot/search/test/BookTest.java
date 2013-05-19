package il.org.mekorot.search.test;

import il.org.mekorot.search.Book;
import android.test.AndroidTestCase;

public class BookTest extends AndroidTestCase {
	Book book;
	
	public void setUp() {
		book = new Book(null);
	}
	
	public void test_getTreeLevel() {
		assertEquals(1, book.getTreeLevel("*   torah..."));
		assertEquals(2, book.getTreeLevel("  **   torah..."));
		assertEquals(3, book.getTreeLevel("***  torah..."));
		assertEquals(0, book.getTreeLevel("torah..."));
	}
	public void test_getContent() {
		assertEquals("torah...", book.getContent("**   torah..."));
		assertEquals("torah...", book.getContent("  ** torah..."));
		assertEquals("bereshit", book.getContent("  ** bereshit"));
	}
}
