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

package wyp.mcd.ui.uicomponents;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import wyp.mcd.R;

public class PopupMenu {

    private Context mContext;
    private PopupMenu.PopupMenuListener popupMenuListener;

    public PopupMenu(Context mContext, PopupMenuListener popupMenuListener) {
        this.mContext = mContext;
        this.popupMenuListener = popupMenuListener;
    }

    public void showPopupMenu(View view) {
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
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search_in_google:
                    popupMenuListener.goToGoogleSearch();
                    break;
                case R.id.search_in_wikipedia:
                    popupMenuListener.goToWikipediaSearch();
                    break;
                case R.id.share:
                    popupMenuListener.shareTranslation();
                    break;
            }
            return true;
        });
        popup.show();
    }

    public interface PopupMenuListener {

        void goToGoogleSearch();

        void goToWikipediaSearch();

        void shareTranslation();
    }
}
