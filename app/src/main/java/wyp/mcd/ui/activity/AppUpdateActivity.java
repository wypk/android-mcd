package wyp.mcd.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.android.BasicActivity;

public class AppUpdateActivity extends BasicActivity {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLottieAnimationView.setAnimation(R.raw.boxing);
        mLottieAnimationView.playAnimation();
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_app_update;
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_app_update;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return null;
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void goToGooglePlayStore(View view) {
        AndroidUtil.goGooglePlayStore(this);
    }
}
