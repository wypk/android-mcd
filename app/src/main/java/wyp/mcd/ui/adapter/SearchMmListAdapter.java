/*
 * Copyright (C) 2018
 *
 * Source code is created by Elissa Software
 * Dictionary data is owned by UCST
 * Database is implemented by Salai Chit Oo Latt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.l4digital.fastscroll.FastScroller;

import java.util.List;

import wyp.mcd.R;
import wyp.mcd.infrastructure.entities.EngToMmEntity;
import wyp.mcd.ui.activity.DetailResultActivity;

public class SearchMmListAdapter extends RecyclerView.Adapter<SearchMmListAdapter.ViewHolder> implements FastScroller.SectionIndexer {

    private List<EngToMmEntity> dataSet;
    private Context context;

    public SearchMmListAdapter(List<EngToMmEntity> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.context = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dictionary_item, parent, false);
        return new SearchMmListAdapter.ViewHolder(itemView, this.context, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        EngToMmEntity engToMmEntity = this.dataSet.get(position);

        holder.lblVocabulary.setText(engToMmEntity.getVocabulary());
        holder.lblMeaning.setText(engToMmEntity.getMeaning());
        holder.itemView.setTag(engToMmEntity);
    }

    /**
     * getItemCount() is called many times, and when it is first called, dataSet has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (dataSet != null)
            return dataSet.size();
        else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        this.dataSet.clear();
    }

    public void addItem(@NonNull List<EngToMmEntity> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
        notifyItemInserted(dataSet.size() - 1);
    }

    @Override
    public String getSectionText(int position) {
        return String.valueOf(dataSet.get(position).getVocabulary().toUpperCase().charAt(0));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView lblVocabulary;
        private final AppCompatTextView lblMeaning;
        private Context mContext;
        private SearchMmListAdapter adapter;

        ViewHolder(View itemView, Context mContext, SearchMmListAdapter adapter) {
            super(itemView);
            this.mContext = mContext;
            this.adapter = adapter;

            this.lblVocabulary = itemView.findViewById(R.id.lblVocabulary);
            this.lblMeaning = itemView.findViewById(R.id.lblMeaning);

            itemView.setOnClickListener(view -> {
                EngToMmEntity engToMmEntity = this.adapter.dataSet.get(this.getAdapterPosition());

                Intent intent = new Intent(this.mContext, DetailResultActivity.class);
                intent.putExtra("vocabularyKey", engToMmEntity.getVocabulary());
                intent.putExtra("typeKey", "( " + engToMmEntity.getType() + " )");
                intent.putExtra("meaningKey", engToMmEntity.getMeaning());
                this.mContext.startActivity(intent);
            });
        }
    }
}