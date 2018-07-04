package es.shwebill.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.Logger;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.constants.DataSizes;
import es.shwebill.constants.InputFormats;
import es.shwebill.domain.type.MyanmarRegion;
import es.shwebill.ui.uicomponent.PhotoPickerDialog;

public class SignUpActivity extends BasicActivity {

    private Bitmap profilePhoto;
    private MyanmarRegion myanmarRegion;
    private String nickName;
    private String nativeTown;
    private String loginId;
    private String fullName;
    private String password;

    @BindView(R.id.lblSignIn)
    AppCompatTextView lblSignIn;

    @BindView(R.id.imvProfilePhoto)
    CircleImageView imvProfilePhoto;

    @BindView(R.id.txtLoginId)
    AppCompatEditText txtLoginId;

    @BindView(R.id.txtPassword)
    AppCompatEditText txtPassword;

    @BindView(R.id.txtFullName)
    AppCompatEditText txtFullName;

    @BindView(R.id.txtRegion)
    AppCompatTextView txtRegion;

    @BindView(R.id.txtNickname)
    AppCompatEditText txtNickname;

    @BindView(R.id.txtNativeTown)
    AppCompatEditText txtNativeTown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_sign_up__sign_up);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    @OnClick(R.id.txtRegion)
    public void chooseRegion() {
        new MaterialDialog.Builder(this)
                .typeface("Zawgyi-font.ttf", "Zawgyi-font.ttf")
                .title(R.string.activity_sign_up__choose_region)
                .items(R.array.activity_sign_up__regions_list)
                .itemsCallback((dialog, view, which, text) -> {
                    Logger.d(SignUpActivity.class, "Choose Region:" + text);
                    txtRegion.setText(text);
                    txtRegion.setTextColor(SignUpActivity.this.getResources().getColor(R.color.colorPrimary));
                    SignUpActivity.this.setSelectedRegion(which);
                })
                .show();
    }

    @OnClick(R.id.lblSignIn)
    public void goSignInScreen() {
        TransitionUtil.showNextActivityWithMap(this, SignInActivity.class, null,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }

    @OnClick(R.id.imvProfilePhoto)
    public void showPopupPhotoDialog(View view) {
        Logger.log("Show Popup Photo Dialog");
        this.showPhotoPickerDialog();
    }

    private void showPhotoPickerDialog() {

        PhotoPickerDialog photoPickerDialog = new PhotoPickerDialog(
                SignUpActivity.this, new PhotoPickerDialog.ActionPerformedListener() {

            @Override
            public void onCancel() {
                // Do nothing...
            }

            @Override
            public void chooseFromGallery() {
                Logger.log("Choose Image From Gallery");
            }

            @Override
            public void takePhoto() {
                Logger.log("Take Photo");
            }
        });
        photoPickerDialog.show();
    }

    public void goVerifyPhoneNumberScreen(View view) {

        if (inputOk()) {
            TransitionUtil.showNextActivityWithMap(this, VerifyPhoneNumberActivity.class, null,
                    R.anim.entering_screen_sliding_from_right,
                    R.anim.exiting_screen_sliding_to_left,
                    false);
        }
    }

    private boolean inputOk() {
        boolean ok = true;
        loginId = this.txtLoginId.getText().toString();
        fullName = this.txtFullName.getText().toString();
        password = this.txtPassword.getText().toString();
        nickName = txtNickname.getText().toString();
        nativeTown = txtNativeTown.getText().toString();

        if (loginId.isEmpty()) {
            this.txtLoginId.setError(getString(R.string.strings_validation_errors__require_login_id));
            ok = false;
        } else if (!InputFormats.USERNAME_PATTERN.matcher(loginId).matches()) {
            this.txtLoginId.setError(getString(R.string.strings_validation_errors__invalid_login_id_format));
            ok = false;
        } else if (loginId.trim().length() > DataSizes.MXL_USER_ID) {
            this.txtLoginId.setError(getString(R.string.strings_validation_errors__invalid_login_id_format));
            ok = false;
        } else if (fullName.isEmpty()) {
            this.txtFullName.setError(getString(R.string.strings_validation_errors__require_full_name));
            ok = false;
        } else if (!InputFormats.USERNAME_PATTERN.matcher(fullName).matches()) {
            this.txtFullName.setError(getString(R.string.strings_validation_errors__invalid_full_name_format));
            ok = false;
        } else if (fullName.trim().length() > DataSizes.MXL_FULL_NAME) {
            this.txtFullName.setError(getString(R.string.strings_validation_errors__invalid_full_name_length));
            ok = false;
        } else if (password.isEmpty()) {
            this.txtPassword.setError(getString(R.string.strings_validation_errors__require_password));
            ok = false;
        } else if (password.trim().length() > DataSizes.MXL_SECRET_KEY) {
            this.txtPassword.setError(getString(R.string.strings_validation_errors__invalid_password_length));
            ok = false;
        } else if (myanmarRegion == null) {
            this.txtRegion.setError(getString(R.string.strings_validation_errors__require_region));
            ok = false;
        } else if (nickName.isEmpty()) {
            this.txtNickname.setError(getString(R.string.strings_validation_errors__require_security_questions));
            ok = false;
        } else if (nativeTown.isEmpty()) {
            this.txtNativeTown.setError(getString(R.string.strings_validation_errors__require_security_questions));
            ok = false;
        }

//        if (this.profilePhoto == null) {
//            ok = false;
//            EsAlertDialog alertDialog = new EsAlertDialog(
//                    this, "Alert",
//                    this.getResources().getString(R.string.strings_validation_errors__submit_without_photo),
//                    (View view) -> {
//                        //Do Something
//                    });
//            alertDialog.show();
//        }
        return ok;
    }

    private Target target = new Target() {

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            SignUpActivity.this.hideProgressBar();

            SignUpActivity.this.imvProfilePhoto.setVisibility(View.GONE);
            SignUpActivity.this.imvProfilePhoto.setImageBitmap(bitmap);
            SignUpActivity.this.profilePhoto = bitmap;
            SignUpActivity.this.imvProfilePhoto.setVisibility(View.VISIBLE);

            Logger.d(this.getClass(), "Profile Photo received...");
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

            SignUpActivity.this.hideProgressBar();

            SignUpActivity.this.imvProfilePhoto.setImageBitmap(
                    BitmapFactory.decodeResource(
                            SignUpActivity.this.getResources(),
                            R.drawable.ic_1565c0_profile));

            Logger.d(this.getClass(), "Cannot get profile photo...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

            SignUpActivity.this.showProgressBar();
        }

        @Override
        public int hashCode() {

            return UUID.randomUUID().hashCode();
        }
    };

    private void setSelectedRegion(int position) {
        switch (position) {
            case 1:
                myanmarRegion = MyanmarRegion.KACHIN;
                break;
            case 2:
                myanmarRegion = MyanmarRegion.KAYAH;
                break;
            case 3:
                myanmarRegion = MyanmarRegion.KAYIN;
                break;
            case 4:
                myanmarRegion = MyanmarRegion.CHIN;
                break;
            case 5:
                myanmarRegion = MyanmarRegion.SAGAING;
                break;
            case 6:
                myanmarRegion = MyanmarRegion.TANINTHARYI;
                break;
            case 7:
                myanmarRegion = MyanmarRegion.NAYPYIDAW;
                break;
            case 8:
                myanmarRegion = MyanmarRegion.BAGO;
                break;
            case 9:
                myanmarRegion = MyanmarRegion.MAGWAY;
                break;
            case 10:
                myanmarRegion = MyanmarRegion.MANDALAY;
                break;
            case 11:
                myanmarRegion = MyanmarRegion.MON;
                break;
            case 12:
                myanmarRegion = MyanmarRegion.RAKHINE;
                break;
            case 13:
                myanmarRegion = MyanmarRegion.YANGON;
                break;
            case 14:
                myanmarRegion = MyanmarRegion.SHAN;
                break;
            case 15:
                myanmarRegion = MyanmarRegion.AYEYARWADDY;
                break;
        }
    }

}
