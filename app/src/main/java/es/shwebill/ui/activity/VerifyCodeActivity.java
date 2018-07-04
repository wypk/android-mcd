package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.constants.DataSizes;

public class VerifyCodeActivity extends BasicActivity {

    @BindView(R.id.txtCode)
    AppCompatEditText txtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_verify_code;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_verify_code__verify_code);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void goPinCodeScreen(View view) {
        if (inputsOk()) {
            TransitionUtil.showNextActivityWithMap(this, PinActivity.class, null,
                    R.anim.entering_screen_sliding_from_right,
                    R.anim.exiting_screen_sliding_to_left,
                    false);
        }
    }

    private boolean inputsOk() {
        boolean ok = true;
        if (txtCode.getText().toString().isEmpty()) {
            txtCode.setError(getString(R.string.strings_validation_errors__require_verify_code));
            ok = false;
        } else if (txtCode.getText().toString().trim().length() < DataSizes.MIN_VERIFY_CODE) {
            txtCode.setError(getString(R.string.strings_validation_errors__invalid_verify_code_length));
            ok = false;
        }
        return ok;
    }
}
