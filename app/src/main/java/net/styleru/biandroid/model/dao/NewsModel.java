package net.styleru.biandroid.model.dao;

import net.styleru.biandroid.model.dto.DataNews;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tetawex on 04.11.16.
 */

public class NewsModel extends ScrollFeedModel<DataNews>
{
    @Override
    public Observable<List<DataNews>> fetch(int id)
    {
        Observable<List<DataNews>> observable =service.getAPI().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
