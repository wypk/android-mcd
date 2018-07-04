package es.shwebill.ui.activity;

import android.os.Bundle;
import android.view.View;

import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;

public class SettingsActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_settings;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_settings__settings);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void goChanePasswordScreen(View View) {
        TransitionUtil.showNextActivityWithMap(this, ChangePasswordActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }
}
