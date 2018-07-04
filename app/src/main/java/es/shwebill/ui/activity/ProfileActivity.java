package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;

public class ProfileActivity extends BasicActivity {

    @BindView(R.id.btnSave)
    AppCompatButton btnGenerateQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_profile;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_profile__my_profile);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    @OnClick(R.id.btnSave)
    public void showQrCode() {
        TransitionUtil.showNextActivityWithMap(this, MyQrCodeActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }
}
