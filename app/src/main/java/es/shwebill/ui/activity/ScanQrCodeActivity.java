package es.shwebill.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.Encrypter;
import es.shwebill.component.util.Logger;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQrCodeActivity extends BasicActivity implements ZXingScannerView.ResultHandler {

    private static final String FLASH_STATE = "FLASH_STATE";

    @BindView(R.id.btnClose)
    AppCompatImageButton btnClose;

    private ZXingScannerView mScannerView;
    private boolean mFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup contentFrame = findViewById(R.id.fl_qr_scan);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_scan_qr_code;
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

    @Override
    public void onResume() {

        super.onResume();
        mScannerView.setResultHandler(this);
        /* You can optionally set aspect ratio tolerance level that is used in calculating the optimal Camera preview size */
        mScannerView.setAspectTolerance(0.2f);
        mScannerView.startCamera();
        mScannerView.setFlash(mFlash);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLASH_STATE, mFlash);
    }

    @Override
    public void handleResult(Result result) {

        try {
            Logger.log("Scan Result is :>>> " + Encrypter.decryptedFromBase64(Encrypter.ENCRYPTING_KEY, result.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> mScannerView.resumeCameraPreview(ScanQrCodeActivity.this), 2000);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void toggleFlash(View v) {
        mFlash = !mFlash;
        mScannerView.setFlash(mFlash);
        AppCompatImageButton btnFlash = findViewById(R.id.btnFlash);
        if (!mFlash) {
            Logger.log("Flash turn off");
            btnFlash.setImageResource(R.drawable.ic_flash_off);
        } else {
            Logger.log("Flash turn on");
            btnFlash.setImageResource(R.drawable.ic_ffffff_flash_on);
        }
    }

    @OnClick(R.id.btnClose)
    public void goBack() {
        this.onBackPressed();
    }
}
