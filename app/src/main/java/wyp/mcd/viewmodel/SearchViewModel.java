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

package wyp.mcd.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import wyp.mcd.infrastructure.entities.EngToEngEntity;
import wyp.mcd.infrastructure.entities.EngToMmEntity;
import wyp.mcd.infrastructure.repository.EngToEngRepository;
import wyp.mcd.infrastructure.repository.EngToMmRepository;

public class SearchViewModel extends AndroidViewModel {

    private EngToMmRepository engToMmRepository;
    private EngToEngRepository engToEngRepository;

    public SearchViewModel(Application application) {
        super(application);
        engToMmRepository = new EngToMmRepository(application);
        engToEngRepository = new EngToEngRepository(application);
    }

    public LiveData<List<EngToMmEntity>> findEngToMm(String vocabulary) {
        return engToMmRepository.find(vocabulary);
    }

    public LiveData<List<EngToEngEntity>> findEngToEng(String vocabulary) {
        return engToEngRepository.find(vocabulary);
    }
}
