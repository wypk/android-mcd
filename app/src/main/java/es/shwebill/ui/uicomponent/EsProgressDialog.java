package es.shwebill.ui.uicomponent;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;

import java.util.Objects;

import es.shwebill.R;


public class EsProgressDialog {

    private static EsProgressDialog esProgressDialog;
    private Dialog mDialog;

    private EsProgressDialog() {
    }

    public static EsProgressDialog getInstance() {

        if (esProgressDialog == null) {
            esProgressDialog = new EsProgressDialog();
        }
        return esProgressDialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showProgressDialog(Context mContext) {

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.view_progress_dialog);
        mDialog.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        Objects.requireNonNull(mDialog
                .getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public void hideProgressDialog() {

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
