package il.org.mekorot.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;

public class BookRepository {
	private static Book emptyBook = new Book(null);
	private static final String REPOSITORY_DEPLOYMENT_DIR = "bookRepository";
	private static final String REPOSITORY_TEST_DIR = "bookRepositoryTest";
	private static String REPOSITORY_DIR = REPOSITORY_DEPLOYMENT_DIR;
	private static String MAP_FILE = "books.map";
	private static final String BOOK_SUFFIX = ".book";
	private static BookRepository instance;
	private Context context;
	private BooksMap booksMap = new BooksMap();
	
	/**
	 * Interface for the books.map file
	 * @author oren
	 *
	 */
	private class BooksMap {
		private Map<String, String> map;
		
		/**
		 * Returns the simple filename e.g. 1.book (without the containing folder).
		 * @param bookName
		 * @return
		 */
		public String getBookFileName(String bookName) {
			if(map == null) initMap();
			return map.get(bookName);
		}
		private void initMap() {
			map = new HashMap<String, String>();
			AssetManager assets = context.getAssets();
			try {
				String line;
				InputStream is = assets.open(REPOSITORY_DIR + "/" + MAP_FILE);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				while((line = reader.readLine()) != null) {
					if(line.equals("")) continue;
					// we assume a line is in the form BOOK_NAME FILE_NAME e.g., torah 1.book
					String[] words = line.split(" ");
					map.put(words[0].trim(), words[1].trim());
				}
				is.close(); reader.close(); // do we need all closing? should not hurt...
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
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
		String fileName = booksMap.getBookFileName(name);
		if(fileName == null)
			return emptyBook;
		
		return new Book(getBookInputStream(fileName));
	}

	/**
	 * Returns an input stream for a book file that exists under assets.
	 * @param fileName
	 * @return
	 */
	private InputStream getBookInputStream(String fileName) {
		AssetManager assets = context.getAssets();
		try {
			InputStream res = assets.open(REPOSITORY_DIR + "/" + fileName);
			return res;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
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
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
