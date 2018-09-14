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

package wyp.mcd.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.marcoscg.licenser.Library;
import com.marcoscg.licenser.License;
import com.marcoscg.licenser.LicenserDialog;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.ui.BasicFragment;
import wyp.mcd.ui.activity.BrowseActivity;

public class AboutFragment extends BasicFragment {

    @BindView(R.id.lblScreenTitle)
    AppCompatTextView lblScreenTitle;

    @BindView(R.id.rlBrowse)
    RelativeLayout rlBrowse;

    @BindView(R.id.rlGithub)
    RelativeLayout rlGithub;

    @BindView(R.id.rlLibrariesCredit)
    RelativeLayout rlLibrariesCredit;

    @BindView(R.id.rlReportBugs)
    RelativeLayout rlReportBugs;

    @BindView(R.id.rlApp)
    RelativeLayout rlApp;

    @BindView(R.id.rlRate)
    RelativeLayout rlRate;

    @BindView(R.id.lblAppVersionName)
    AppCompatTextView lblAppVersionName;

    @Override
    public void createView() {
        lblScreenTitle.setText(Objects.requireNonNull(getActivity()).getResources().getString(R.string.title_about));
        lblAppVersionName.setText(String.format("%s %s", getString(R.string.version), AndroidUtil.getCurrentVersionName(getActivity())));
    }

    @Override
    public int getLayoutXmlId() {
        return R.layout.fragment_about;
    }

    @Override
    public void refresh() {
    }

    @OnClick(R.id.rlBrowse)
    public void goBrowse() {
        Intent intent = new Intent(getActivity(), BrowseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rlApp)
    public void goAppLanding() {
        showOnWebView("https://wyphyoe.github.io/mcd/");
    }

    @OnClick(R.id.rlGithub)
    public void goGithub() {
        showOnWebView("https://github.com/wyphyoe/mcd-android");
    }

    @OnClick(R.id.rlLibrariesCredit)
    public void showLibraries() {
        LicenserDialog licenserDialog = new LicenserDialog(Objects.requireNonNull(getActivity()))
                .setTitle("Licenses")
                .setCustomNoticeTitle("Notices for libraries")
                .setCancelable(true)
                .setLibrary(new Library("Licenser",
                        "https://github.com/marcoscgdev/Licenser",
                        License.MIT))
                .setLibrary(new Library("Alerter",
                        "https://github.com/Tapadoo/Alerter",
                        License.MIT))
                .setLibrary(new Library("Butterknife",
                        "https://github.com/JakeWharton/butterknife",
                        License.APACHE))
                .setLibrary(new Library("FinestWebView",
                        "https://github.com/TheFinestArtist/FinestWebView-Android",
                        License.MIT))
                .setLibrary(new Library("Gson",
                        "https://github.com/google/gson",
                        License.APACHE))
                .setLibrary(new Library("Rabbit",
                        "https://github.com/Rabbit-Converter/Rabbit",
                        License.APACHE))
                .setLibrary(new Library("FastScroll",
                        "https://github.com/L4Digital/FastScroll",
                        License.APACHE))
                .setLibrary(new Library("Lottie",
                        "https://github.com/airbnb/lottie-android",
                        License.APACHE))
                .setLibrary(new Library("JSoup",
                        "https://jsoup.org/",
                        License.APACHE))
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    // TODO:
                });
        licenserDialog.show();
    }

    @OnClick(R.id.rlReportBugs)
    public void reportBugs() {
        showOnWebView("https://github.com/wyphyoe/mcd-android/issues");
    }

    @OnClick(R.id.rlRate)
    public void rateOnGooglePlay() {
        AndroidUtil.goGooglePlayStore(getContext());
    }

    private void showOnWebView(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}

