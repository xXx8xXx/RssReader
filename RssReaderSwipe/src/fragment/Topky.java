package fragment;

import java.util.ArrayList;
import java.util.List;

import adapter.RssListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import model.FeedItem;

import org.json.JSONObject;

import reader.RssReader;
import rssreader.WebViewWindow;

import com.tmm.android.rssreader.R;
import com.tmm.android.rssreader.R.id;
import com.tmm.android.rssreader.R.layout;

public class Topky extends android.support.v4.app.Fragment {

	private RssListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_topky,container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		List<FeedItem> jobs = new ArrayList<FeedItem>(); 
		try { 
			jobs = RssReader.getLatestRssFeed(); 
		} catch (Exception e) { 
			Log.e("RSS ERROR", "Error loading RSS Feed Stream >> " + e.getMessage() + " //" + e.toString()); 
		}
		
		final ListView lstView = (ListView)getView().findViewById(R.id.topky_list);
		adapter = new RssListAdapter(getActivity(), jobs);
		lstView.setAdapter(adapter);
		
		// ListView onclicklistener
		lstView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				FeedItem fi = (FeedItem)lstView.getItemAtPosition(pos);
				//WebViewWindow wvw = new WebViewWindow(fi.getLink());
				Intent intent = new Intent(getActivity(), WebViewWindow.class);
				intent.putExtra("url", fi.getLink());
				startActivity(intent);
				/*View webv = LayoutInflater.from(getActivity()).inflate(R.layout.webview, null);
				WebView wv = (WebView) webv.findViewById(R.id.webview);
				wv.loadUrl(fi.getLink());
				wv.bringToFront();
				wv.startLayoutAnimation();*/
				//Toast.makeText(getActivity(), fi.getTitle(), Toast.LENGTH_LONG).show();
			}
		});
	}

}