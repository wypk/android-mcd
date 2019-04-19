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

package wyp.mcd.component.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Wai Yan on 3/17/19.
 */
public class WebViewUtil {

    public static void show(Context context, String url) {

        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
