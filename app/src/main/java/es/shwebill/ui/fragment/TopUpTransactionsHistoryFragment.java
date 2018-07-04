package es.shwebill.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.BasicFragment;
import es.shwebill.component.util.Logger;
import es.shwebill.domain.type.OperatorFilterType;
import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;
import es.shwebill.ui.adapter.TopUpTransactionsHistoryListAdapter;
import es.shwebill.ui.viewmodel.TopUpTransactionsHistoryViewModel;

public class TopUpTransactionsHistoryFragment extends BasicFragment
        implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.recyclerViewTransactions)
    RecyclerView recyclerViewTransactions;

    @BindView(R.id.rlNoTransactions)
    RelativeLayout rlNoTransactions;

    @BindView(R.id.lblFilterTypeLabel)
    AppCompatTextView lblFilterTypeLabel;

    @BindView(R.id.lblFilterType)
    AppCompatTextView lblFilterType;

    @BindView(R.id.lblDateChooser)
    AppCompatTextView lblDateChooser;

    private TopUpTransactionsHistoryListAdapter topUpTransactionsHistoryListAdapter;
    private TopUpTransactionsHistoryViewModel topUpTransactionsHistoryViewModel;
    private List<TopUpTransactionsHistoryEntity> topUpTransactionsHistoryList = new ArrayList<>();

    @Override
    protected void createView() {

        lblFilterTypeLabel.setText(getString(R.string.fragment_transactions_history__operator));

        topUpTransactionsHistoryListAdapter = new TopUpTransactionsHistoryListAdapter(
                new ArrayList<>());
        recyclerViewTransactions.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerViewTransactions.setLayoutManager(mLayoutManager);
        /* Add ItemDecoration
        recyclerViewTransactions.addItemDecoration(new RecyclerViewDividerItemDecoration(getActivity(), R.drawable.view_divider));
        */

        /* Instantiate view model */
        topUpTransactionsHistoryViewModel = ViewModelProviders
                .of(this)
                .get(TopUpTransactionsHistoryViewModel.class);

        //Dummy
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.MPT));
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.OOREDOO));
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.TELENOR));
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.MPT_ELOAD));
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.MECTEL));
        topUpTransactionsHistoryViewModel.saveTopUpTransactionsHistory(
                new TopUpTransactionsHistoryEntity("09428155046", 1000.00, new Date(),
                        Operators.MYTEL));

        recyclerViewTransactions.setAdapter(topUpTransactionsHistoryListAdapter);
        this.loadAllData();
    }

    @Override
    protected int getLayoutXmlId() {

        return R.layout.fragment_transcations_history;
    }

    @Override
    public void refresh() {

    }

    @OnClick(R.id.lblFilterType)
    public void showFilterOperatorTypeDialog() {
        AndroidUtil.showMaterialDialog(
                getActivity(), R.string.fragment_transactions_history__choose_operator,
                R.array.fragment_transactions_history__operators_list,
                R.string.fragment_transactions_history__choose_label,
                (dialog, itemView, which, text) -> {
                    String chosenText = text.toString();
                    lblFilterType.setText(chosenText);
                    selectedOperator(chosenText);
                    return true;
                });
    }

    @OnClick(R.id.lblDateChooser)
    public void showFilterDateDialog() {
        AndroidUtil.showMaterialDialog(
                getActivity(), R.string.fragment_transactions_history__choose_date,
                R.array.fragment_transactions_history__filter_date,
                R.string.fragment_transactions_history__choose_label,
                (dialog, itemView, which, text) -> {
                    String chosenText = text.toString();
                    lblDateChooser.setText(chosenText);
                    selectedDate(which);
                    return true;
                });
    }

    private void loadAllData() {

        topUpTransactionsHistoryViewModel
                .getAllTopUpTransactionsHistory()
                .observe(this, topUpTransactionsHistoryEntities -> {
                    assert topUpTransactionsHistoryEntities != null;
                    //Temp Store In array list
                    this.topUpTransactionsHistoryList = topUpTransactionsHistoryEntities;

                    this.recyclerViewVisibility(topUpTransactionsHistoryList.size());
                    topUpTransactionsHistoryListAdapter.addItems(topUpTransactionsHistoryList);
                });
    }

    private void recyclerViewVisibility(int size) {

        if (0 == size) {
            rlNoTransactions.setVisibility(View.VISIBLE);
            recyclerViewTransactions.setVisibility(View.GONE);
        } else {
            rlNoTransactions.setVisibility(View.GONE);
            recyclerViewTransactions.setVisibility(View.VISIBLE);
        }
    }

    private void selectedOperator(String operatorName) {

        OperatorFilterType operatorFilterType = OperatorFilterType.valueOf(
                operatorName.toUpperCase());
        switch (operatorFilterType) {
            case ALL:
                this.loadAllData();
                break;
            case MPT:
                this.loadOperatorFilterData(Operators.MPT);
                break;
            case MPT_ELOAD:
                this.loadOperatorFilterData(Operators.MPT_ELOAD);
                break;
            case TELENOR:
                this.loadOperatorFilterData(Operators.TELENOR);
                break;
            case OOREDOO:
                this.loadOperatorFilterData(Operators.OOREDOO);
                break;
            case MYTEL:
                this.loadOperatorFilterData(Operators.MYTEL);
                break;
            case MECTEL:
                this.loadOperatorFilterData(Operators.MECTEL);
                break;
        }
    }

    private void selectedDate(int position) {

        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                AndroidUtil.showDatePickerDialog(Objects.requireNonNull(getActivity()), this);
                break;
        }
    }

    private void loadOperatorFilterData(Operators operators) {
        topUpTransactionsHistoryViewModel
                .getFilterWithOperatorType(operators)
                .observe(this, topUpTransactionsHistoryEntities -> {
                    assert topUpTransactionsHistoryEntities != null;
                    //Temp Store In array list
                    this.topUpTransactionsHistoryList = topUpTransactionsHistoryEntities;

                    this.recyclerViewVisibility(topUpTransactionsHistoryList.size());
                    topUpTransactionsHistoryListAdapter.addItems(topUpTransactionsHistoryList);
                });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        for (TopUpTransactionsHistoryEntity topUpTransactionsHistoryEntity : this.topUpTransactionsHistoryList) {

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(topUpTransactionsHistoryEntity.getTopUpDate());
            calendar2.set(year, monthOfYear, dayOfMonth);

            Logger.d(TopUpTransactionsHistoryFragment.class, "User choose date:" + calendar2);

            if (calendar1.equals(calendar2)) {
                topUpTransactionsHistoryList.add(topUpTransactionsHistoryEntity);
            }

        }
        this.recyclerViewVisibility(topUpTransactionsHistoryList.size());
        topUpTransactionsHistoryListAdapter.addItems(topUpTransactionsHistoryList);
    }
}
