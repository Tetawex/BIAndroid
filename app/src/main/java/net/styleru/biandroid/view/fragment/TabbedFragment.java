package net.styleru.biandroid.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.styleru.biandroid.R;
import net.styleru.biandroid.view.tab.SlidingTabLayout;
import net.styleru.biandroid.view.adapter.tab.ViewPagerAdapter;

public abstract class TabbedFragment extends Fragment
{

    protected ViewPager pager;
    protected ViewPagerAdapter adapter;
    protected SlidingTabLayout tabs;
    protected CharSequence[] titles;
    protected int tabsNumber =2;
    private OnFragmentInteractionListener mListener;

    public TabbedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
        titles=new CharSequence[tabsNumber];
        titles[0]=getText(R.string.tab_events);
        titles[1]=getText(R.string.tab_scbi);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tabbed, container, false);
        return addTabsToView(view);
    }
    protected abstract View addTabsToView(View view);

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);



        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
        pager=null;
        tabs=null;
        titles=null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
