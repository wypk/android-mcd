/*
 * Copyright 2019 Wai Yan (TechBase Software). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.component.util;

import android.os.AsyncTask;

import org.jsoup.Jsoup;

public class GetVersionCodeFromPlayStoreUtil extends AsyncTask<Void, Void, String> {

    private AsyncResponse delegate;
    private String appUrl;

    public GetVersionCodeFromPlayStoreUtil(AsyncResponse delegate, String appUrl) {
        this.delegate = delegate;
        this.appUrl = appUrl;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String newVersion;
        try {
            newVersion = Jsoup.connect(appUrl)
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select(".hAyfc .htlgb")
                    .get(7)
                    .ownText();
            return newVersion;
        } catch (Exception e) {
            Logger.d(this.getClass(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
        delegate.processFinish(onlineVersion);
    }

    /* You may separate this or combined to caller class. */
    public interface AsyncResponse {
        void processFinish(String onlineVersion);
    }
}

