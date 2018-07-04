package es.shwebill.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.Random;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.AppInfoStorage;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;

public class SplashActivity extends BasicActivity {

    private int[] splashImages = new int[]{R.drawable.splash_background, R.drawable.splash_background1, R.drawable.splash_background2};

    @BindView(R.id.spBackground)
    KenBurnsView spBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        /* Set background */
        setSplashBackground();

        /* Calling main activity */
        final Handler handler = new Handler();
        handler.postDelayed(this::showNextScreen, 3000);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_splash;
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

    private void setSplashBackground() {

        /* Generate Random Image and set background */
        Random randomGenerator = new Random();
        int imgId = randomGenerator.nextInt(splashImages.length);
        spBackground.setImageDrawable(getResources().getDrawable(splashImages[imgId]));
    }

    private void showNextScreen() {
        if (AppInfoStorage.getInstance().isAlreadyLanguageSelected()) {
            TransitionUtil.showNextActivityWithMap(this, SignInActivity.class, null,
                    R.anim.slide_up,
                    R.anim.slide_down,
                    true);
        } else {
            TransitionUtil.showNextActivityWithMap(this, SelectLanguageActivity.class, null,
                    R.anim.slide_up,
                    R.anim.slide_down,
                    true);
        }
    }
}
