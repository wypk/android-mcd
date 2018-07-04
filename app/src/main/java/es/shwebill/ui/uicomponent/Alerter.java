package es.shwebill.ui.uicomponent;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import es.shwebill.R;


public class Alerter {

    public static void showAlerter(
            Activity context, String message, @ColorRes final int colorResId,
            @DrawableRes final int drawableResId) {

        com.tapadoo.alerter.Alerter.create(context)
                .setText(message)
                .setTextAppearance(R.style.AlertTextAppearance_Text)
                .setTextTypeface(Typeface.createFromAsset(
                        context.getAssets(),
                        "font/zawgyi.ttf"))
                .setIconColorFilter(0) // Optional - Removes white tint
                .setBackgroundColorRes(colorResId)
                .setIcon(drawableResId)
                .show();
    }
}
