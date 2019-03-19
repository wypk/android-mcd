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

package wyp.mcd.component.android;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import wyp.mcd.infrastructure.entities.EngToEngEntity;
import wyp.mcd.infrastructure.entities.EngToMmEntity;

public class JsonParser {

    private final static String JSON_FILE_ENG_TO_MM = "data/eng_to_mm.json";
    private final static String JSON_FILE_ENG_TO_ENG = "data/eng_to_eng.json";
    private static Type engToMmList = new TypeToken<List<EngToMmEntity>>() {}.getType();
    private static Type engToEngList = new TypeToken<List<EngToEngEntity>>() {}.getType();
    private Context mContext;
    private List<EngToMmEntity> engToMmEntitiesList = new ArrayList<>();
    private List<EngToEngEntity> engToEngEntitiesList = new ArrayList<>();

    public JsonParser(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public List<EngToMmEntity> parseEngToMmJson() {
        Gson gson = new Gson();
        try {
            engToMmEntitiesList = gson.fromJson(new JsonReader(new InputStreamReader(mContext.getAssets().open(JsonParser.JSON_FILE_ENG_TO_MM))), JsonParser.engToMmList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return engToMmEntitiesList;
    }

    public List<EngToEngEntity> parseEngToEngJson() {
        Gson gson = new Gson();
        try {
            engToEngEntitiesList = gson.fromJson(new JsonReader(new InputStreamReader(mContext.getAssets().open(JsonParser.JSON_FILE_ENG_TO_ENG))), JsonParser.engToEngList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return engToEngEntitiesList;
    }
}
