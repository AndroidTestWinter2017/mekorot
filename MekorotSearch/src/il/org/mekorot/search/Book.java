package il.org.mekorot.search;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private String name;
	
	public String getName() {
		return name;
	}

	public Book(String name) {
		this.name = name;
	}

	/**
	 * Given a path in the book tree e.g. {"Torah", "Bereshit"}, returns
	 * the children of the last node e.g. {"Perek A", "Perek B", ...}. The the path does
	 * not exist in the tree returns an empty array.
	 * @param path
	 * @return
	 */
	public String[] getChildren(String[] path) {
		String[] emptyResult = new String[]{};
		//List<String> result = new ArrayList<String>();
		// a dummy implementation
		if(path.length == 0)
			return emptyResult;
		
		String bookName = path[0];
		if(bookName.equals("תורה"))
			return new String[]{"בראשית", "שמות", "ויקרא", "במדבר", "דברים"};
		if(bookName.equals("משנה תורה"))
			return new String[]{"המדע", "אהבה", "זמנים"};
		
		return emptyResult;
	}

}
