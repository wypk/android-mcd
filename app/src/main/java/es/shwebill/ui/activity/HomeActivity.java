package es.shwebill.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.BasicActivity;
import es.shwebill.component.util.LanguageHelper;
import es.shwebill.component.util.Logger;
import es.shwebill.component.util.TransitionUtil;
import es.shwebill.domain.model.OperatorItem;
import es.shwebill.ui.adapter.OperatorsListAdapter;

public class HomeActivity extends BasicActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private OperatorsListAdapter operatorsListAdapter;
    private Context mContext;
    private GridLayoutManager gridLayoutManager;

    @BindView(R.id.switchBtnLanguageMode)
    SwitchCompat switchBtnLanguageMode;

    @BindView(R.id.imvProfilePhoto)
    ImageView imvProfilePhoto;

    @BindView(R.id.lblFullName)
    AppCompatTextView lblFullName;

    @BindView(R.id.nav_lblTransfer)
    AppCompatTextView nav_lblTransfer;

    @BindView(R.id.nav_lblTransactionHistory)
    AppCompatTextView nav_lblTransactionHistory;

    @BindView(R.id.nav_lblHelpCenter)
    AppCompatTextView nav_lblHelpCenter;

    @BindView(R.id.nav_lblSettings)
    AppCompatTextView nav_lblSettings;

    @BindView(R.id.rvOperators)
    RecyclerView rvOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        this.setToolbar();

        drawerLayout = findViewById(R.id.activity_radar_drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                /* Code here will be triggered once the drawerLayout closes as we don't want anything to happen so we leave this blank */
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                /* Code here will be triggered once the drawerLayout open as we dont want anything to happen so we leave this blank */
                super.onDrawerOpened(drawerView);
            }
        };

        /* Setting the actionbarToggle to drawerLayout layout */
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        /* calling sync state is necessary or else your hamburger icon wont show up */
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        this.invalidateOptionsMenu();

        /* Add Operators to Recycler view */
        setOperatorsToRecyclerView();

        setSelectedLanguageSwitchCompat();
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_home;
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* toggle nav drawerLayout on selecting action bar app icon/title */
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        /* Sync the toggle state after onRestoreInstanceState has occurred. */
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        /* Pass any configuration change to the drawerLayout toggle */
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(Gravity.LEFT); //OPEN Nav Drawer!
        } else {
            finish();
        }
    }

    private void setToolbar() {

        toolbar = findViewById(R.id.toolbar_nav);
        toolbar.findViewById(R.id.navigation_menu)
                .setOnClickListener(v -> {
                    Logger.log("Drawer calling...");
                    if (drawerLayout.isDrawerOpen(navigationView)) {
                        drawerLayout.closeDrawer(navigationView);
                    } else {
                        drawerLayout.openDrawer(navigationView);
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.activity_radar_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setOperatorsToRecyclerView() {

        gridLayoutManager = new GridLayoutManager(mContext, 2);

        rvOperators.setHasFixedSize(true);
        rvOperators.setLayoutManager(gridLayoutManager);

        operatorsListAdapter = new OperatorsListAdapter(OperatorsListAdapter.getAllItemList(), this);
        rvOperators.setAdapter(operatorsListAdapter);
        operatorsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        OperatorItem operatorItem = (OperatorItem) view.getTag();
        Logger.log(operatorItem.getOperatorName() + " :Clicked");

        HashMap<String, Serializable> data = new HashMap<>();
        data.put("operator-type", operatorItem.getOperatorName());

        TransitionUtil.showNextActivityWithMap(this, TopUpActivity.class, data,
                R.anim.entering_screen_sliding_from_right,
                R.anim.exiting_screen_sliding_to_left,
                false);
    }

    @OnClick(R.id.imvProfilePhoto)
    public void goToMyProfileActivity() {

        drawerLayout.closeDrawer(GravityCompat.START);
        Logger.log("Calling profile activity.");

        TransitionUtil.showNextActivityWithMap(this, ProfileActivity.class, null,
                R.anim.slide_up,
                R.anim.slide_down,
                false);
    }

    @OnClick(R.id.nav_lblTransfer)
    public void goToTransferActivity() {

        drawerLayout.closeDrawer(GravityCompat.START);
        Logger.log("Calling transfer activity.");
        TransitionUtil.showNextActivityWithMap(this, TransferBalanceActivity.class, null,
                R.anim.slide_up,
                R.anim.slide_down,
                false);
    }

    @OnClick(R.id.nav_lblTransactionHistory)
    public void goToTransactionHistoryActivity() {

        drawerLayout.closeDrawer(GravityCompat.START);
        Logger.log("Calling transaction history activity.");
        TransitionUtil.showNextActivityWithMap(this, TransactionsHistoryActivity.class, null,
                R.anim.slide_up,
                R.anim.slide_down,
                false);
    }

    @OnClick(R.id.nav_lblHelpCenter)
    public void goToHelpCenter() {

        drawerLayout.closeDrawer(GravityCompat.START);
        Logger.log("Calling help center activity.");
        TransitionUtil.showNextActivityWithMap(this, HelpCenterActivity.class, null,
                R.anim.slide_up,
                R.anim.slide_down,
                false);
    }

    @OnClick(R.id.nav_lblSettings)
    public void goToSettingsActivity() {

        drawerLayout.closeDrawer(GravityCompat.START);
        Logger.log("Calling settings activity.");
        TransitionUtil.showNextActivityWithMap(this, SettingsActivity.class, null,
                R.anim.slide_up,
                R.anim.slide_down,
                false);
    }

    private void setSelectedLanguageSwitchCompat() {

        switch (LanguageHelper.getLanguage(this)) {
            case "en":
                switchBtnLanguageMode.setChecked(true);
                break;
            case "my":
                switchBtnLanguageMode.setChecked(false);
                break;
        }
    }

    private void switchLanguage(Activity activity, String languageCode) {
        LanguageHelper.setLanguage(activity, languageCode);
        AndroidUtil.relaunchActivity(activity);
    }

    @OnClick(R.id.switchBtnLanguageMode)
    public void clickSwitchLanguage() {

        Logger.d(this.getClass(), "switchBtnLanguageMode.isChecked() : " +
                switchBtnLanguageMode.isChecked());

        if (switchBtnLanguageMode.isChecked()) {
            /* If the switch button is on */
            Logger.d(this.getClass(), "switchBtnLanguageMode : English");
            this.switchLanguage(this, "en");
        } else {
            /* If the switch button is off */
            Logger.d(this.getClass(), "switchBtnLanguageMode : Myanmar");
            this.switchLanguage(this, "my");
        }
    }
}
