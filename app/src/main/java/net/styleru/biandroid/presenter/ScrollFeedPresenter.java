package net.styleru.biandroid.presenter;

import android.util.Log;

import net.styleru.biandroid.model.dao.ScrollFeedModel;
import net.styleru.biandroid.view.IScrollFeedView;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by tetawex on 03.11.16.
 */
public class ScrollFeedPresenter<T extends ScrollFeedModel> implements IScrollFeedPresenter
{
    protected IScrollFeedView view;
    protected Subscription subscription = Subscriptions.empty();

    public ScrollFeedPresenter(IScrollFeedView view)
    {
        this.view=view;
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onFeedUpdated() {

    }

    @Override
    public void onFeedAppended() {

    }

    @Override
    public void onRequestFeedUpdate() {

    }

    @Override
    public void onRequestFeedAppend() {

    }
}
