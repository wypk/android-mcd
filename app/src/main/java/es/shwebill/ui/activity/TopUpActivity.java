package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;

public class TopUpActivity extends BasicActivity {

    private String operatorName;

    @BindView(R.id.txtPhoneNumber)
    AppCompatEditText txtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                operatorName = (String) extras.get("operator-type");
            }

            /*Solving for overwriting of super class's onCreate Method */
            final AppCompatTextView lblScreenTitle = this.findViewById(R.id.lblScreenTitle);
            lblScreenTitle.setText(operatorName);
        }
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_top_up;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return "";
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void submitTopUp(View view) {

        if (inputOk()) {

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
