package es.shwebill.persistence.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import es.shwebill.persistence.ShweBillRoomDatabase;
import es.shwebill.persistence.dao.TransferTransactionsHistoryDao;
import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;

public class TransferTransactionsHistoryRepository {

    private TransferTransactionsHistoryDao transferTransactionsHistoryDao;
    private LiveData<List<TransferTransactionsHistoryEntity>> transferTransactionsHistoryList;

    public TransferTransactionsHistoryRepository(Application application) {
        ShweBillRoomDatabase shweBillRoomDatabase = ShweBillRoomDatabase.getDatabase(application);
        transferTransactionsHistoryDao = shweBillRoomDatabase.transferTransactionsHistoryDao();
        transferTransactionsHistoryList = transferTransactionsHistoryDao.getAllTransferTransactionsHistory();
    }

    public LiveData<List<TransferTransactionsHistoryEntity>> getTransferTransactionsHistoryList() {
        return transferTransactionsHistoryList;
    }

    public void saveTransferTransactionsHistory(TransferTransactionsHistoryEntity transferTransactionsHistoryEntity) {
        new InsertAsyncTask(transferTransactionsHistoryDao).execute(transferTransactionsHistoryEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<TransferTransactionsHistoryEntity, Void, Void> {

        private TransferTransactionsHistoryDao transferTransactionsHistoryDao;

        public InsertAsyncTask(TransferTransactionsHistoryDao transferTransactionsHistoryDao) {
            this.transferTransactionsHistoryDao = transferTransactionsHistoryDao;
        }

        @Override
        protected Void doInBackground(TransferTransactionsHistoryEntity... transferTransactionsHistoryEntities) {
            transferTransactionsHistoryDao.saveTransferTransactionsHistory(transferTransactionsHistoryEntities[0]);
            return null;
        }
    }
}
