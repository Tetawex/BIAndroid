package net.styleru.biandroid.view.adapter.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.styleru.biandroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MutableRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    protected Context context;
    protected LayoutInflater inflater;
    private List<T> data= Collections.emptyList();

    public MutableRecyclerAdapter(Context context, List<T> data)
    {
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public void setDataWithNotify(List<T> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    public void appendDataWithNotify(List<T> data)
    {
        Log.d(data+"",data.size()+"");
        if(this.data.equals(Collections.emptyList()))
        {
            setDataWithNotify(data);
            return;
        }
        ((ArrayList)(this.data)).addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount()
    {
        return data.size();
    }

    protected void loadImageIntoView(String imageUrl,ImageView view)
    {
        Glide.with(context).load(imageUrl)
                .placeholder(R.drawable.ic_logo_fail)
                .error(R.drawable.ic_logo_fail)
                .into(view);
    }
}
