package es.shwebill.component.retrofit;

import android.content.Context;

import es.shwebill.component.util.Logger;

public class ApiExceptionTranslator {

    public static String getMessageFromException(Context context, RestException ex) {

        final String CODE = ex.errorCode;

        Logger.d(ApiExceptionTranslator.class, "ApiExceptionTranslator : code : " + CODE);

        int id = context.getResources().getIdentifier(CODE, "string", context.getPackageName());

        if (id == 0) {
            Logger.d(ApiExceptionTranslator.class, "ApiExceptionTranslator : cannot find CODE.");
            return null;
        }

        return context.getResources().getString(id);
    }
}
