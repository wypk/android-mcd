package es.shwebill.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.CapturePhotoUtil;
import es.shwebill.component.util.Encrypter;

public class MyQrCodeActivity extends BasicActivity {

    @BindView(R.id.imvQRCode)
    AppCompatImageView imvQRCode;

    @BindView(R.id.cvQrCode)
    CardView cvQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            generateQRCode(Encrypter.encryptedToBase64(Encrypter.ENCRYPTING_KEY, "WaiYanPhyoe"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_my_qr_code;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_my_qr_code__my_qr_code);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    private void generateQRCode(String autoMaxAccountName) {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(autoMaxAccountName, BarcodeFormat.QR_CODE,
                    240, 240);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imvQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            /* has background drawable, then draw it on the canvas */
            bgDrawable.draw(canvas);
        } else {
            /* does not have background drawable, then draw white background on the canvas */
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public void onClickSave(View view) {
        final String imagePath = CapturePhotoUtil.insertImage(getContentResolver(), getBitmapFromView(imvQRCode), "ShweBill'sQr.png", "Shwe Bill");
        TastyToast.makeText(MyQrCodeActivity.this, imagePath, TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    @SuppressLint("SetWorldReadable")
    public void onClickShare(View view) {

        Bitmap bitmap = getBitmapFromView(cvQrCode);
        try {
            File file = new File(this.getExternalCacheDir(), "ShweBill'sQr.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
