package il.org.mekorot.search.test;

import il.org.mekorot.search.BookRepository;
import android.test.AndroidTestCase;

public class BookRepositoryTest extends AndroidTestCase {
	
	public void setUp() {
		BookRepository.setTestingMode(true);
	}
	public void tearDown() {
		BookRepository.setTestingMode(false);
	}
	public void test() {
		assertEquals(2, BookRepository.getNumberOfBooks());
	}
}
