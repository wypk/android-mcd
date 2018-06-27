package wyp.mcd.component.util;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;

public class GetVersionCodeUtils extends AsyncTask<Void, Void, String> {

    /* You may separate this or combined to caller class. */
    public interface AsyncResponse {
        void processFinish(String onlineVersion);
    }

    private AsyncResponse delegate;

    public GetVersionCodeUtils(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String newVersion;
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=wyp.mmcomputerdictionary")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select(".hAyfc .htlgb")
                    .get(7)
                    .ownText();
            return newVersion;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
        delegate.processFinish(onlineVersion);
    }
}

