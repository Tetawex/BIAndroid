package net.styleru.biandroid.model.api;

import net.styleru.biandroid.model.dto.DataCommunity;
import net.styleru.biandroid.model.dto.DataNews;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tetawex on 03.11.16.
 */

public interface NetworkAPI
{
    @GET("/?ng7cxy11qd")
    Observable<List<DataNews>> getNews();

    @GET("/?d7cq5dsob4")
    Observable<List<DataCommunity>> getCommunity();
}
