package es.shwebill.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import es.shwebill.R;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.BasicFragment;
import es.shwebill.domain.type.TransferTransactions;
import es.shwebill.domain.type.TransferTransactionsFilterType;
import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;
import es.shwebill.ui.adapter.TransferTransactionsHistoryListAdapter;
import es.shwebill.ui.viewmodel.TransferTransactionsHistoryViewModel;

public class TransferTransactionsHistoryFragment extends BasicFragment implements DatePickerDialog.OnDateSetListener {

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

    private TransferTransactionsHistoryListAdapter transferTransactionsHistoryListAdapter;
    private TransferTransactionsHistoryViewModel transferTransactionsHistoryViewModel;
    private List<TransferTransactionsHistoryEntity> transferTransactionsHistoryList = new ArrayList<>();
    private List<TransferTransactionsHistoryEntity> transferTransactionsHistoryFilterList = new ArrayList<>();

    @Override
    protected void createView() {

        lblFilterTypeLabel.setText(getString(R.string.fragment_transactions_history__type));

        transferTransactionsHistoryListAdapter = new TransferTransactionsHistoryListAdapter(new ArrayList<>());

        recyclerViewTransactions.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerViewTransactions.setLayoutManager(mLayoutManager);

       /* Add ItemDecoration
        recyclerViewTransactions.addItemDecoration(
                new RecyclerViewDividerItemDecoration(getActivity(), R.drawable.view_divider));
        */

        /* Instantiate view model */
        transferTransactionsHistoryViewModel = ViewModelProviders.of(this).get(TransferTransactionsHistoryViewModel.class);

        //Dummy
        transferTransactionsHistoryViewModel.saveTransferTransactionsHistory(new TransferTransactionsHistoryEntity("Wai Yan Phyoe", 200000, new Date(), TransferTransactions.SEND));
        transferTransactionsHistoryViewModel.saveTransferTransactionsHistory(new TransferTransactionsHistoryEntity("Maung Maung", 800000, new Date(), TransferTransactions.RECEIVE));

        recyclerViewTransactions.setAdapter(transferTransactionsHistoryListAdapter);
        this.loadAllData();
    }

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_transcations_history;
    }

    @Override
    public void refresh() {

    }

    private void loadAllData() {
        transferTransactionsHistoryViewModel.getAllTransferTransactionsHistory().observe(this, transferTransactionsHistoryEntities -> {
            assert transferTransactionsHistoryEntities != null;

            transferTransactionsHistoryList = transferTransactionsHistoryEntities;

            this.recyclerViewVisibility(transferTransactionsHistoryList.size());
            transferTransactionsHistoryListAdapter.addItems(transferTransactionsHistoryList);
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

    @OnClick(R.id.lblFilterType)
    public void showFilterTransferTypeDialog() {
        AndroidUtil.showMaterialDialog(
                getActivity(), R.string.fragment_transactions_history__choose_type,
                R.array.fragment_transactions_history__filter_transfer_type,
                R.string.fragment_transactions_history__choose_label,
                (dialog, itemView, which, text) -> {
                    String chosenText = text.toString();
                    lblFilterType.setText(chosenText);
                    selectedTransferType(chosenText);
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

    private void selectedTransferType(String transferType) {
        TransferTransactionsFilterType transferTransactionsFilterType = TransferTransactionsFilterType.valueOf(transferType.toUpperCase());
        switch (transferTransactionsFilterType) {
            case ALL:
                this.loadAllData();
                break;
            case SEND:
                loadTransferTypeFilter(TransferTransactions.SEND);
                break;
            case RECEIVE:
                loadTransferTypeFilter(TransferTransactions.RECEIVE);
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

    private void loadTransferTypeFilter(TransferTransactions transferTransactions) {
        if (!transferTransactionsHistoryFilterList.isEmpty()) {
            transferTransactionsHistoryFilterList.clear();
        }
        for (TransferTransactionsHistoryEntity transferTransactionsHistoryEntity : this.transferTransactionsHistoryList) {
            if (transferTransactionsHistoryEntity.getTransferTransactions() == transferTransactions) {
                transferTransactionsHistoryFilterList.add(transferTransactionsHistoryEntity);
            }
        }
        this.recyclerViewVisibility(transferTransactionsHistoryFilterList.size());
        transferTransactionsHistoryListAdapter.addItems(transferTransactionsHistoryFilterList);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
