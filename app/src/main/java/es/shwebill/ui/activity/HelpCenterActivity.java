package es.shwebill.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.BasicActivity;

public class HelpCenterActivity extends BasicActivity {

    @BindView(R.id.rlHelpCenterEmail)
    RelativeLayout rlHelpCenterEmail;

    @BindView(R.id.rlHelpCenterPhoneOne)
    RelativeLayout rlHelpCenterPhoneOne;

    @BindView(R.id.rlHelpCenterPhoneTwo)
    RelativeLayout rlHelpCenterPhoneTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_help_center;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_help_center__help_center);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    @OnClick(R.id.rlHelpCenterEmail)
    public void sendEmail() {
        AndroidUtil.sentEmail(this, "waiyanphyoecu.wyp@gmail.com");
    }

    @OnClick(R.id.rlHelpCenterPhoneOne)
    public void callCenterPhoneOne() {
        AndroidUtil.callPhone(this, "09428155046");
    }

    @OnClick(R.id.rlHelpCenterPhoneTwo)
    public void callCenterPhoneTwo() {
        AndroidUtil.callPhone(this, "09428155046");
    }
}
