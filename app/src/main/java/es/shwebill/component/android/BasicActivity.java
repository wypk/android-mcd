package es.shwebill.component.android;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

import butterknife.ButterKnife;
import es.shwebill.R;
import es.shwebill.component.network.NetworkStatusBroadcastReceiver;
import es.shwebill.component.util.LanguageHelper;
import es.shwebill.component.util.Logger;
import es.shwebill.component.util.SystemFontChecker;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.ui.uicomponent.EsProgressDialog;

public abstract class BasicActivity extends AppCompatActivity implements NetworkStatusBroadcastReceiver.NetworkStatusListener {

    private EsProgressDialog progressDialog = EsProgressDialog.getInstance();
    private NetworkStatusBroadcastReceiver networkStatusBroadcastReceiver = null;
    private SystemFontChecker fontChecker;

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        if (this.isTaskRoot()) {
            if (this.getDefaultBackScreenIfRoot() != null) {
                TransitionUtil.showNextActivityWithTransitionAsRoot(
                        this, this.getDefaultBackScreenIfRoot(),
                        this.getDefaultBackScreenExtraIfRoot(),
                        R.anim.entering_screen_sliding_from_left,
                        R.anim.exiting_screen_sliding_to_right, true);
                this.finish();
            }
        } else {
            this.overridePendingTransition(
                    R.anim.entering_screen_sliding_from_left,
                    R.anim.exiting_screen_sliding_to_right);
        }
    }

    @Override
    public void onInternetAvailable() {
        Logger.d(this.getClass(), ":INTERNET AVAILABLE");
    }

    @Override
    public void onInternetUnavailable() {
        Logger.d(this.getClass(), ":INTERNET UNAVAILABLE");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(null);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        this.setContentView(this.getLayoutFileId());

        /* Implement fontChecker */
        fontChecker = SystemFontChecker.getInstance();

        /* Bind Butter knife */
        ButterKnife.bind(this);

        /* Check the screen got lblScreenTitle or not. */
        if (this.findViewById(R.id.btnBack) != null) {
            AppCompatImageButton btnBack = this.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(view -> this.onBackPressed());
        }

        if (this.findViewById(R.id.lblScreenTitle) != null) {
            AppCompatTextView lblScreenTitle = this.findViewById(R.id.lblScreenTitle);
            lblScreenTitle.setText(this.getScreenTitleAtStart());
        }

        /* NetworkStatusBroadcastReceiver */
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageHelper.onAttach(newBase, LanguageHelper.getLanguage(newBase)));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract int getLayoutFileId();

    protected abstract String getScreenTitleAtStart();

    protected abstract Class<? extends BasicActivity> getDefaultBackScreenIfRoot();

    protected abstract Bundle getDefaultBackScreenExtraIfRoot();

    /**
     * show progress bar on UI.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showProgressBar() {
        progressDialog.showProgressDialog(this);
    }

    /**
     * hide progress bar on UI.
     */
    public void hideProgressBar() {
        progressDialog.hideProgressDialog();
    }

    public void checkBurmeseFont() {
        if (fontChecker.isUnicode(this)) {
            Logger.log("This device use unicode font.");
        } else {
            Logger.log("This device use zawgyi font.");
        }
    }
}
