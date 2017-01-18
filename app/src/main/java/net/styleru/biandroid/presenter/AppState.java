package net.styleru.biandroid.presenter;

/**
 * Created by tetawex on 29.10.16.
 */

public class AppState
{
    private static AppState appState;
    public static AppState getInstance()
    {
        if(appState==null)
            appState=new AppState();
        return appState;
    }

    public int getChosenFragment() {
        return chosenFragment;
    }

    public void setChosenFragment(int chosenFragment) {
        this.chosenFragment = chosenFragment;
    }

    public boolean isNavBarOpened() {
        return navBarOpened;
    }

    public void setNavBarOpened(boolean navBarOpened) {
        this.navBarOpened = navBarOpened;
    }

    private boolean navBarOpened;
    private int chosenFragment;

    public AppState(boolean navBarOpened,int chosenFragment)
    {
        this.navBarOpened=navBarOpened;
        this.chosenFragment=chosenFragment;
    }
    public AppState()
    {
        this(false,0);
    }
}
