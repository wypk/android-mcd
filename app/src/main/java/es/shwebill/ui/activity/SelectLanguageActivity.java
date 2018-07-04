package es.shwebill.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.AppInfoStorage;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.LanguageHelper;
import es.shwebill.component.util.Logger;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.domain.type.DisplayLanguages;

public class SelectLanguageActivity extends BasicActivity {

    @BindView(R.id.llMyanmar)
    LinearLayoutCompat llMyanmar;

    @BindView(R.id.llEnglish)
    LinearLayoutCompat llEnglish;

    private DisplayLanguages displayLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (LanguageHelper.getLanguage(this)) {
            case "en":
                displayLanguages = DisplayLanguages.ENGLISH;
                changeLanguageLinearLayoutBackground(displayLanguages);
                break;
            case "my":
                displayLanguages = DisplayLanguages.MYANMAR;
                changeLanguageLinearLayoutBackground(displayLanguages);
                break;
            default:
                changeLanguageLinearLayoutBackground(DisplayLanguages.ENGLISH);

        }
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_select_language;
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

    @OnClick(R.id.llMyanmar)
    public void clickMyanmar() {
        displayLanguages = DisplayLanguages.MYANMAR;
        Logger.log("Choose :" + displayLanguages);
        changeLanguageLinearLayoutBackground(displayLanguages);
        setLanguage(this, "my");
    }

    @OnClick(R.id.llEnglish)
    public void clickEnglish() {
        displayLanguages = DisplayLanguages.ENGLISH;
        Logger.log("Choose :" + displayLanguages);
        changeLanguageLinearLayoutBackground(displayLanguages);
        setLanguage(this, "eng");
    }

    private void changeLanguageLinearLayoutBackground(DisplayLanguages displayLanguages) {

        switch (displayLanguages) {

            case MYANMAR:
                this.llMyanmar.setBackgroundDrawable(
                        getResources().getDrawable(
                                R.drawable.shape_language_selected_background));
                this.llEnglish.setBackgroundDrawable(
                        getResources().getDrawable(
                                R.drawable.shape_language_unselected_background));
                break;
            case ENGLISH:
                this.llMyanmar.setBackgroundDrawable(
                        getResources().getDrawable(
                                R.drawable.shape_language_unselected_background));
                this.llEnglish.setBackgroundDrawable(
                        getResources().getDrawable(
                                R.drawable.shape_language_selected_background));
                break;
        }
    }

    public void goToSignInScreen(View view) {

        /* Mark user already reach language selected screen */
        AppInfoStorage.getInstance().languageSelected();

        TransitionUtil.showNextActivityWithMap(this, SignInActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left, false);
    }

    public void setLanguage(Activity activity, String languageCode) {
        LanguageHelper.setLanguage(activity, languageCode);
        AndroidUtil.relaunchActivity(activity);
    }
}
