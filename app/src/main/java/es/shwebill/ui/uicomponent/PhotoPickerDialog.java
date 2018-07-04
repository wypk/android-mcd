package es.shwebill.ui.uicomponent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.shwebill.R;


public class PhotoPickerDialog
        extends BottomSheetDialog {

    @BindView(R.id.btnTakePhoto)
    AppCompatButton btnTakePhoto;

    @BindView(R.id.btnFromGallery)
    AppCompatButton btnFromGallery;

    @BindView(R.id.btnCancel)
    AppCompatButton btnCancel;

    private Activity parentActivity;
    private ActionPerformedListener actionPerformedListener;
    private int desireWidth;
    private int desireHeight;


    public PhotoPickerDialog(
            Context context,
            @NonNull ActionPerformedListener actionPerformedListener) {

        super(context);

        if (context instanceof Activity) {
            this.parentActivity = (Activity) context;
        } else {
            throw new IllegalArgumentException("Context must be instance of Activity.");
        }

        this.actionPerformedListener = actionPerformedListener;
        this.desireHeight = desireHeight;
        this.desireWidth = desireWidth;
    }

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_photo_picker_dialog);
        ButterKnife.bind(this);

        this.setOnDismissListener(dialogInterface -> {
            this.actionPerformedListener.onCancel();
        });
    }

    @OnClick(R.id.btnCancel)
    protected void doCancel() {

        this.dismiss();
        this.actionPerformedListener.onCancel();
    }

    @OnClick(R.id.btnFromGallery)
    protected void doPickFromGallery() {

        this.dismiss();

        this.actionPerformedListener.chooseFromGallery();
    }

    @OnClick(R.id.btnTakePhoto)
    protected void doTakePhoto() {

        this.dismiss();
        this.actionPerformedListener.takePhoto();
    }


    public interface ActionPerformedListener {

        void onCancel();

        void chooseFromGallery();

        void takePhoto();
    }
}
