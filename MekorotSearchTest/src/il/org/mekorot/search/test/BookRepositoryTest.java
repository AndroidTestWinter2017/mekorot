package il.org.mekorot.search.test;

import il.org.mekorot.search.BookRepository;
import android.test.AndroidTestCase;

public class BookRepositoryTest extends AndroidTestCase {
	private BookRepository bookRepository;
	
	public void setUp() {
		bookRepository = BookRepository.instance(getContext());
		bookRepository.setTestingMode(true);
	}
	public void tearDown() {
		bookRepository.setTestingMode(false);
	}
	public void test() {
		assertEquals(1, bookRepository.getNumberOfBooks());
	}
}
