package net.styleru.biandroid.view.adapter.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.styleru.biandroid.view.tab.EventsTab;
import net.styleru.biandroid.view.tab.SCBITab;


/**
 * Created by Tetawex on 20.08.2016.
 */
public class TimetableAdapter extends ViewPagerAdapter
{

    public TimetableAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);
    }
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            return new EventsTab();
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {

            return new SCBITab();
        }


    }
}
