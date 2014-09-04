package model;

import java.net.URL;

import android.text.Spanned;

/**
 * @author rob
 * 
 * This class is used to store all the information about a given article
 * that is returned from an RSS feed. It is based on the RSS2.0 definition and
 * contains the variables for select core attributes in the schema
 *
 */
public class FeedItem {
	
	private long articleId;
	private long feedId;
	private String title;
	private String description;
	private String enclosure;
	private String pubDate;
	private String link;
	private String encodedContent;
	private Spanned text;
	/**
	 * @return the articleId
	 */
	public long getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}
	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param string the title to set
	 */
	public void setTitle(String string) {
		this.title = string;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param url the url to set
	 */
	public void setLink(String url) {
		this.link = url;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		
		//parse description for any image or video links
		if (description.contains("<img ")){
			String img  = description.substring(description.indexOf("<img "));
			String cleanUp = img.substring(0, img.indexOf(">")+1);
			img = img.substring(img.indexOf("src=") + 5);
			int indexOf = img.indexOf("'");
			if (indexOf==-1){
				indexOf = img.indexOf("\"");
			}
			img = img.substring(0, indexOf);
			
			setEnclosure(img);
			
			this.description = this.description.replace(cleanUp, "");
		}
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}
	/**
	 * @param encodedContent the encodedContent to set
	 */
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}
	/**
	 * @return the encodedContent
	 */
	public String getEncodedContent() {
		return encodedContent;
	}
	/**
	 * @param imgLink the imgLink to set
	 */
	public void setEnclosure(String imgLink) {
		this.enclosure = imgLink;
	}
	/**
	 * @return the imgLink
	 */
	public String getEnclosure() {
		return enclosure;
	}
	/**
	 * @return the link
	 */
	public Spanned getText() {
		return text;
	}
	/**
	 * @param url the url to set
	 */
	public void setText(Spanned text) {
		this.text = text;
	}

}
