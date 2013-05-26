package il.org.mekorot.search;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private static final String RANGE_CHARACTER = ">>";
	private Node root = new Node();

	private class Node {
		private String value;
		private List<Node> children = new ArrayList<Book.Node>();

		String getValue() {
			return value;
		}

		void setValue(String value) {
			this.value = value;
		}
		
		void addChild(Node node) {
			children.add(node);
		}
		String[] getChildren() {
			String[] result = new String[children.size()];
			for(int i=0; i < children.size(); i++)
				result[i] = children.get(i).getValue();
			
			return result;
		}
		Node getChild(String value) {
			for(Node node : children)
				if(node.value.equals(value))
					return node;
			
			return null;
		}
		/**
		 * Note: the depth is calculated based on the left branch of
		 * the tree. Since we expect all our branches to be in equal depth
		 * this should work fine.
		 * @param node
		 * @return
		 */
		public int getDepth() {
			if(children.size() == 0)
				return 1;
			else
				return 1 + getLeftChild().getDepth();
		}
		public Node getLeftChild() {
			if(children.size() == 0)
				return null;
			else
				return children.get(0);
		}

		public boolean isLegalPath(String[] path) {
			if(path == null || path.length != getDepth() || !path[0].equals(getValue()))
				return false;
			
			if(path.length == 1)
				return true;
			
			Node node = getChild(path[1]);
			if(node == null)
				return false;
			
			String[] newpath = new String[path.length -1];
			System.arraycopy(path, 1, newpath, 0, path.length-1);
			
			return node.isLegalPath(newpath);
		}
		
		
	}
	
	public String getName() {
		return root.getValue();
	}

	/**
	 * Initialize from a book file
	 * @param bookInputStream
	 */
	public Book(InputStream is) {
		if(is == null) { // return "empty book"
			root.setValue("");
			return;
		}
		
		is = preprocesssBookFile(is);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		buildTree(root, reader);
	}

	/**
	 * Returning an input stream where the special UNTIL markers are
	 * flattened. E.g., if a line "*** Perek A..D" exists in the given
	 * input stream then in the returned input stream will be 4 corresponding
	 * lines, one for each perek.
	 * @param is
	 * @return
	 */
	private InputStream preprocesssBookFile(InputStream is) {
		// TODO: currently we simply copy the lines. should flatten them.
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		
		String line;
		try {
			while((line = reader.readLine()) != null) {
				String lastWord = getLastWord(line);
				if(lastWordHasRangeCharacter(lastWord)) { // e.g. "*** Torah Perek A..B"
					String begin = lastWord.split(RANGE_CHARACTER)[0];
					String end = lastWord.split(RANGE_CHARACTER)[1];
					String[] range = new HebrewRange(begin, end, HebrewRange.ALEPH_BET_NUMBERING).getRange();
					String prefix = removeLastWord(line);
					for(String elem : range) {
						buffer.append(prefix + " " + elem);
						buffer.append("\n");
					}
						
				} else {
					buffer.append(line);
					buffer.append("\n");					
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return stringBufferToInputStream(buffer);
	}

	private String removeLastWord(String line) {
		int index = line.trim().lastIndexOf(" ");
		return line.trim().substring(0, index);
	}

	private boolean lastWordHasRangeCharacter(String word) {
		return word.contains(RANGE_CHARACTER);
	}

	private String getLastWord(String line) {
		String[] words = line.split(" ");
		return words[words.length -1];
	}

	private InputStream stringBufferToInputStream(StringBuffer buffer) {
		return new ByteArrayInputStream(buffer.toString().getBytes());
	}

	/**
	 * The next line to be read is the line holding the value for the
	 * parameter node.
	 * @param node
	 * @param reader
	 */
	private void buildTree(Node node, BufferedReader reader) {
		String line;
		int mylevel = -1;
		try {
			while(true) {
				// we mark this point since we may want to roll back
				reader.mark(100); // we don't expect one line to exceed 100 chars
				line = reader.readLine();
				if(line == null) break; // break if reached EOF
				if(line.equals("")) continue; // skip empty lines
				
				if(mylevel == -1) { // this is the first line where we set the value of the node
					mylevel = getTreeLevel(line);
					node.setValue(getContent(line));
					continue;
				}
				int currlevel = getTreeLevel(line);
				
				if(currlevel > mylevel ) { // line is one of node's children
					Node child = new Node();
					reader.reset();
					buildTree(child, reader);		
					node.addChild(child);
					continue;
				}
				
				if(currlevel == mylevel) { // i.e. our node has no children
					reader.reset(); // return current line to reader
					return;
				}
				
				if(currlevel < mylevel) {
					reader.reset();
					return;
				}
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the number of '*' the line begins with (the level in the tree).
	 * @param line
	 * @return
	 */
	public int getTreeLevel(String line) {
		return line.trim().lastIndexOf('*') + 1;
	}

	/**
	 * Removes any preceding '*', returning only the content of the line.
	 * @param line
	 * @return
	 */
	public String getContent(String line) {
		return line.substring(line.lastIndexOf('*') + 1).trim();
	}

	/**
	 * Given a path in the book tree e.g. {"Torah", "Bereshit"}, returns
	 * the children of the last node e.g. {"Perek A", "Perek B", ...}. If the path does
	 * not exist in the tree returns an empty array.
	 * @param path
	 * @return
	 */
	public String[] getChildren(String[] path) {
		if(path == null || path.length == 0)
			return new String[]{};
		
		return getChildren(root, path);
	}

	private String[] getChildren(Node node, String[] path) {
		if(!node.getValue().equals(path[0]))
			return new String[]{};
		
		if(path.length == 1)
			return node.getChildren();
		
		Node child = root.getChild(path[1]);
		if(child == null)
			return new String[]{};
		
		String[] newpath = new String[path.length -1];
		System.arraycopy(path, 1, newpath, 0, path.length-1);
		return getChildren(child, newpath);
		
	}

	/**
	 * Returns the height of the tree, how deep it is.
	 * @return
	 */
	public int getDepth() {
		return root.getDepth();
	}

	public boolean isLegalPath(String[] path) {
		return root.isLegalPath(path);
	}

}
