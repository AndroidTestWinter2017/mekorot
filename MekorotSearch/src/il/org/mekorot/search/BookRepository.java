package il.org.mekorot.search;

import android.content.Context;

public class BookRepository {
	private static Book emptyBook = new Book("");
	private static final String REPOSITORY_DEPLOYMENT_DIR = "books/";
	private static final String REPOSITORY_TEST_DIR = "books_test/";
	private static String REPOSITORY_DIR = REPOSITORY_DEPLOYMENT_DIR;
	private static final String MAP_FILE = REPOSITORY_DIR + "books.map";
	private static BookRepository instance;
	private Context context;
	
	private BookRepository(Context context) {
		this.context = context;
	}
	
	public static BookRepository instance(Context context) {
		if(instance == null)
			instance = new BookRepository(context);
		
		return instance;
	}
	
	public Book getEmptyBook() {
		return emptyBook;
	}
	/**
	 * In testing mode (flag=true) book files that are located in
	 * a dedicated testing area are accessed. 
	 * @param flag
	 */
	public void setTestingMode(boolean flag) {
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
	public Book getBook(String name) {
		return new Book(name);
	}

	public boolean isEmptyBook(Book book) {
		if(book.getName().equals(""))
			return true;
		else
			return false;
	}

	public int getNumberOfBooks() {
		
		return 0;
	}

}
