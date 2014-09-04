/**
 * 
 */
package reader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.FeedItem;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

public class RssReader {
	
	private final static String BOLD_OPEN = "<B>";
	private final static String BOLD_CLOSE = "</B>";
	private final static String BREAK = "<BR>";
	private final static String ITALIC_OPEN = "<I>";
	private final static String ITALIC_CLOSE = "</I>";
	private final static String SMALL_OPEN = "<SMALL>";
	private final static String SMALL_CLOSE = "</SMALL>";

	/**
	 * This method defines a feed URL and then calles our SAX Handler to read the article list
	 * from the stream
	 * 
	 * @return List<JSONObject> - suitable for the List View activity
	 */
	public static List<FeedItem> getLatestRssFeed(){
		String feed = "http://www.topky.sk/rss/10/Spravy_-_Domace.rss";
		RSSHandler rh = new RSSHandler();
		List<FeedItem> articles =  rh.getLatestArticles(feed);
		Log.e("RSS ERROR", "Number of articles " + articles.size());
		
		for (FeedItem article : articles) {
            String title = article.getTitle();
			String description = article.getDescription();
			String date = article.getPubDate();
			String imgLink = article.getEnclosure();
			String link = article.getLink();
			
			StringBuffer sb = new StringBuffer();
			sb.append(BOLD_OPEN).append(title).append(BOLD_CLOSE);
			sb.append(BREAK);
			sb.append(SMALL_OPEN).append(ITALIC_OPEN).append(date).append(ITALIC_CLOSE).append(SMALL_CLOSE);
			sb.append(BREAK);
			sb.append(description);
			//String ahref = " <a href=\"" + link + "\"" + ">Článok</a>"; 
			//sb.append(ahref);
			
			article.setText(Html.fromHtml(sb.toString()));
        }
		
		return articles;
	}
	
}