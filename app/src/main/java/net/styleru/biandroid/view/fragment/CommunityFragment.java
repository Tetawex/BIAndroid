package net.styleru.biandroid.view.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.styleru.biandroid.model.dto.DataCommunity;
import net.styleru.biandroid.presenter.CommunityPresenter;
import net.styleru.biandroid.presenter.ScrollFeedPresenter;
import net.styleru.biandroid.view.IScrollFeedView;
import net.styleru.biandroid.view.adapter.recycler.CommunityRecyclerAdapter;
import net.styleru.biandroid.model.dto.DataJSONCommunity;
import net.styleru.biandroid.R;
import net.styleru.biandroid.model.network.AsyncFetch;
import net.styleru.biandroid.view.adapter.recycler.EndlessRecyclerViewScrollListener;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tetawex on 22.08.2016.
 */
public class CommunityFragment extends Fragment implements IScrollFeedView<DataCommunity>
{
    private static ScrollFeedPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommunityRecyclerAdapter adapter;
    private EndlessRecyclerViewScrollListener recyclerListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_default, container, false);

        adapter=new CommunityRecyclerAdapter(getContext(), Collections.emptyList());
        presenter =new CommunityPresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        if(savedInstanceState!=null)
        {
            if (savedInstanceState.getParcelable("RCVState") == null)
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            else {
                LinearLayoutManager lm = new LinearLayoutManager(getContext());
                lm.onRestoreInstanceState(savedInstanceState.getParcelable("RCVState"));
                recyclerView.setLayoutManager(lm);
            }
        }
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerListener=new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view)
            {
                presenter.onRequestFeedAppend();
            }
        };

        recyclerView.addOnScrollListener(recyclerListener);
        swipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorTabsScroll,
                R.color.colorTextNavBar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onRequestFeedUpdate();
            }
        });
        presenter.onRequestFeedUpdate();
        return view;

    }
    public static Fragment newInstance()
    {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void appendData(List<DataCommunity> data)
    {
        adapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<DataCommunity> data)
    {
        adapter.setDataWithNotify(data);
    }

    @Override
    public void onDataUpdated()
    {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.removeOnScrollListener(recyclerListener);
        recyclerListener=new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view)
            {
                presenter.onRequestFeedAppend();
            }
        };
        recyclerView.addOnScrollListener(recyclerListener);
    }

}

