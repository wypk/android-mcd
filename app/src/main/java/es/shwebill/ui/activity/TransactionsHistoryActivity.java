package es.shwebill.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import es.shwebill.R;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.ui.adapter.ViewPagerAdapter;

public class TransactionsHistoryActivity extends BasicActivity {

    @BindView(R.id.tlTransactionsLabel)
    TabLayout tlTransactionsLabel;

    @BindView(R.id.vpTransactionsList)
    ViewPager vpTransactionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTransactionsLabelUI();
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_transactions_history;
    }

    @Override
    protected String getScreenTitleAtStart() {
        return getString(R.string.activity_transactions_history__transactions_history);
    }

    @Override
    protected Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    protected Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    private void setTransactionsLabelUI() {

        tlTransactionsLabel.addTab(tlTransactionsLabel.newTab().setText(getString(R.string.transaction_type__top_up)));
        tlTransactionsLabel.addTab(tlTransactionsLabel.newTab().setText(getString(R.string.transaction_type__transfer)));

        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tlTransactionsLabel.getTabCount());
        vpTransactionsList.setAdapter(adapter);
        vpTransactionsList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlTransactionsLabel));
        tlTransactionsLabel.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpTransactionsList.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
