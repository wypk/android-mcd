package es.shwebill.persistence.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.ShweBillRoomDatabase;
import es.shwebill.persistence.dao.TopUpTransactionsHistoryDao;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;

public class TopUpTransactionsHistoryRepository {

    private TopUpTransactionsHistoryDao topUpTransactionsHistoryDao;
    private LiveData<List<TopUpTransactionsHistoryEntity>> topUpTransactionsHistoryList;

    public TopUpTransactionsHistoryRepository(Application application) {
        ShweBillRoomDatabase shweBillRoomDatabase = ShweBillRoomDatabase.getDatabase(application);
        topUpTransactionsHistoryDao = shweBillRoomDatabase.topUpTransactionsHistoryDao();
        topUpTransactionsHistoryList = topUpTransactionsHistoryDao.getAllTransferTransactionsHistory();
    }

    public LiveData<List<TopUpTransactionsHistoryEntity>> getTopUpTransactionsHistoryList() {
        return topUpTransactionsHistoryList;
    }

    public void saveTopUpTransactionsHistory(TopUpTransactionsHistoryEntity topUpTransactionsHistoryEntity) {
        new InsertAsyncTask(topUpTransactionsHistoryDao).execute(topUpTransactionsHistoryEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<TopUpTransactionsHistoryEntity, Void, Void> {

        private TopUpTransactionsHistoryDao topUpTransactionsHistoryDao;

        InsertAsyncTask(TopUpTransactionsHistoryDao dao) {
            this.topUpTransactionsHistoryDao = dao;
        }

        @Override
        protected Void doInBackground(TopUpTransactionsHistoryEntity... params) {
            topUpTransactionsHistoryDao.saveTransferTransactionsHistory(params[0]);
            return null;
        }
    }

    public LiveData<List<TopUpTransactionsHistoryEntity>> getFilterWithOperatorType(Operators operators) {
        return topUpTransactionsHistoryDao.getFilterWithOperatorType(operators);
    }
}
