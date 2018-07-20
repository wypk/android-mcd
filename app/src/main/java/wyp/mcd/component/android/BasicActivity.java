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
package wyp.mcd.component.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.WindowManager;

import butterknife.ButterKnife;
import wyp.mcd.R;
import wyp.mcd.component.network.NetworkStatusBroadcastReceiver;
import wyp.mcd.component.util.TransitionUtil;
import wyp.mcd.ui.uicomponents.EsProgressDialog;

public abstract class BasicActivity extends AppCompatActivity implements NetworkStatusBroadcastReceiver.NetworkStatusListener {

    private EsProgressDialog progressDialog = EsProgressDialog.getInstance();
    private NetworkStatusBroadcastReceiver networkStatusBroadcastReceiver = null;

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        if (this.isTaskRoot()) {
            if (this.getDefaultBackScreenIfRoot() != null) {

                TransitionUtil.showNextActivityWithTransitionAsRoot(
                        this, this.getDefaultBackScreenIfRoot(),
                        this.getDefaultBackScreenExtraIfRoot(),
                        R.anim.entering_screen_sliding_up,
                        R.anim.exiting_screen_sliding_down, true);
                this.finish();
            }
        } else {
            this.overridePendingTransition(
                    R.anim.entering_screen_sliding_up,
                    R.anim.exiting_screen_sliding_down);
        }
    }

    @Override
    public void onInternetAvailable() {
    }

    @Override
    public void onInternetUnavailable() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(null);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        this.setContentView(this.getLayoutFileId());

        /*
         * Bind Butter knife
         */
        ButterKnife.bind(this);

        /*
         * Check the screen got lblScreenTitle or not.
         */
        if (this.findViewById(R.id.lblScreenTitle) != null) {
            AppCompatTextView lblScreenTitle = this.findViewById(R.id.lblScreenTitle);
            lblScreenTitle.setText(this.getScreenTitleAtStart());
        }

        if (this.findViewById(R.id.btnBack) != null) {
            AppCompatImageButton btnBack = this.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(view -> this.onBackPressed());
        }

        /*
         * NetworkStatusBroadcastReceiver
         */
        this.networkStatusBroadcastReceiver = new NetworkStatusBroadcastReceiver(this, this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        this.networkStatusBroadcastReceiver.unregisterFromContext();
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.networkStatusBroadcastReceiver.registerToContext();
    }

    protected abstract int getLayoutFileId();

    protected abstract int getRootLayoutId();

    protected abstract String getScreenTitleAtStart();

    protected abstract Class<? extends BasicActivity> getDefaultBackScreenIfRoot();

    protected abstract Bundle getDefaultBackScreenExtraIfRoot();

    /**
     * show progress bar on UI.
     */
    public void showProgressBar() {
        progressDialog.showProgressDialog(this);
    }

    /**
     * hide progress bar on UI.
     */
    public void hideProgressBar() {
        progressDialog.hideProgressDialog();
    }

}
