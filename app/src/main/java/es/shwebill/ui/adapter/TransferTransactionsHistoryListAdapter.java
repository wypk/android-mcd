package es.shwebill.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;

import es.shwebill.R;
import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;

public class TransferTransactionsHistoryListAdapter extends RecyclerView.Adapter<TransferTransactionsHistoryListAdapter.TransferTransactionsHistoryViewHolder> {

    private List<TransferTransactionsHistoryEntity> dataSet;

    public TransferTransactionsHistoryListAdapter(List<TransferTransactionsHistoryEntity> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public TransferTransactionsHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransferTransactionsHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transfer_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransferTransactionsHistoryViewHolder holder, int position) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy");

        TransferTransactionsHistoryEntity transferTransactionsHistoryEntity = dataSet.get(position);
        holder.lblAccount.setText(transferTransactionsHistoryEntity.getAccountName());
        holder.lblDate.setText(String.valueOf(df.format(transferTransactionsHistoryEntity.getTransferDate())));
        holder.lblAmount.setText(String.format("%s Ks", transferTransactionsHistoryEntity.getAmount()));
        switch (transferTransactionsHistoryEntity.getTransferTransactions()) {
            case SEND:
                holder.lblAmount.setBackgroundColor(Color.parseColor("#ed1c24"));
                break;
            case RECEIVE:
                holder.lblAmount.setBackgroundColor(Color.parseColor("#43a047"));
                break;
        }

    }

    public void addItems(List<TransferTransactionsHistoryEntity> transferTransactionsHistoryEntity) {
        this.dataSet = transferTransactionsHistoryEntity;
        notifyDataSetChanged();
        notifyItemInserted(transferTransactionsHistoryEntity.size() - 1);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    static class TransferTransactionsHistoryViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView lblAccount;
        private final AppCompatTextView lblDate;
        private final AppCompatTextView lblAmount;

        TransferTransactionsHistoryViewHolder(View view) {
            super(view);
            lblAccount = view.findViewById(R.id.lblAccount);
            lblDate = view.findViewById(R.id.lblDate);
            lblAmount = view.findViewById(R.id.lblAmount);
        }
    }
}
