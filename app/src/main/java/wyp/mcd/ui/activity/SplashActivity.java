/*
 * Copyright (C) 2018
 *  Source code is created by Elissa Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package wyp.mcd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.sharedpreferences.AppInfoStorage;
import wyp.mcd.component.util.Logger;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_splash);

        /* Call create shortcut icon method */
        createShortcutIcon();

        /* Calling main activity */
        final Handler handler = new Handler();
        handler.postDelayed(this::showNextScreen, 500);
    }

    private void createShortcutIcon() {
        if (!AppInfoStorage.getInstance().isShortcutCreated()) {
            AndroidUtil.createShortcut(getApplicationContext(), SplashActivity.class);
        }
    }

    private void showNextScreen() {

        if (AppInfoStorage.getInstance().getVersionCode() == AndroidUtil.getCurrentVersionCode(this)) {

            Logger.log("Current version code :" + AndroidUtil.getCurrentVersionCode(this));
            Logger.log("AppInfoStorage version code :" + AppInfoStorage.getInstance().getVersionCode());

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, ParseAndPersistActivity.class));
            finish();
        }
    }
}
