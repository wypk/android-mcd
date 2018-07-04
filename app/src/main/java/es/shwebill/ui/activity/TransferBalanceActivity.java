package es.shwebill.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.TransitionUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class TransferBalanceActivity extends BasicActivity {

    @BindView(R.id.rlScanAndTransfer)
    RelativeLayout rlScanAndTransfer;

    @BindView(R.id.txtBalance)
    AppCompatEditText txtBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_transfer_balance;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_transfer_balance__transfer_balance);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    @OnClick(R.id.rlScanAndTransfer)
    public void scanQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (inputOk()) {
                TransitionUtil.showNextActivityWithMap(this, ScanQrCodeActivity.class, null,
                        R.anim.slide_up,
                        R.anim.slide_down, false);
            }
        } else {
            TransferBalanceActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /* NOTE: delegate the permission handling to generated method */
        TransferBalanceActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public void showCamera() {
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onCameraDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void onCameraNeverAskAgain() {
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("Allow", (dialog, which) -> request.proceed())
                .setNegativeButton("Denied", (dialog, which) -> request.cancel())
                .setCancelable(false)
                .setMessage("Need to access camera permission.")
                .show();
    }

    private boolean inputOk() {
        boolean ok = true;
        if (txtBalance.getText().toString().isEmpty()) {
            txtBalance.setError(getString(R.string.strings_validation_errors__require_balance));
            ok = false;
        }
        return ok;
    }
}
