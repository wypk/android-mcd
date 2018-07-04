package es.shwebill.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;
import es.shwebill.persistence.repository.TransferTransactionsHistoryRepository;

public class TransferTransactionsHistoryViewModel extends AndroidViewModel {

    private TransferTransactionsHistoryRepository transferTransactionsHistoryRepository;

    public TransferTransactionsHistoryViewModel(@NonNull Application application) {
        super(application);
        transferTransactionsHistoryRepository = new TransferTransactionsHistoryRepository(application);
    }

    public LiveData<List<TransferTransactionsHistoryEntity>> getAllTransferTransactionsHistory() {
        return transferTransactionsHistoryRepository.getTransferTransactionsHistoryList();
    }

    public void saveTransferTransactionsHistory(TransferTransactionsHistoryEntity transferTransactionsHistoryEntity) {
        transferTransactionsHistoryRepository.saveTransferTransactionsHistory(transferTransactionsHistoryEntity);
    }
}
