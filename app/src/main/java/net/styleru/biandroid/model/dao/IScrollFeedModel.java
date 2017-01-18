package net.styleru.biandroid.model.dao;

import net.styleru.biandroid.model.dto.DataNews;

import java.util.List;

import rx.Observable;

/**
 * Created by tetawex on 03.11.16.
 */

public interface IScrollFeedModel<T>
{
    Observable<List<T>> fetchAppendData(int id);
    Observable<List<T>> fetchUpdateData();
}
