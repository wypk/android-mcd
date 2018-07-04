package es.shwebill.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;
import es.shwebill.persistence.repository.TopUpTransactionsHistoryRepository;

public class TopUpTransactionsHistoryViewModel extends AndroidViewModel {

    private TopUpTransactionsHistoryRepository topUpTransactionsHistoryRepository;

    public TopUpTransactionsHistoryViewModel(@NonNull Application application) {
        super(application);
        topUpTransactionsHistoryRepository = new TopUpTransactionsHistoryRepository(application);
    }

    public LiveData<List<TopUpTransactionsHistoryEntity>> getAllTopUpTransactionsHistory() {
        return topUpTransactionsHistoryRepository.getTopUpTransactionsHistoryList();
    }

    public void saveTopUpTransactionsHistory(TopUpTransactionsHistoryEntity topUpTransactionsHistoryEntity) {
        topUpTransactionsHistoryRepository.saveTopUpTransactionsHistory(topUpTransactionsHistoryEntity);
    }

    public LiveData<List<TopUpTransactionsHistoryEntity>> getFilterWithOperatorType(Operators operators) {
        return topUpTransactionsHistoryRepository.getFilterWithOperatorType(operators);
    }
}
