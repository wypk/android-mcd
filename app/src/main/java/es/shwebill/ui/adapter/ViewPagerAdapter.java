package es.shwebill.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.shwebill.ui.fragment.TopUpTransactionsHistoryFragment;
import es.shwebill.ui.fragment.TransferTransactionsHistoryFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fragmentManager, int NumOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopUpTransactionsHistoryFragment();
            case 1:
                return new TransferTransactionsHistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
