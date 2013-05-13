package il.org.mekorot.search;

public class BookRepository {
	private static Book emptyBook = new Book("");
	private static final String REPOSITORY_DEPLOYMENT_DIR = "books/";
	private static final String REPOSITORY_TEST_DIR = "books_test/";
	private static String REPOSITORY_DIR = REPOSITORY_DEPLOYMENT_DIR;
	private static final String MAP_FILE = REPOSITORY_DIR + "books.map";
	
	public static Book getEmptyBook() {
		return emptyBook;
	}
	public static void setTestingMode(boolean flag) {
		if(flag)
			REPOSITORY_DIR = REPOSITORY_TEST_DIR;
		else
			REPOSITORY_DIR = REPOSITORY_DEPLOYMENT_DIR;
	}
	/**
	 * If the book doesn't exist in the cache (TODO), creates it while
	 * initializing its content from a file. If there is no such corresponding file,
	 * an empty book is returned.
	 * @param name
	 * @return
	 */
	public static Book getBook(String name) {
		return new Book(name);
	}

	public static boolean isEmptyBook(Book book) {
		if(book.getName().equals(""))
			return true;
		else
			return false;
	}

	public static int getNumberOfBooks() {
		
		return 0;
	}

}
