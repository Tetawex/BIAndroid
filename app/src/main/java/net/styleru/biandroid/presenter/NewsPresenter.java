package net.styleru.biandroid.presenter;

import android.util.Log;

import net.styleru.biandroid.model.dao.NewsModel;
import net.styleru.biandroid.model.dao.ScrollFeedModel;
import net.styleru.biandroid.view.IScrollFeedView;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by tetawex on 03.11.16.
 */
public class NewsPresenter extends ScrollFeedPresenter
{
    private NewsModel model = new NewsModel();

    public NewsPresenter(IScrollFeedView view)
    {
        super(view);
    }

    @Override
    public void onDestroyView()
    {

    }

    @Override
    public void onFeedUpdated() {

    }

    @Override
    public void onFeedAppended() {

    }

    @Override
    public void onRequestFeedUpdate()
    {
        if(!subscription.isUnsubscribed())
            subscription.unsubscribe();
        subscription = model.fetchUpdateData()
                //.flatMap(Observable::from)
                //.toList()
                .subscribe(list -> view.setData(list),
                        throwable -> {
                            view.onDataUpdated();
                            Log.e("presenter", throwable.getMessage());

                        },
                        () -> {
                            view.onDataUpdated();
                            if(!subscription.isUnsubscribed()) {
                                subscription.unsubscribe();
                            }
                        });

    }

    @Override
    public void onRequestFeedAppend()
    {
        subscription = model.fetchAppendData(0)
               // .flatMap(Observable::from)
                //.toList()
                .subscribe(list -> view.appendData(list),
                        throwable -> Log.e("presenter",throwable.getMessage()),
                        () -> {
                            if(!subscription.isUnsubscribed()) {
                                subscription.unsubscribe();
                            }
                        });

    }
}
