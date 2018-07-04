package es.shwebill.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.constants.DataSizes;
import es.shwebill.constants.InputFormats;

public class SignInActivity extends BasicActivity {

    private String password;
    private String loginId;

    @BindView(R.id.txtLoginId)
    AppCompatEditText txtLoginId;

    @BindView(R.id.txtPassword)
    AppCompatEditText txtPassword;

    @BindView(R.id.lblRegister)
    AppCompatTextView lblRegister;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_sign_in;
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

    @OnClick(R.id.lblRegister)
    public void goRegister() {
        TransitionUtil.showNextActivityWithMap(this, SignUpActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }

    private boolean inputOk() {
        boolean ok = true;
        loginId = this.txtLoginId.getText().toString();
        password = this.txtPassword.getText().toString();

        if (loginId.isEmpty()) {
            this.txtLoginId.setError(this.getResources().getString(R.string.strings_validation_errors__require_login_id));
            ok = false;
        } else if (!InputFormats.USERNAME_PATTERN.matcher(loginId).matches()) {
            this.txtLoginId.setError(this.getResources().getString(R.string.strings_validation_errors__invalid_login_id_format));
            ok = false;
        } else if (loginId.trim().length() > DataSizes.MXL_USER_ID) {
            this.txtLoginId.setError(this.getResources().getString(R.string.strings_validation_errors__invalid_login_id_format));
            ok = false;
        } else if (password.isEmpty()) {
            this.txtPassword.setError(this.getResources().getString(R.string.strings_validation_errors__require_password));
            ok = false;
        } else if (password.trim().length() > DataSizes.MXL_SECRET_KEY) {
            this.txtPassword.setError(this.getResources().getString(R.string.strings_validation_errors__invalid_password_length));
            ok = false;
        }
        return ok;
    }

    public void doSignIn(View view) {
        if (inputOk()) {
            TransitionUtil.showNextActivityWithMap(this, HomeActivity.class, null,
                    R.anim.entering_screen_sliding_from_right,
                    R.anim.exiting_screen_sliding_to_left,
                    true);
        }
    }

    public void goForgetPasswordScreen(View view) {
        TransitionUtil.showNextActivityWithMap(this, ForgetPasswordActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }
}
