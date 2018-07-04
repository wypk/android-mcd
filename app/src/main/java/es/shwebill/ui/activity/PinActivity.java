package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.Logger;
import es.shwebill.component.util.TransitionUtil;

public class PinActivity extends BasicActivity {

    @BindView(R.id.pin_view)
    PinLockView mPinView;

    @BindView(R.id.indicator_dots)
    IndicatorDots mIndicatorDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPinView.attachIndicatorDots(mIndicatorDots);
        mPinView.setPinLockListener(mPinLockListener);
        /*
        mPinView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        mPinView.enableLayoutShuffling();
        */
        mPinView.setPinLength(6);
        mPinView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mPinView.setDeleteButtonDrawable(this.getResources().getDrawable(R.drawable.ic_1565c0_delete));
        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FIXED);
        mIndicatorDots.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_pin;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_pin_code__enter_pin_code);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Logger.log("Pin complete:>>>" + pin);
            TransitionUtil.showNextActivityWithMap(PinActivity.this, HomeActivity.class, null,
                    R.anim.entering_screen_sliding_from_right,
                    R.anim.exiting_screen_sliding_to_left,
                    false);
        }

        @Override
        public void onEmpty() {
            Logger.log("Pin empty:");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Logger.log("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };

}
