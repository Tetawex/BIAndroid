package net.styleru.biandroid.presenter;

import okhttp3.Credentials;

/**
 * Created by tetawex on 15.11.16.
 */

public interface ILoginPresenter
{
    void onLoginRequested(String login, String password);
    void onLoginSucceed();
    void onLoginFailed(String errorMsg);
}
