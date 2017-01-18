package net.styleru.biandroid.view.adapter.recycler;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import net.styleru.biandroid.R;
import net.styleru.biandroid.view.activity.GalleryActivity;
import net.styleru.biandroid.view.activity.MainActivity;
import net.styleru.biandroid.model.dto.DataJSONNews;
import net.styleru.biandroid.util.DateTimeConverter;
import net.styleru.biandroid.model.dto.SupportDataSocialNetworkReference;

import java.util.ArrayList;
import java.util.List;

public class EventsRecyclerAdapter extends MutableRecyclerAdapter<DataJSONNews>
{

    // create constructor to initialize context and data sent from MainActivity
    public EventsRecyclerAdapter(Context context, List<DataJSONNews> data)
    {
        super(context, data);
    }
    // Inflate the layout when viewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.card_news, parent,false);
        NewsViewHolder holder=new NewsViewHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        // Get current position of item in recyclerview to bind data and assign values from list
        final NewsViewHolder eventsHolder= (NewsViewHolder) holder;
        DataJSONNews current=getData().get(position);
        eventsHolder.title.setText(current.title);
        eventsHolder.content.setText(current.content);
        eventsHolder.description.setText(new DateTimeConverter()
                .toHumanLanguage(current.dateTime,context));
        eventsHolder.setImages(current.images);
        eventsHolder.setReferences(current.references);

        loadImageIntoView(current.logoUrl,eventsHolder.logo);
        if(current.images.length!=0)
        {
            loadImageIntoView(current.images[0], eventsHolder.cover);
            eventsHolder.cover.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    startGalleryActivity(eventsHolder.images);
                }
            });
        }
    }

    public void startGalleryActivity(String[] urls)
    {
        ArrayList<String> images = new ArrayList<>();
        for(String s : urls)
        {
            images.add(s);
        }
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putStringArrayListExtra(GalleryActivity.EXTRA_NAME, images);
        context.startActivity(intent);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        TextView description;
        TextView content;
        ImageView logo;
        ImageView cover;
        ImageButton moreInfoButton;
        String[] images;
        String[] references;

        PopupMenu socNetReferences;

        public NewsViewHolder(final View itemView)
        {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            content= (TextView) itemView.findViewById(R.id.description);
            description= (TextView) itemView.findViewById(R.id.dateTime);
            moreInfoButton= (ImageButton) itemView.findViewById(R.id.moreInfoButton);
            socNetReferences=new PopupMenu(itemView.getContext(),moreInfoButton);
            logo= (ImageView) itemView.findViewById(R.id.logo);
            cover =(ImageView) itemView.findViewById(R.id.cover);
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    socNetReferences.dismiss();
                    socNetReferences.show();
                }
            });
            socNetReferences.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    ((MainActivity)(itemView.getContext())).invokeUrlIntent(references[item.getItemId()]);
                    return false;
                }
            });

        }
        public void setImages(String[] images)
        {
            this.images=images;//Should not really do it like that
            ((TextView) itemView.findViewById(R.id.imageCount)).setText(String.valueOf(images.length));
            if(images.length<2)
            {
                itemView.findViewById(R.id.imageCounter).setVisibility(View.GONE);
                if(images.length<1)
                    itemView.findViewById(R.id.frame).setVisibility(View.GONE);
            }
        }
        public void setReferences(SupportDataSocialNetworkReference[] sc)
        {
            references=new String[sc.length];
            for(int i=0;i<sc.length;i++)
            {
                socNetReferences.getMenu().add(0,i,0,sc[i].title);
                references[i]=sc[i].reference;
            }
        }
    }

}
