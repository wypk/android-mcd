package es.shwebill.component.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemFontChecker {

    private static SystemFontChecker systemFontChecker;

    private SystemFontChecker() {
    }

    public static SystemFontChecker getInstance() {
        if (systemFontChecker == null) {
            systemFontChecker = new SystemFontChecker();
        }
        return systemFontChecker;
    }

    public final boolean isUnicode(Context mContext) {
        TextView textView = new TextView(mContext, null);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setText("က");
        textView.measure(-2, -2);
        int length1 = textView.getMeasuredWidth();
        textView.setText("က္က");
        textView.measure(-2, -2);
        int length2 = textView.getMeasuredWidth();
        return length1 == length2;
    }
}
