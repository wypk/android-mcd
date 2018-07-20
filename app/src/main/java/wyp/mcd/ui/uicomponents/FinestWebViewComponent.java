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

package wyp.mcd.ui.uicomponents;

import android.content.Context;
import android.view.Gravity;

import com.thefinestartist.finestwebview.FinestWebView;

import wyp.mcd.R;

public class FinestWebViewComponent {

    public static void showWithFinestWebView(Context mContext, String title, String url) {

        new FinestWebView.Builder(mContext)
                .theme(R.style.FinestWebViewTheme)
                .titleDefault(title)
                .showUrl(false)
                .statusBarColorRes(R.color.colorPrimaryDark)
                .toolbarColorRes(R.color.finestWhite)
                .titleColorRes(R.color.colorPrimaryDark)
                .urlColorRes(R.color.colorAccent)
                .iconDefaultColorRes(R.color.colorPrimaryDark)
                .progressBarColorRes(R.color.colorPrimaryDark)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .showSwipeRefreshLayout(true)
                .swipeRefreshColorRes(R.color.colorPrimaryDark)
                .menuSelector(R.drawable.selector_light_theme)
                .menuTextGravity(Gravity.CENTER)
                .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                .dividerHeight(0)
                .gradientDivider(false)
                .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                .show(url);
    }
}
