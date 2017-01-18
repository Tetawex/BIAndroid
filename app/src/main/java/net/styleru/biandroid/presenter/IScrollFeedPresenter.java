package net.styleru.biandroid.presenter;

/**
 * Created by tetawex on 03.11.16.
 */
public interface IScrollFeedPresenter
{
    public void onDestroyView();
    public void onFeedUpdated();
    public void onFeedAppended();
    public void onRequestFeedUpdate();
    public void onRequestFeedAppend();
}
