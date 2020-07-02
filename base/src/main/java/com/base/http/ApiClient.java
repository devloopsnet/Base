package com.base.http;

import com.base.utils.BaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class ApiClient {
    static final String BASE_URL = BaseApp.getBaseUrl();
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)//Optional .
                    .build();
        }
        return retrofit;
    }
}