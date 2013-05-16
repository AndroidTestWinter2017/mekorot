package il.org.mekorot.search;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;

public class BookRepository {
	private static Book emptyBook = new Book("");
	private static final String REPOSITORY_DEPLOYMENT_DIR = "bookRepository";
	private static final String REPOSITORY_TEST_DIR = "bookRepositoryTest";
	private static String REPOSITORY_DIR = REPOSITORY_DEPLOYMENT_DIR;
	private static final String MAP_FILE = REPOSITORY_DIR + "books.map";
	private static final String BOOK_SUFFIX = ".book";
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
		AssetManager assets = context.getAssets();
		int result = 0;
		try {
			String[] files = assets.list(REPOSITORY_DIR);
			for(String file : files) {
				if(file.endsWith(BOOK_SUFFIX))
					result++;
			}
			assets.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
