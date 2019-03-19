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

package wyp.mcd.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.ui.BasicActivity;

public class AppUpdateActivity extends BasicActivity {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLottieAnimationView.setAnimation(R.raw.update);
        mLottieAnimationView.playAnimation();
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_app_update;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return null;
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    public void goToGooglePlayStore(View view) {
        AndroidUtil.goGooglePlayStore(this);
    }
}
