/*
 * Copyright (C) 2018
 *  Source code is created by Elissa Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package wyp.mcd.component.ui;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import wyp.mcd.R;

public class LanguageOptionsPopupMenu {

    private Context mContext;
    private LanguageOptionsPopupMenu.LanguageOptionsPopupMenuListener languageOptionsPopupMenuListener;

    public LanguageOptionsPopupMenu(Context mContext, LanguageOptionsPopupMenuListener languageOptionsPopupMenuListener) {
        this.mContext = mContext;
        this.languageOptionsPopupMenuListener = languageOptionsPopupMenuListener;
    }

    public void showLanguageOptionsMenu(View view) {
        android.support.v7.widget.PopupMenu popup = new android.support.v7.widget.PopupMenu(mContext, view);
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popup.getMenuInflater().inflate(R.menu.language_options_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_eng_to_mm:
                    languageOptionsPopupMenuListener.actionEngToMM();
                    break;
                case R.id.action_eng_to_eng:
                    languageOptionsPopupMenuListener.actionEngToEng();
                    break;
            }
            return true;
        });
        popup.show();
    }

    public interface LanguageOptionsPopupMenuListener {

        void actionEngToMM();

        void actionEngToEng();
    }

}
