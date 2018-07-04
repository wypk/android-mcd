package es.shwebill.component.retrofit;

import java.io.IOException;

import es.shwebill.component.android.UserInfoStorage;
import es.shwebill.component.util.Logger;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitService<T> {

    private Class<T> serviceClass;
    private T service;

    public RetrofitService(Class<T> serviceClass, final String baseUrl) {

        final UserInfoStorage userInfoStorage = UserInfoStorage.getInstance();

        this.serviceClass = serviceClass;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder requestBuilder = null;

                if (userInfoStorage.isCredentialsAvailable()) {

                    final String userId = userInfoStorage.getUserId();
                    final String token = userInfoStorage.getToken();

                    requestBuilder = original.newBuilder()
                            .header("Authorization", token)
                            .header("X-USER-ID", userId);
                } else {
                    requestBuilder = original.newBuilder();
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new NullOrEmptyConverterFactory())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        Logger.d(this.getClass(), "RetrofitService - init for : " + serviceClass.getName());
        this.service = retrofit.create(this.serviceClass);
        Logger.d(this.getClass(), "RetrofitService - built for : " + serviceClass.getName());
    }

    public T getService() {

        return this.service;
    }
}
