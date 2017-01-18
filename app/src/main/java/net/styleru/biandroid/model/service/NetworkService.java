package net.styleru.biandroid.model.service;

import com.google.gson.GsonBuilder;

import net.styleru.biandroid.model.api.NetworkAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tetawex on 28.10.2016.
 */

public class NetworkService {

    private static String baseUrl = "https://1fichier.com";
    private NetworkAPI networkAPI;

    public NetworkService()
    {
        this(baseUrl);
    }

    public NetworkService(String baseUrl)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                        .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }

    public NetworkAPI getAPI()
    {
        return networkAPI;
    }

}