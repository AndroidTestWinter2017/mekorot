package il.org.mekorot.search;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ResultActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		if(android.os.Build.VERSION.SDK_INT >= 11)
			getActionBar().hide();
		String url = getIntent().getStringExtra(SearchActivity.URL);
		WebView webview = (WebView)findViewById(R.id.webview);
		
		// fit to screen. http://stackoverflow.com/questions/3916330/android-webview-webpage-should-fit-the-device-screen
		webview.getSettings().setLoadWithOverviewMode(true);
	    webview.getSettings().setUseWideViewPort(true);
	    // enable zooming. http://stackoverflow.com/questions/7121053/how-to-enable-zoom-controls-and-pinch-zoom-in-a-webview
	    webview.getSettings().setBuiltInZoomControls(true);
	    // open links in the webview
	    webview.setWebViewClient(new WebViewClient());
		webview.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
