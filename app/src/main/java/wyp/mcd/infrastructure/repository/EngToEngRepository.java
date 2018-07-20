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

package wyp.mcd.infrastructure.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import wyp.mcd.infrastructure.dao.EngToEngDao;
import wyp.mcd.infrastructure.entities.EngToEngEntity;
import wyp.mcd.infrastructure.pesistance.McdRoomDatabase;

public class EngToEngRepository {

    private EngToEngDao engToEngDao;

    public EngToEngRepository(Application application) {
        McdRoomDatabase mcdRoomDatabase = McdRoomDatabase.getDatabase(application);
        engToEngDao = mcdRoomDatabase.engToEngDao();
    }

    public LiveData<List<EngToEngEntity>> getAll() {
        return engToEngDao.getAll();
    }

    public int getCount() {
        return engToEngDao.getCount();
    }

    public EngToEngEntity getEngToEng(String vocabulary) {
        return engToEngDao.getEngToEng(vocabulary);
    }

    public void insert(EngToEngEntity engToEngEntity) {
        new InsertAsyncTask(engToEngDao).execute(engToEngEntity);
    }

    public LiveData<List<EngToEngEntity>> find(String vocabulary) {
        try {
            return new FindAsyncTask(engToEngDao).execute(vocabulary).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InsertAsyncTask extends AsyncTask<EngToEngEntity, Void, Void> {

        private EngToEngDao mAsyncTaskDao;

        InsertAsyncTask(EngToEngDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(final EngToEngEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class FindAsyncTask extends AsyncTask<String, Void, LiveData<List<EngToEngEntity>>> {

        private EngToEngDao mAsyncTaskDao;

        FindAsyncTask(EngToEngDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected LiveData<List<EngToEngEntity>> doInBackground(String... vocabulary) {
            return mAsyncTaskDao.find(vocabulary[0]);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(LiveData<List<EngToEngEntity>> listLiveData) {
            super.onPostExecute(listLiveData);
        }
    }

}
