package net.styleru.biandroid.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.styleru.biandroid.R;
import net.styleru.biandroid.view.tab.SlidingTabLayout;
import net.styleru.biandroid.view.adapter.tab.TimetableAdapter;

/**
 * Created by Tetawex on 20.08.2016.
 */
public class TimetableFragment extends TabbedFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        titles[0]=getText(R.string.tab_events);
        titles[1]=getText(R.string.tab_scbi);

    }

    public static Fragment newInstance()
    {
        TimetableFragment fragment = new TimetableFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View addTabsToView(View view)
    {
        adapter =  new TimetableAdapter(getFragmentManager(),titles, tabsNumber);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorTabsScroll);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout

        tabs.setViewPager(pager);
        return view;
    }
}
