package com.boymustafa.experiment.network;

import com.boymustafa.experiment.Consant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static DeliverService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Consant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(DeliverService.class);

    }


}
