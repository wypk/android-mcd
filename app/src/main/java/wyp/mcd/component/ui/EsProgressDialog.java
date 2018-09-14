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

package wyp.mcd.component.ui;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

import wyp.mcd.R;

public class EsProgressDialog {

    private static EsProgressDialog esProgressDialog;
    private Dialog mDialog;

    private EsProgressDialog() {
    }

    public static EsProgressDialog getInstance() {

        if (esProgressDialog == null) {
            esProgressDialog = new EsProgressDialog();
        }
        return esProgressDialog;
    }

    public void showProgressDialog(Context mContext) {

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.view_progress_dialog);

        LottieAnimationView lottieAnimationView = mDialog.findViewById(R.id.progressAnimation);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> lottieAnimationView.setProgress((Float) animation.getAnimatedValue()));
        animator.start();

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public void hideProgressDialog() {

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
