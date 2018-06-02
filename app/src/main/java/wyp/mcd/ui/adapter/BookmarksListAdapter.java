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

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.l4digital.fastscroll.FastScroller;

import java.util.List;

import wyp.mcd.R;
import wyp.mcd.infrastructure.entities.BookmarksEntity;

public class BookmarksListAdapter extends RecyclerView.Adapter<BookmarksListAdapter.BookmarksViewHolder> implements FastScroller.SectionIndexer {

    private List<BookmarksEntity> dataSet;
    private View.OnLongClickListener longClickListener;
    private View.OnClickListener clickListener;

    public BookmarksListAdapter(List<BookmarksEntity> dataSet, View.OnLongClickListener longClickListener, View.OnClickListener clickListener) {
        this.dataSet = dataSet;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BookmarksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookmarksViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_bookmark_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BookmarksViewHolder holder, int position) {

        BookmarksEntity bookmarksEntity = dataSet.get(position);

        holder.lblBookmarkItem.setText(bookmarksEntity.getBookmarkWord());
        holder.itemView.setTag(bookmarksEntity);
        holder.itemView.setOnLongClickListener(longClickListener);
        holder.itemView.setOnClickListener(clickListener);
    }

    public void addItems(List<BookmarksEntity> bookmarkList) {
        this.dataSet = bookmarkList;
        notifyDataSetChanged();
        notifyItemInserted(bookmarkList.size() - 1);
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

    @Override
    public String getSectionText(int position) {
        return String.valueOf(dataSet.get(position).getBookmarkWord().toUpperCase().charAt(0));
    }

    static class BookmarksViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView lblBookmarkItem;

        BookmarksViewHolder(View view) {
            super(view);
            lblBookmarkItem = view.findViewById(R.id.lblBookmark);
        }
    }

}
