package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.constants.DataSizes;

public class ChangePasswordActivity extends BasicActivity {

    private String oldPassword;
    private String newPassword;

    @BindView(R.id.txtOldPassword)
    AppCompatEditText txtOldPassword;

    @BindView(R.id.txtNewPassword)
    AppCompatEditText txtNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_change_password__change_password);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void doChangePasswordSubmit(View view) {
        if (inputsOk()) {

        }
    }

    private boolean inputsOk() {
        boolean ok = true;

        oldPassword = txtOldPassword.getText().toString();
        newPassword = txtNewPassword.getText().toString();

        if (oldPassword.isEmpty()) {
            txtOldPassword.setError(this.getResources().getString(R.string.strings_validation_errors__require_password));
            ok = false;
        } else if (oldPassword.trim().length() > DataSizes.MXL_SECRET_KEY) {
            this.txtOldPassword.setError(this.getResources().getString(R.string.strings_validation_errors__invalid_password_length));
            ok = false;
        } else if (newPassword.isEmpty()) {
            txtNewPassword.setError(this.getResources().getString(R.string.strings_validation_errors__require_password));
            ok = false;
        } else if (newPassword.trim().length() > DataSizes.MXL_SECRET_KEY) {
            this.txtNewPassword.setError(this.getResources().getString(R.string.strings_validation_errors__invalid_password_length));
            ok = false;
        }
        return ok;
    }
}
