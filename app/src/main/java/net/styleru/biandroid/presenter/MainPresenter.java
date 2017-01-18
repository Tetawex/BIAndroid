package net.styleru.biandroid.presenter;

import net.styleru.biandroid.view.IMainView;

/**
 * Created by tetawex on 15.11.16.
 */

public class MainPresenter implements IMainPresenter
{
    private IMainView view;
    public MainPresenter(IMainView view)
    {
        this.view=view;
    }
    @Override
    public void onNavbarItemSelected(int id)
    {
        view.switchFragment(id);
    }
    @Override
    public void onViewCreated()
    {

    }
}
