/*
 * Copyright (C) 2018
 *
 * Source code is created by Elissa Software
 * Dictionary data is owned by UCST
 * Database is implemented by Salai Chit Oo Latt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.ui.uicomponents;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import wyp.mcd.R;

public class Alerter {

    public static void showAlerter(
            Activity context, String message, @ColorRes final int colorResId,
            @DrawableRes final int drawableResId) {

        com.tapadoo.alerter.Alerter.create(context)
                .setText(message)
                .setTextAppearance(R.style.AlertTextAppearance_Text)
                .setTextTypeface(Typeface.createFromAsset(
                        context.getAssets(),
                        "fonts/Moderat.ttf"))
                .setIconColorFilter(0) // Optional - Removes white tint
                .setBackgroundColorRes(colorResId)
                .setIcon(drawableResId)
                .show();
    }
}
