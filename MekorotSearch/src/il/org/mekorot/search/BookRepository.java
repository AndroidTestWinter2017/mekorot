package il.org.mekorot.search;

public class BookRepository {
	private static Book emptyBook = new Book("");
	
	public static Book getEmptyBook() {
		return emptyBook;
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

}
