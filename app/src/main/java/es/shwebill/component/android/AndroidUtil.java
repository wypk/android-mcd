package es.shwebill.component.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import es.shwebill.R;
import es.shwebill.ui.uicomponent.Alerter;


public class AndroidUtil {

    public static boolean isInternetAvailable(Context mContext) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void playDefaultNotificationSound(Context mContext) {

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(mContext, notification);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void noInternetConnectionAlerter(Context mContext) {

        Alerter.showAlerter(
                (Activity) mContext, mContext
                        .getResources()
                        .getString(R.string.MESSAGE_LOCAL__INTERNET_CONNECTION_ERROR),
                R.color.red_A700, R.drawable.ic_no_internet);
    }

    public static void sentEmail(Context context, String email) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        context.startActivity(emailIntent);
    }

    public static void callPhone(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public static void relaunchActivity(Activity activity) {

        Intent intent = new Intent(activity, activity.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        Runtime.getRuntime().exit(0);
        activity.finish();
    }

    public static void showDatePickerDialog(Activity activity, BasicFragment fragment) {

        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                (DatePickerDialog.OnDateSetListener) fragment,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setLocale(Locale.ENGLISH);
        datePickerDialog.setMaxDate(now);
        datePickerDialog.show(activity.getFragmentManager(), "Date Picker Dialog");
    }

    public static void showMaterialDialog(
            Activity activity, int title, int items, int positiveText,
            MaterialDialog.ListCallbackSingleChoice callback) {

        new MaterialDialog.Builder(Objects.requireNonNull(activity))
                .typeface("Zawgyi-font.ttf", "Zawgyi-font.ttf")
                .title(title)
                .items(items)
                .itemsCallbackSingleChoice(0, (dialog, view, which, text) -> {
                    callback.onSelection(dialog, view, which, text);
                    return true;
                })
                .positiveText(positiveText)
                .show();
    }
}
