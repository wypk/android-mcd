package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;

public class ForgetPasswordActivity extends BasicActivity {

    @BindView(R.id.txtPhoneNumber)
    AppCompatEditText txtPhoneNumber;

    @BindView(R.id.txtNickname)
    AppCompatEditText txtNickname;

    @BindView(R.id.txtNativeTown)
    AppCompatEditText txtNativeTown;

    private String nickName;
    private String nativeTown;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_forget_password__forget_password);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void doForgetPasswordSubmit(View view) {
        if (inputOk()) {

        }
    }

    private boolean inputOk() {
        boolean ok = true;

        phoneNumber = txtPhoneNumber.getText().toString();
        nickName = txtNickname.getText().toString();
        nativeTown = txtNativeTown.getText().toString();

        if (phoneNumber.isEmpty()) {
            this.txtPhoneNumber.setError(getString(R.string.strings_validation_errors__require_phone_number));
            ok = false;
        } else if (nickName.isEmpty()) {
            this.txtNickname.setError(getString(R.string.strings_validation_errors__require_security_questions));
            ok = false;
        } else if (nativeTown.isEmpty()) {
            this.txtNativeTown.setError(getString(R.string.strings_validation_errors__require_security_questions));
            ok = false;
        }
        return ok;
    }

}
