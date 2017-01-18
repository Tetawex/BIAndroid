package net.styleru.biandroid.presenter;

import net.styleru.biandroid.view.ILoginView;

/**
 * Created by tetawex on 15.11.16.
 */

public class LoginPresenter implements ILoginPresenter
{
    private ILoginView view;
    public LoginPresenter(ILoginView view)
    {
        this.view=view;
    }
    @Override
    public void onLoginRequested(String login, String password)
    {
        onLoginSucceed();
    }

    @Override
    public void onLoginSucceed()
    {
        view.switchActivity();
    }

    @Override
    public void onLoginFailed(String errorMsg)
    {
        view.errorLog("Fail...");
    }
}
