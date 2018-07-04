package es.shwebill.component.android;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BasicFragment extends Fragment {

    private View fragmentView = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (this.fragmentView == null) {
            this.fragmentView = inflater.inflate(this.getLayoutXmlId(), container, false);
            ButterKnife.bind(this, this.fragmentView);
            this.createView();
        }
        return this.fragmentView;
    }

    public View getFragmentView() {
        return this.fragmentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showProgressBar(BasicActivity parentActivity) {
        parentActivity.showProgressBar();
    }

    public void hideProgressBar(BasicActivity parentActivity) {
        parentActivity.hideProgressBar();
    }

    protected abstract void createView();

    protected abstract int getLayoutXmlId();

    public abstract void refresh();
}
