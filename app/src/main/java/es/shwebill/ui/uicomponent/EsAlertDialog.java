package es.shwebill.ui.uicomponent;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.shwebill.R;
import es.shwebill.component.util.Logger;

public class EsAlertDialog extends AppCompatDialog {

    @BindView(R.id.lblTitle)
    AppCompatTextView lblTitle;

    @BindView(R.id.lblMessage)
    AppCompatTextView lblMessage;

    @BindView(R.id.btnOk)
    AppCompatButton btnOk;

    private View.OnClickListener onOkClickListener;

    @Nullable
    private String title;

    @NonNull
    private String message;

    public EsAlertDialog(
            Context context, @Nullable String title, @NonNull String message,
            View.OnClickListener onOkClickListener) {

        super(context);
        this.title = title;
        this.message = message;
        this.onOkClickListener = onOkClickListener;
        this.setCancelable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.view_es_alert_dialog);
        ButterKnife.bind(this);

        if (this.title == null) {
            this.title = "Alert";
        }

        this.lblTitle.setText(this.title);
        this.lblMessage.setText(this.message);

        this.btnOk.setOnClickListener(v -> {
            Logger.d(this.getClass(), "Dismiss EsAlertDialog. : " + this.toString());
            this.dismiss();
            this.onOkClickListener.onClick(v);
        });

    }
}
