package net.styleru.biandroid.presenter;

import android.util.Log;

import net.styleru.biandroid.model.dao.CommunityModel;
import net.styleru.biandroid.model.dao.ScrollFeedModel;
import net.styleru.biandroid.view.IScrollFeedView;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by tetawex on 03.11.16.
 */
public class CommunityPresenter extends ScrollFeedPresenter
{
    private CommunityModel model = new CommunityModel();

    public CommunityPresenter(IScrollFeedView view)
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
                        throwable -> {
                            if(!subscription.isUnsubscribed()) {
                            subscription.unsubscribe();
                            }
                            Log.e("presenter",throwable.getMessage());
                        },
                        () -> {
                            if(!subscription.isUnsubscribed()) {
                                subscription.unsubscribe();
                            }
                        });

    }
}
