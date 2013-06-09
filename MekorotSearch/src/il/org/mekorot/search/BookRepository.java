package il.org.mekorot.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;

public class BookRepository {
	private static String REPOSITORY_DIR = "bookRepository";
	private static String MAP_FILE = "books.map";
	private static final String BOOK_SUFFIX = ".book";
	private static BookRepository instance;
	private Map<String, Book> bookCache;
	private static final int BOOK_CACHE_SIZE = 5;
	private Context context;
	private BooksMap booksMap;
	
	/**
	 * Interface for the books.map file
	 * @author oren
	 *
	 */
	private class BooksMap {
		private Map<String, String> map;
		private static final String SEPARATOR = "::";
		
		BooksMap() {
			initMap();
		}
		/**
		 * Returns the simple filename e.g. 1.book (without the containing folder).
		 * @param bookName
		 * @return
		 */
		public String getBookFileName(String bookName) {
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
					// we assume a line is in the form BOOK_NAME SEPARATOR FILE_NAME e.g., torah :: 1.book
					String[] words = line.split(SEPARATOR);
					map.put(words[0].trim(), words[1].trim());
				}
				is.close(); reader.close(); // do we need all closing? should not hurt...
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		public String[] getAllBooks() {
			return map.keySet().toArray(new String[]{});
		}
	}
	
	private BookRepository(Context context) {
		this.context = context;
		booksMap = new BooksMap();
		bookCache = new HashMap<String, Book>();
	}
	
	public static BookRepository instance(Context context) {
		if(instance == null)
			instance = new BookRepository(context);
		
		return instance;
	}

	/**
	 * If the book doesn't exist in the cache (TODO), creates it while
	 * initializing its content from a file. If there is no such corresponding file,
	 * null is returned.
	 * @param name
	 * @return
	 */
	public Book getBook(String name) {
		Book book = getBookFromCache(name);
		if(book != null)
			return book;
		
		String fileName = booksMap.getBookFileName(name);
		if(fileName == null)
			return null;
		
		book = new Book(getBookInputStream(fileName), name);
		insertBookToCache(book);
		return book;
	}

	private void insertBookToCache(Book book) {
		// return if book already exists in cache
		if(bookCache.get(book.getName()) != null)
				return;
		// insert if cache is less then maximal size
		if(bookCache.size() < BOOK_CACHE_SIZE)
			bookCache.put(book.getName(), book);
		
		// TODO: size = BOOK_CACHE_SIZE
		// should decide element to remove...
	}

	private Book getBookFromCache(String name) {
		return bookCache.get(name);
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

	public String[] getAllBooks() {
		return booksMap.getAllBooks();
	}

}
