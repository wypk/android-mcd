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
import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;

public class TopUpTransactionsHistoryListAdapter extends RecyclerView.Adapter<TopUpTransactionsHistoryListAdapter.TopUpTransactionsHistoryViewHolder> {

    private List<TopUpTransactionsHistoryEntity> dataSet;

    public TopUpTransactionsHistoryListAdapter(List<TopUpTransactionsHistoryEntity> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public TopUpTransactionsHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopUpTransactionsHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_top_up_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopUpTransactionsHistoryViewHolder holder, int position) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy");

        TopUpTransactionsHistoryEntity topUpTransactionsHistoryEntity = dataSet.get(position);
        holder.lblPhoneNumber.setText(topUpTransactionsHistoryEntity.getTopUpPhoneNumber());
        holder.lblDate.setText(String.valueOf(df.format(topUpTransactionsHistoryEntity.getTopUpDate())));
        holder.lblAmount.setText(String.format("%s Ks", topUpTransactionsHistoryEntity.getAmount()));

        switch (topUpTransactionsHistoryEntity.getOperatorName()) {
            case MPT:
                holder.lblOperatorName.setText(String.valueOf(Operators.MPT));
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#fbc02d"));
                break;
            case MPT_ELOAD:
                holder.lblOperatorName.setText("MPT Eload");
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#fbc02d"));
                break;
            case TELENOR:
                holder.lblOperatorName.setText("Telenor");
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#19aaf8"));
                break;
            case OOREDOO:
                holder.lblOperatorName.setText("Ooredoo");
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#ed1c24"));
                break;
            case MYTEL:
                holder.lblOperatorName.setText("MyTel");
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#f26522"));
                break;
            case MECTEL:
                holder.lblOperatorName.setText("MecTel");
                holder.lblOperatorName.setBackgroundColor(Color.parseColor("#428bca"));
                break;
        }
    }

    public void addItems(List<TopUpTransactionsHistoryEntity> topUpTransactionsHistoryEntity) {
        this.dataSet = topUpTransactionsHistoryEntity;
        notifyDataSetChanged();
        notifyItemInserted(topUpTransactionsHistoryEntity.size() - 1);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    static class TopUpTransactionsHistoryViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView lblPhoneNumber;
        private final AppCompatTextView lblDate;
        private final AppCompatTextView lblAmount;
        private final AppCompatTextView lblOperatorName;

        TopUpTransactionsHistoryViewHolder(View view) {
            super(view);
            lblPhoneNumber = view.findViewById(R.id.lblPhoneNumber);
            lblDate = view.findViewById(R.id.lblDate);
            lblAmount = view.findViewById(R.id.lblAmount);
            lblOperatorName = view.findViewById(R.id.lblOperatorName);
        }
    }
}
