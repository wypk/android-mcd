package es.shwebill.component.retrofit;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import es.shwebill.BuildConfig;
import es.shwebill.component.android.AndroidUtil;
import es.shwebill.component.android.NoInternetConnectionException;
import es.shwebill.component.util.Logger;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public abstract class RestRx<SERVICE, REQUEST, RESPONSE> {

    private SERVICE service;

    public RestRx(SERVICE service) {

        this.service = service;
    }

    public Observable<Response<RESPONSE>> invoke(Context activity, final REQUEST request) {

        return Observable.create((ObservableOnSubscribe<Response<RESPONSE>>) emitter -> {

            try {

                if (!AndroidUtil.isInternetAvailable(activity)) {
                    throw new NoInternetConnectionException();
                }

                Call<RESPONSE> call = RestRx.this.call(request);

                Response<RESPONSE> response = call.execute();

                Logger.d(this.getClass(), "RestRx - call.executed : " + call.isExecuted());
                if (response != null && !response.isSuccessful()) {
                    switch (response.code()) {
                        case 400:
                            Logger.d(this.getClass(),
                                    "RestRx - call : Error occurred at rest API call...");
                            RestErrorResponse restErrorResponse = new RestErrorResponse();

                            if (response.errorBody() != null) {
                                String json = response.errorBody().string();
                                JSONObject jsonObject = new JSONObject(json);
                                restErrorResponse.setErrorType(jsonObject.getString("errorType"));
                                restErrorResponse.setErrorCode(jsonObject.getString("errorCode"));
                                restErrorResponse
                                        .setErrorMessage(jsonObject.getString("errorMessage"));
                            }

                            Logger.d(this.getClass(), "RestRx - call : errorType : " +
                                    restErrorResponse.getErrorType());
                            Logger.d(this.getClass(), "RestRx - call : errorCode : " +
                                    restErrorResponse.getErrorCode());
                            Logger.d(this.getClass(), "RestRx - call : errorMsg : " +
                                    restErrorResponse.getErrorMessage());

                            throw new BadRequestException(
                                    restErrorResponse.getErrorType(),
                                    restErrorResponse.getErrorCode(),
                                    restErrorResponse.getErrorMessage());
                        case 401:
                        case 403:
                            Logger.d(
                                    this.getClass(),
                                    "RestRx - call : Error occurred at rest API authentication...");
                            throw new AuthenticationException();
                        case 404:
                            Logger.d(this.getClass(),
                                    "RestRx - call : Error occurred at rest API not found...");
                            throw new ResourceNotFoundException();
                        default:
                            throw new RestException("N.A", "N.A", "N.A");
                    }
                }

                emitter.onNext(response);
                emitter.onComplete();

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
                Logger.d(this.getClass(), "RestRx : error : " + Log.getStackTraceString(e));
                emitter.onError(e);
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    protected abstract Call<RESPONSE> call(REQUEST request);

    protected SERVICE getService() {

        return this.service;
    }
}
