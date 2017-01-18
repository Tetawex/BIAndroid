package net.styleru.biandroid.view.tab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.styleru.biandroid.R;
import net.styleru.biandroid.view.adapter.recycler.SCBIRecyclerAdapter;
import net.styleru.biandroid.model.dto.DataJSONSCBI;
import net.styleru.biandroid.model.dto.SupportDataSCBINode;
import net.styleru.biandroid.model.network.AsyncFetch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hp1 on 21-01-2015.
 */
public class SCBITab extends Fragment
{
        private RecyclerView recyclerView;
        private SCBIRecyclerAdapter mAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            super.onCreateView(inflater,container,savedInstanceState);
            // Inflate the layout for this fragment
            View view=inflater.inflate(R.layout.fragment_default, container, false);

            fetch();

            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mAdapter = new SCBIRecyclerAdapter(getContext(), Collections.<DataJSONSCBI>emptyList());
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            return view;
        }
        public void fetch()
        {
            new SCBIAsyncFetch(getContext(),getString(R.string.loading),
                    getString(R.string.network_fail),
                    getString(R.string.url_scbi)).execute();
        }
        public static Fragment newInstance()
        {
            SCBITab fragment = new SCBITab();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        private class SCBIAsyncFetch extends AsyncFetch
        {
            ProgressDialog pdLoading = new ProgressDialog(getContext());
            HttpURLConnection conn;
            URL url = null;

            public SCBIAsyncFetch(Context context, String loadingMsg, String failMsg, String urlPath)
            {
                super(context, loadingMsg, failMsg, urlPath);
            }

            @Override
            protected void onPostExecute(String result)
            {

                //this method will be running on UI thread
                super.onPostExecute(result);

                List<DataJSONSCBI> data=new ArrayList<>();

                try
                {
                    JSONArray jArray = new JSONArray(result);

                    // Extract data from json and store into ArrayList as class objects
                    for(int i=0;i<jArray.length();i++)
                    {
                        JSONObject jObject = jArray.getJSONObject(i);
                        DataJSONSCBI dataSCBI = new DataJSONSCBI();
                        dataSCBI.title= jObject.getString("title");
                        JSONArray studiesArray = jObject.getJSONArray("studies");
                        SupportDataSCBINode[] nodeArray=new SupportDataSCBINode[studiesArray.length()];
                        for(int j=0;j<studiesArray.length();j++)
                        {
                            JSONObject jItem = studiesArray.getJSONObject(j);
                            SupportDataSCBINode node=new SupportDataSCBINode();
                            node.dayOfWeek=jItem.getString("dayOfWeek");
                            node.timeStart=jItem.getString("timeStart");
                            node.timeEnd=jItem.getString("timeEnd");
                            node.mentorName=jItem.getString("mentorName");
                            node.roomNumber=jItem.getInt("roomNumber");
                            nodeArray[j]=node;
                        }
                        dataSCBI.studies=nodeArray;
                        data.add(dataSCBI);
                    }
                    mAdapter.setData(data);
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e)
                {
                    Toast.makeText(getContext(), getString(R.string.network_fail), Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        }
}