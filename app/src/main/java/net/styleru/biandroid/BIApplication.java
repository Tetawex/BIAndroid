package net.styleru.biandroid;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;
import net.styleru.biandroid.model.service.NetworkService;

/**
 * Created by Tetawex on 22.08.2016.
 */
public class BIApplication extends Application
{
    public int chosenFragment=0;
    private NetworkService networkService;
    @Override
    public void onCreate()
    {
        super.onCreate();
        JodaTimeAndroid.init(this);
        networkService = new NetworkService();
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
