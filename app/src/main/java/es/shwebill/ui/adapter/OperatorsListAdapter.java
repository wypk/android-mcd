package es.shwebill.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.shwebill.R;
import es.shwebill.constants.OperatorsName;
import es.shwebill.domain.model.OperatorItem;

public class OperatorsListAdapter extends RecyclerView.Adapter<OperatorsListAdapter.OperatorsViewHolder> {

    private List<OperatorItem> dataSet;
    private View.OnClickListener clickListener;

    public OperatorsListAdapter(List<OperatorItem> dataSet, View.OnClickListener clickListener) {
        this.dataSet = dataSet;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public OperatorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OperatorsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_operator_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final OperatorsViewHolder holder, int position) {

        OperatorItem operatorItem = dataSet.get(position);

        holder.imvOperatorIcon.setImageResource(operatorItem.getOperatorIcon());
        holder.lblOperatorName.setText(operatorItem.getOperatorName());

        holder.itemView.setTag(operatorItem);
        holder.itemView.setOnClickListener(clickListener);
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * dataSet has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (dataSet != null)
            return dataSet.size();
        else
            return 0;
    }

    static class OperatorsViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView lblOperatorName;
        private AppCompatImageView imvOperatorIcon;

        OperatorsViewHolder(View view) {
            super(view);
            lblOperatorName = view.findViewById(R.id.lblOperatorName);
            imvOperatorIcon = view.findViewById(R.id.imvOperatorIcon);
        }
    }

    public static List<OperatorItem> getAllItemList() {
        ArrayList<OperatorItem> allItems = new ArrayList<>();
        allItems.add(new OperatorItem(OperatorsName.mpt, R.drawable.dummy_operator));
        allItems.add(new OperatorItem(OperatorsName.mptEload, R.drawable.dummy_operator));
        allItems.add(new OperatorItem(OperatorsName.telenor, R.drawable.dummy_operator));
        allItems.add(new OperatorItem(OperatorsName.ooredoo, R.drawable.dummy_operator));
        allItems.add(new OperatorItem(OperatorsName.myTel, R.drawable.dummy_operator));
        allItems.add(new OperatorItem(OperatorsName.mecTel, R.drawable.dummy_operator));
        return allItems;
    }
}
