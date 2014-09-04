package adapter;

import helper.FlowTextHelperCls;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import model.FeedItem;

import org.json.JSONException;
import org.json.JSONObject;

import com.tmm.android.rssreader.R;
import com.tmm.android.rssreader.R.id;
import com.tmm.android.rssreader.R.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class RssListAdapter extends ArrayAdapter<FeedItem> {
	
	static {
		
	}

	public RssListAdapter(Activity activity, List<FeedItem> imageAndTexts) {
		super(activity, 0, imageAndTexts);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		// Inflate the views from XML
		View rowView = inflater.inflate(R.layout.image_text_layout, null);
		FeedItem jsonImageText = getItem(position);
		
		//TextView description = (TextView) rowView.findViewById(R.id.description);
		TextView texttitle = (TextView) rowView.findViewById(R.id.title);
		//TextView pubDate = (TextView) rowView.findViewById(R.id.pubDate);
		//TextView link = (TextView) rowView.findViewById(R.id.link);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.feed_image);
		
		//texttitle.setMovementMethod(LinkMovementMethod.getInstance());
		
        try {
        	
        	if (jsonImageText.getEnclosure() != null){
        		
        		System.out.println("XXXX Link found!");
        		String url = (String) jsonImageText.getEnclosure();
                URL feedImage= new URL(url);
            	
            	HttpURLConnection conn= (HttpURLConnection)feedImage.openConnection();
                InputStream is = conn.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(is);
                imageView.setImageBitmap(img);
        	}
        	
        	Spanned text = jsonImageText.getText();
        	
        	Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        	FlowTextHelperCls.tryFlowText(text, imageView, texttitle, display);

        } catch (MalformedURLException e) {
        	//handle exception here - in case of invalid URL being parsed
        	//from the RSS feed item
        }
        catch (IOException e) {
        	//handle exception here - maybe no access to web
        }

		return rowView;

	} 

}