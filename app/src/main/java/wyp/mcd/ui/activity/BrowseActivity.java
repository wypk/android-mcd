/*
 * Copyright (C) 2018
 *  Source code is created by Elissa Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package wyp.mcd.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;

import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import wyp.mcd.R;
import wyp.mcd.component.ui.BasicActivity;
import wyp.mcd.component.type.DictionaryType;
import wyp.mcd.ui.adapter.SearchEngListAdapter;
import wyp.mcd.ui.adapter.SearchMmListAdapter;
import wyp.mcd.component.ui.RecyclerViewDividerItemDecoration;
import wyp.mcd.viewmodel.BrowseListViewModel;

public class BrowseActivity extends BasicActivity {

    @BindView(R.id.recyclerViewBrowse)
    FastScrollRecyclerView recyclerViewBrowse;

    @BindView(R.id.toggleImageBtn)
    AppCompatImageButton toggleBtnDictionaryType;

    private DictionaryType dictionaryType;
    private boolean dictionaryTypeWatcher;
    private SearchMmListAdapter searchMmListAdapter;
    private SearchEngListAdapter searchEngListAdapter;
    private BrowseListViewModel browseListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Set Default when create activity */
        this.setDefaultDictionaryType();

        /* Implement adapter */
        searchMmListAdapter = new SearchMmListAdapter(new ArrayList<>(), this);
        searchEngListAdapter = new SearchEngListAdapter(new ArrayList<>(), this);

        recyclerViewBrowse.setHasFixedSize(true);
        recyclerViewBrowse.setLayoutManager(new LinearLayoutManager(this));

        /* Add ItemDecoration */
        recyclerViewBrowse.addItemDecoration(
                new RecyclerViewDividerItemDecoration(this, R.drawable.view_divider));

        /* Instantiate view model */
        browseListViewModel = ViewModelProviders.of(this).get(BrowseListViewModel.class);

        /* First load data */
        loadData();
    }

    @Override
    public int getLayoutFileId() {
        return R.layout.activity_browse;
    }

    @Override
    public String getScreenTitleAtStart() {
        return getString(R.string.title_browse);
    }

    @Override
    public Class<? extends BasicActivity> getDefaultBackScreenIfRoot() {
        return null;
    }

    @Override
    public Bundle getDefaultBackScreenExtraIfRoot() {
        return null;
    }

    @OnClick(R.id.toggleImageBtn)
    public void clickToggleBtnDictionaryType() {
        if (dictionaryTypeWatcher) {
            toggleBtnDictionaryType.setImageResource(R.drawable.ic_myanmar);
            dictionaryTypeWatcher = false;
            this.dictionaryType = DictionaryType.ENG_TO_ENG;
            loadData();
        } else {
            toggleBtnDictionaryType.setImageResource(R.drawable.ic_united_kingdom);
            dictionaryTypeWatcher = true;
            this.dictionaryType = DictionaryType.ENG_TO_MM;
            loadData();
        }
    }

    private void loadData() {

        if (DictionaryType.ENG_TO_MM == dictionaryType) {
            recyclerViewBrowse.setAdapter(searchMmListAdapter);
            browseListViewModel.getAllEngToMm().observe(this, engToMmEntities -> {
                assert engToMmEntities != null;
                searchMmListAdapter.addItem(engToMmEntities);
            });
        }
        if (DictionaryType.ENG_TO_ENG == dictionaryType) {
            recyclerViewBrowse.setAdapter(searchEngListAdapter);
            browseListViewModel.getAllEngToEng().observe(this, engToEngEntities -> {
                assert engToEngEntities != null;
                searchEngListAdapter.addItems(engToEngEntities);
            });
        }
    }

    private void setDefaultDictionaryType() {
        dictionaryType = DictionaryType.ENG_TO_MM;
        toggleBtnDictionaryType.setImageResource(R.drawable.ic_united_kingdom);
        dictionaryTypeWatcher = true;
    }

}
