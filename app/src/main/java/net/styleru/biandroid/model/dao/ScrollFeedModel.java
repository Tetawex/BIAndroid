package net.styleru.biandroid.model.dao;

import net.styleru.biandroid.model.dto.DataNews;
import net.styleru.biandroid.model.service.NetworkService;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tetawex on 03.11.16.
 */

public abstract class ScrollFeedModel<T> implements IScrollFeedModel<T>
{
    protected NetworkService service;
    public ScrollFeedModel()
    {
        service=new NetworkService();
    }
    public Observable<List<T>> fetchAppendData(int id)
    {
        return fetch(id);
    }
    public Observable<List<T>> fetchUpdateData()
    {
        return fetch(0);
    }
    public abstract Observable<List<T>> fetch(final int id);
}
