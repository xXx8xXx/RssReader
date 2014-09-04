package rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.tmm.android.rssreader.R;

import adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RssActivity extends FragmentActivity implements
		ActionBar.TabListener {

	ActionBar actionbar;
	ViewPager viewpager;
	TabsPagerAdapter ft;
	// Tab titles
	private String[] tabs = { "Topky.sk", "SME.sk", "PC.sk" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_viewpager);
		viewpager = (ViewPager) findViewById(R.id.pager);
		ft = new TabsPagerAdapter(getSupportFragmentManager(), this);
		actionbar = getActionBar();
		viewpager.setAdapter(ft);
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.addTab(actionbar.newTab().setText("Topky.sk")
				.setTabListener(this));
		actionbar.addTab(actionbar.newTab().setText("SME.sk")
				.setTabListener(this));
		actionbar.addTab(actionbar.newTab().setText("PC.sk")
				.setTabListener(this));
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				actionbar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				actionbar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		viewpager.setCurrentItem(arg0.getPosition());
		//actionbar.setSelectedNavigationItem(arg0.getPosition());

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		viewpager.setCurrentItem(arg0.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}

/*
 * public class RssActivity extends ListActivity {
 * 
 * private RssListAdapter adapter;
 * 
 * @Override public void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); 
 * List<JSONObject> jobs = new ArrayList<JSONObject>(); 
 * try { 
 * 	jobs = RssReader.getLatestRssFeed(); 
 * } catch
 * (Exception e) { 
 * 	Log.e("RSS ERROR", "Error loading RSS Feed Stream >> " + e.getMessage() + " //" + e.toString()); 
 * }
 * 
 * adapter = new RssListAdapter(this,jobs); setListAdapter(adapter); }
 * 
 * }
 */