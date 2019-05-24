package com.amd.mmd.myapplication;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application{
    private static MyApi myApi;
    private static Retrofit retrofit;
    final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    @Override
    public void onCreate() {
        super.onCreate();
        createMyRetrofitInstance();
    }

    private void createMyRetrofitInstance(){
       retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi myApi = retrofit.create(MyApi.class);
    }

    public static MyApi getMyApi(){
        return myApi;
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
