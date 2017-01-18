package net.styleru.biandroid.model.dao;

import net.styleru.biandroid.model.dto.DataCommunity;
import net.styleru.biandroid.model.dto.DataNews;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tetawex on 04.11.16.
 */

public class CommunityModel extends ScrollFeedModel<DataCommunity>
{
    @Override
    public Observable<List<DataCommunity>> fetch(int id)
    {
        Observable<List<DataCommunity>> observable =service.getAPI().getCommunity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
        }

}
