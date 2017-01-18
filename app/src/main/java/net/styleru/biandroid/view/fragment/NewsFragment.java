package net.styleru.biandroid.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.styleru.biandroid.R;
import net.styleru.biandroid.presenter.NewsPresenter;
import net.styleru.biandroid.view.IScrollFeedView;
import net.styleru.biandroid.view.adapter.recycler.EndlessRecyclerViewScrollListener;
import net.styleru.biandroid.view.adapter.recycler.NewsRecyclerAdapter;
import net.styleru.biandroid.model.dto.DataNews;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tetawex on 22.08.2016.
 */
public class NewsFragment extends Fragment implements IScrollFeedView<DataNews>
{
    private RecyclerView recyclerView;
    private NewsRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;


    private static NewsPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_default, container, false);

        adapter=new NewsRecyclerAdapter(getContext(), Collections.emptyList());
        NewsFragment.presenter =new NewsPresenter(this);
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

        recyclerViewScrollListener=new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view)
            {
                NewsFragment.presenter.onRequestFeedAppend();
            }
        };

        recyclerView.addOnScrollListener(recyclerViewScrollListener);
        swipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorTabsScroll,
                R.color.colorTextNavBar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                NewsFragment.presenter.onRequestFeedUpdate();

            }
        });
        NewsFragment.presenter.onRequestFeedUpdate();
        return view;

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(recyclerView!=null&&recyclerView.getLayoutManager()!=null)
            outState.putParcelable("RCVState",
                recyclerView.getLayoutManager().onSaveInstanceState());
    }

    public static Fragment newInstance()
    {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void appendData(List<DataNews> data)
    {
        adapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<DataNews> data)
    {
        adapter.setDataWithNotify(data);
    }
    @Override
    public void onDataUpdated()
    {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }
}
