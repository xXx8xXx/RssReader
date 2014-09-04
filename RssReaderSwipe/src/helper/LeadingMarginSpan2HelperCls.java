package helper;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.style.LeadingMarginSpan.LeadingMarginSpan2;

public class LeadingMarginSpan2HelperCls implements LeadingMarginSpan2 {
	
	private int margin;
    private int lines;
	
	public LeadingMarginSpan2HelperCls(int lines, int margin) {
        this.margin = margin;
        this.lines = lines;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return first ? margin : 0;
    }

    @Override
    public int getLeadingMarginLineCount() {
        return lines;
    }

    /*@Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, 
            int top, int baseline, int bottom, CharSequence text, 
            int start, int end, boolean first, Layout layout) {}*/

	@Override
	public void drawLeadingMargin(Canvas arg0, Paint arg1, int arg2, int arg3,
			int arg4, int arg5, int arg6, CharSequence arg7, int arg8,
			int arg9, boolean arg10, Layout arg11) {
		// TODO Auto-generated method stub
		
	}

}