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

package wyp.mcd.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import wyp.mcd.R;
import wyp.mcd.component.android.BasicFragment;
import wyp.mcd.component.type.DictionaryType;
import wyp.mcd.ui.adapter.SearchEngListAdapter;
import wyp.mcd.ui.adapter.SearchMmListAdapter;
import wyp.mcd.ui.uicomponents.LanguageOptionsPopupMenu;
import wyp.mcd.ui.uicomponents.RecyclerViewDividerItemDecoration;
import wyp.mcd.viewmodel.SearchViewModel;

public class SearchFragment extends BasicFragment implements LanguageOptionsPopupMenu.LanguageOptionsPopupMenuListener {

    @BindView(R.id.btnOptions)
    AppCompatImageButton btnOptions;

    @BindView(R.id.btnClear)
    AppCompatImageButton btnClear;

    @BindView(R.id.txtSearch)
    AppCompatEditText txtSearch;

    @BindView(R.id.recyclerViewSearch)
    FastScrollRecyclerView recyclerViewSearch;

    private LanguageOptionsPopupMenu languageOptionsPopupMenu = null;
    private DictionaryType dictionaryType = DictionaryType.ENG_TO_MM;

    private SearchMmListAdapter searchMmListAdapter;
    private SearchEngListAdapter searchEngListAdapter;
    private SearchViewModel searchViewModel;

    @Override
    public void createView() {

        /* Implement language options popup menu */
        languageOptionsPopupMenu = new LanguageOptionsPopupMenu(getActivity(), this);

        /* Implement adapter */
        searchMmListAdapter = new SearchMmListAdapter(new ArrayList<>(), getActivity());
        searchEngListAdapter = new SearchEngListAdapter(new ArrayList<>(), getActivity());

        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));

        /* Add ItemDecoration */
        recyclerViewSearch.addItemDecoration(
                new RecyclerViewDividerItemDecoration(getActivity(), R.drawable.view_divider));

        /* Instantiate view model */
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    @Override
    public int getLayoutXmlId() {
        return R.layout.fragment_search;
    }

    @Override
    public void refresh() {

    }

    @OnClick(R.id.btnOptions)
    public void showOptions(View view) {
        languageOptionsPopupMenu.showLanguageOptionsMenu(view);
    }

    @Override
    public void actionEngToMM() {
        this.dictionaryType = DictionaryType.ENG_TO_MM;
    }

    @Override
    public void actionEngToEng() {
        this.dictionaryType = DictionaryType.ENG_TO_ENG;
    }

    @OnTextChanged(R.id.txtSearch)
    public void onSearchTextFocusChanged(CharSequence text) {
        String searchWord = text.toString();
        if (!searchWord.isEmpty() && !(Character.isWhitespace(searchWord.charAt(0)) && Character.isWhitespace(searchWord.charAt(searchWord.length() - 1)))) {
            btnOptions.setVisibility(View.GONE);
            btnClear.setVisibility(View.VISIBLE);
            recyclerViewSearch.setVisibility(View.VISIBLE);
            /* Set Data to adapter */
            loadData(searchWord.trim() + "%");
        } else {
            btnOptions.setVisibility(View.VISIBLE);
            btnClear.setVisibility(View.GONE);
            recyclerViewSearch.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnClear)
    public void clearText() {
        txtSearch.setText("");
    }

    private void loadData(String vocabulary) {

        if (DictionaryType.ENG_TO_MM == dictionaryType) {
            recyclerViewSearch.setAdapter(searchMmListAdapter);
            searchViewModel.findEngToMm(vocabulary).observe(this, engToMmEntities -> {
                assert engToMmEntities != null;
                searchMmListAdapter.addItem(engToMmEntities);
            });
        }
        if (DictionaryType.ENG_TO_ENG == dictionaryType) {
            recyclerViewSearch.setAdapter(searchEngListAdapter);
            searchViewModel.findEngToEng(vocabulary).observe(this, engToEngEntities -> {
                assert engToEngEntities != null;
                searchEngListAdapter.addItems(engToEngEntities);
            });
        }
    }

}
