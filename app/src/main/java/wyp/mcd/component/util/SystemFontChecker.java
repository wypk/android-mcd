/*
 * Copyright (C) 2018
 *
 * Source code is created by Elissa Software
 * Dictionary data is owned by UCST
 * Database is implemented by Salai Chit Oo Latt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.component.util;

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
