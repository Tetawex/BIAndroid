package net.styleru.biandroid.view.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.styleru.biandroid.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class MentorsTab extends ScrollTab
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_mentors,container,false);
        return v;
    }
}
