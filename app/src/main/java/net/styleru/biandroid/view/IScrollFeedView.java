package net.styleru.biandroid.view;

import net.styleru.biandroid.model.dto.DataNews;

import java.util.List;

/**
 * Created by tetawex on 03.11.16.
 */

public interface IScrollFeedView<T> extends IView
{
    void appendData(List<T> data);
    void setData(List<T> data);
    void onDataUpdated();
}
