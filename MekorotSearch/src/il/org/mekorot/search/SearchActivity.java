package il.org.mekorot.search;

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SearchActivity extends Activity {
	public static final String URL = "il.org.mekorot.URL";
	private static final String SEPARATOR = ",";
	private AutoCompleteTextView bookView;
	private MultiAutoCompleteTextView pathView;
	private ArrayAdapter<String> pathAdapter;
	private BookRepository bookRepository;
	/**
	 * The book that was chosen by the user
	 */
	private Book book;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		bookRepository = BookRepository.instance(this);
		
		// get the book view and set adapter with all available books
		bookView = (AutoCompleteTextView) findViewById(R.id.book);
		ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, bookRepository.getAllBooks());
		bookView.setAdapter(bookAdapter);
		addBookViewTextChangedListener();
		
		// adapter of pathView is determined dynamically based on the book chosen and the current path
		pathView = (MultiAutoCompleteTextView) findViewById(R.id.path);
        pathView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        addPathViewTextChangedListener();
	}

	private void addPathViewTextChangedListener() {
		pathView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				afterInputChanged();
			}

		});
		
	}

	private void addBookViewTextChangedListener() {
		bookView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				afterInputChanged();
			}
		});
		
	}
	/**
	 * Returns the book name as well as the rest path components.
	 * E.g., {"Torah", "Bereshit", "Perek A"}
	 * @return
	 */
	private String[] getFullPath() {
		List<String> result = new ArrayList<String>();
		result.add(getBookName());
		String[] path = getPath();
		if(path.length == 0)
			return result.toArray(new String[]{});
		
		for(String elem : path) {
			if(elem.equals("") || elem.equals(" ")) continue; // last path element is a space which we would like to drop
			result.add(elem.trim());
		}
		
		return result.toArray(new String[]{});
	}
	/**
	 * Note that for some reason, if the path is empty then an
	 * array of length one is returned (with an empty string). Is that
	 * the way split works?
	 * @return
	 */
	private String[] getPath() {
		return pathView.getText().toString().split(SEPARATOR);
	}
	private String getBookName() {
		return bookView.getText().toString().trim();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	private void updatePathAdapter(String[] content) {
		pathAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, content);
		pathView.setAdapter(pathAdapter);
	}
	
	public void searchButtonPressed(View view) {
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra(URL, "http://www.mechon-mamre.org/i/7611n.htm");
		startActivity(intent);
	}
	public void deleteButtonPressed(View view) {
		switch(view.getId()) {
		case R.id.book_delete_button:
			bookView.setText("");
			pathView.setText("");
			bookView.requestFocus();
			break;
		case R.id.path_delete_button:
			pathView.setText("");
			pathView.requestFocus();
			break;
		}
	}

	private void afterInputChanged() {
		// do we need to update the book?
		String bookName = bookView.getText().toString();
		if(book == null || !bookName.equals(book.getName()))
			book = bookRepository.getBook(bookName);
		
		if(book == null)
			return;
		
		String[] fullPath = getFullPath();
		String[] children = book.getChildren(fullPath);
		if(children.length != 0)
			updatePathAdapter(children);
	}

}
