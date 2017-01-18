package net.styleru.biandroid.view.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.styleru.biandroid.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ScrollTab extends Tab {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setRetainInstance(true);
        View v =inflater.inflate(R.layout.tab_events,container,false);
        return v;

    }
    public static Fragment newInstance(boolean showSecondary)
    {
        ScrollTab fragment = new ScrollTab();
        Bundle args = new Bundle();
        args.putBoolean("showSecondary", showSecondary);
        fragment.setArguments(args);
        return fragment;
    }
}