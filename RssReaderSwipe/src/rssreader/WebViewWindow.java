package rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewWindow extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		WebView wv = (WebView) findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClient());
		wv.setHorizontalScrollBarEnabled(true);
		wv.setVerticalScrollBarEnabled(true);
		wv.setScrollbarFadingEnabled(false);
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		
		WebSettings ws = wv.getSettings();
		ws.setBuiltInZoomControls(true);
		
		wv.loadUrl(getIntent().getStringExtra("url"));
	}
	
}