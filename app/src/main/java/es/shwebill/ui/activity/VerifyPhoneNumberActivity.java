package es.shwebill.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.TelephonyManager;
import android.view.View;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;

public class VerifyPhoneNumberActivity extends BasicActivity {

    @BindView(R.id.txtPhoneNumber)
    AppCompatEditText txtPhoneNumber;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        assert telephonyManager != null;
        txtPhoneNumber.setText(telephonyManager.getLine1Number());
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_verify_phone_number;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_verify_phone_number__verify_your_number);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void goVerifyCodeScreen(View view) {

        if (inputOk()) {
            TransitionUtil.showNextActivityWithMap(this, VerifyCodeActivity.class, null,
                    R.anim.entering_screen_sliding_from_right,
                    R.anim.exiting_screen_sliding_to_left,
                    false);
        }
    }

    private boolean inputOk() {
        boolean ok = true;
        if (txtPhoneNumber.getText().toString().isEmpty()) {
            txtPhoneNumber.setError(getString(R.string.strings_validation_errors__require_phone_number));
            ok = false;
        }
        return ok;
    }
}
