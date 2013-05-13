package il.org.mekorot.search.test;

import static org.junit.Assert.*;
import il.org.mekorot.search.BookRepository;

import org.junit.Test;

public class BookRepositoryTest {

	@Test
	public void test() {
		assertEquals(1, BookRepository.getNumberOfBooks());
	}

}
