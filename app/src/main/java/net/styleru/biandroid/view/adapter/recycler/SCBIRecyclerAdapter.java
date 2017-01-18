package net.styleru.biandroid.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.styleru.biandroid.R;
import net.styleru.biandroid.model.dto.DataJSONSCBI;

import java.util.List;

/**
 * Created by Tetawex on 28.09.2016.
 */

public class SCBIRecyclerAdapter extends MutableRecyclerAdapter<DataJSONSCBI>
{
    public SCBIRecyclerAdapter(Context context, List<DataJSONSCBI> data)
    {
        super(context,data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.card_scbi, parent,false);
        SCBIRecyclerAdapter.SCBIViewHolder holder=new SCBIRecyclerAdapter.SCBIViewHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        SCBIRecyclerAdapter.SCBIViewHolder scbiHolder = (SCBIRecyclerAdapter.SCBIViewHolder) holder;
        DataJSONSCBI current = getData().get(position);
        scbiHolder.initStudiesView(current.studies.length);
        scbiHolder.title.setText(current.title);
        for(int i=0;i<scbiHolder.studies.length;i++)
        {
            View v=scbiHolder.studies[i];
            Log.println(Log.ERROR,"cock"," cock"+(v==null));
            ((TextView)(v.findViewById(R.id.dayOfWeek))).setText(current.studies[i].dayOfWeek);
            ((TextView)v.findViewById(R.id.timeStart)).setText(current.studies[i].timeStart);
            ((TextView)v.findViewById(R.id.timeEnd)).setText(current.studies[i].timeEnd);
            ((TextView)v.findViewById(R.id.mentorName)).setText(current.studies[i].mentorName);
            TextView txt=(TextView)v.findViewById(R.id.roomNumber);
            txt.setText(txt.getContext().getString(R.string.room)+" "+current.studies[i].roomNumber);
        }
    }


    class SCBIViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        View[] studies;
        LinearLayout layout;

        public SCBIViewHolder(View itemView)
        {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            layout = (LinearLayout) itemView.findViewById(R.id.studiesList);

        }
        public void initStudiesView(int length)
        {
            studies=new View[length];
            View divider=inflater.inflate(R.layout.view_divider,layout,false);
            for (int i=0;i<studies.length;i++)
            {
                studies[i]=inflater.inflate(R.layout.view_scbiitem,layout,false);
                layout.addView(studies[i]);
                if(i<studies.length-1)
                    layout.addView(divider);
            }
        }

    }
}
