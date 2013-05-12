package il.org.mekorot.search;

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
public class SearchActivity extends Activity implements TextWatcher {
	public static final String URL = "il.org.mekorot.URL";
	private static final String SEPARATOR = ",";
	private AutoCompleteTextView bookView;
	private MultiAutoCompleteTextView pathView;
	private ArrayAdapter<String> pathAdapter;
	private ArrayAdapter<String> emptyAdapter;
	/**
	 * The book that was chosen by the user
	 */
	private Book book = BookRepository.getEmptyBook();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		bookView = (AutoCompleteTextView) findViewById(R.id.book);
		pathView = (MultiAutoCompleteTextView) findViewById(R.id.path);
		
		emptyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, new String[]{});
		
		// setting the adapter for the book textview
		ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.ALL_BOOKS_HEB));
		bookView.setAdapter(bookAdapter);
		
		addBookViewTextChangedListener();
		
		// adapter of pathView is determined dynamically based on the book chosen and the current path
        pathView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        pathView.addTextChangedListener(this);
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
				updateBook();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	@Override
	public void afterTextChanged(Editable s) {		 
	}
	/**
	 * Ask the book repository for a book corresponding to the text
	 * entered in the book view.
	 */
	private void updateBook() {
		String bookName = bookView.getText().toString();
		book = BookRepository.getBook(bookName);
		
		if(BookRepository.isEmptyBook(book)) {
			pathAdapter = emptyAdapter;
			pathView.setAdapter(pathAdapter);
			return;
		}
		
		// update path adapter
		pathAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, book.getChildren(new String[]{bookName}));
		pathView.setAdapter(pathAdapter);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	private void separatorAdded() {
		
	}
	private void separatorRemoved() {
		
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

}
