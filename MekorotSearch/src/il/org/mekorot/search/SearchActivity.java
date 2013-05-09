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
	static final String[] BOOKS = new String[] {"בראשית", "שמות", "ויקרא", "במדבר", "דברים"};
	static final String[] CHAPTERS_1 = new String[] {"פרק א", "פרק ב"};
	static final String[] CHAPTERS_2 = new String[] {"פרק א", "פרק ב", "פרק ג", "פרק ד"};
	static final String[] CHAPTERS_3 = new String[] {"פרק א", "פרק ב", "פרק ג", "פרק ד", "פרק ה", "פרק ו"};
	static final String[] CHAPTERS_4 = new String[] {"פרק א", "פרק ב", "פרק ג", "פרק ד", "פרק ה", "פרק ו", "פרק ז", "פרק ח"};
	static final String[] CHAPTERS_5 = new String[] {"פרק א", "פרק ב", "פרק ג", "פרק ד", "פרק ה", "פרק ו", "פרק ז", "פרק ח", "פרק ט", "פרק י"};
	public static final String URL = "il.org.mekorot.URL";
	private static final String SEPARATOR = ",";
	private AutoCompleteTextView bookView;
	private MultiAutoCompleteTextView pathView;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		getActionBar().setDisplayShowTitleEnabled(false); // requires use of API level 11, see @TargetApi annotation above
		
		bookView = (AutoCompleteTextView) findViewById(R.id.book);
		pathView = (MultiAutoCompleteTextView) findViewById(R.id.path);
		
		// setting the adapter for the book textview
		ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.ALL_BOOKS_HEB));
		bookView.setAdapter(bookAdapter);
		
		adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, BOOKS);
//		adapter2 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, CHAPTERS_1);
		
		pathView.setAdapter(adapter1);
        pathView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        pathView.addTextChangedListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	@Override
	public void afterTextChanged(Editable s) {
		String input = pathView.getText().toString();
		
		int numberOfCommas = input.length() - input.replace(",", "").length();
		
		if(numberOfCommas == 0)
			pathView.setAdapter(adapter1);
		else if(numberOfCommas == 1)
			pathView.setAdapter(adapter2);		 
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

}
