/*
 * Copyright 2019 Wai Yan (TechBase Software). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.infrastructure.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import wyp.mcd.infrastructure.dao.EngToMmDao;
import wyp.mcd.infrastructure.entities.EngToMmEntity;
import wyp.mcd.infrastructure.pesistance.McdRoomDatabase;

public class EngToMmRepository {

    private EngToMmDao engToMmDao;

    public EngToMmRepository(Application application) {
        McdRoomDatabase mcdRoomDatabase = McdRoomDatabase.getDatabase(application);
        engToMmDao = mcdRoomDatabase.engToMmDao();
    }

    public LiveData<List<EngToMmEntity>> getAll() {
        return engToMmDao.getAll();
    }

    public int getCount() {
        return engToMmDao.getCount();
    }

    public EngToMmEntity getEngToMm(String vocabulary) {
        return engToMmDao.getEngToMm(vocabulary);
    }

    public void insert(EngToMmEntity engToMmEntity) {
        new InsertAsyncTask(engToMmDao).execute(engToMmEntity);
    }

    public LiveData<List<EngToMmEntity>> find(String vocabulary) {
        try {
            return new FindAsyncTask(engToMmDao).execute(vocabulary).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InsertAsyncTask extends AsyncTask<EngToMmEntity, Void, Void> {

        private EngToMmDao mAsyncTaskDao;

        InsertAsyncTask(EngToMmDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(final EngToMmEntity... params) {
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

    private static class FindAsyncTask extends AsyncTask<String, Void, LiveData<List<EngToMmEntity>>> {

        private EngToMmDao mAsyncTaskDao;

        FindAsyncTask(EngToMmDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected LiveData<List<EngToMmEntity>> doInBackground(String... vocabulary) {
            return mAsyncTaskDao.find(vocabulary[0]);
        }
    }

}
