package com.ziyata.absen.data.remote;

import com.ziyata.absen.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // set log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Membuat object http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Menambahkan logging interceptor ke dalam httpClient
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;
    }
}
