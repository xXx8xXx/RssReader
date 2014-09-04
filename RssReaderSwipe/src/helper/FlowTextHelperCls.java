package helper;

import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FlowTextHelperCls {
	
	private static boolean mNewClassAvailable;

    static {
        if (Integer.valueOf(Build.VERSION.SDK) >= 8) { // Froyo 2.2, API level 8
        	mNewClassAvailable = true;
        }

        // Also you can use this trick if you don't know the exact version:
        try {
           Class.forName("android.text.style.LeadingMarginSpan$LeadingMarginSpan2");
           mNewClassAvailable = true;
        } catch (Exception ex) {
           mNewClassAvailable = false;
        }
    }

    public static void tryFlowText(Spanned spTitle, View thumbnailView, TextView messageView, Display display){
        // There is nothing I can do for older versions, so just return
        if(!mNewClassAvailable) return;

        // Get height and width of the image and height of the text line
        thumbnailView.measure(display.getWidth(), display.getHeight());
        int height = thumbnailView.getMeasuredHeight();
        int width = thumbnailView.getMeasuredWidth();
        float textLineHeight = messageView.getPaint().getTextSize();

        // Set the span according to the number of lines and width of the image
        int lines = (int)Math.round(height / textLineHeight);
        //For an html text you can use this line: SpannableStringBuilder ss = (SpannableStringBuilder)Html.fromHtml(text);
        SpannableString ss = new SpannableString(spTitle);
        ss.setSpan(new LeadingMarginSpan2HelperCls(lines, width), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //ss.setSpan(new LeadingMarginSpan2HelperCls(lines, width), 0, lines, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        messageView.setText(ss);

        // Align the text with the image by removing the rule that the text is to the right of the image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)messageView.getLayoutParams();
        int[]rules = params.getRules();
        rules[RelativeLayout.RIGHT_OF] = 0;
    }

}