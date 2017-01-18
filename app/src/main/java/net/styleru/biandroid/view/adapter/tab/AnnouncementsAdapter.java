package net.styleru.biandroid.view.adapter.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.styleru.biandroid.view.tab.AnnouncementsTab;


/**
 * Created by Tetawex on 20.08.2016.
 */
public class AnnouncementsAdapter extends ViewPagerAdapter
{

    public AnnouncementsAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);
    }
    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            return AnnouncementsTab.newInstance(true);
        }
        else
        {
            return AnnouncementsTab.newInstance(false);
        }


    }
}
