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
import net.styleru.biandroid.util.DateTimeConverter;
import net.styleru.biandroid.model.dto.DataNews;
import net.styleru.biandroid.model.dto.SocNetUrl;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends MutableRecyclerAdapter<DataNews>
{

    // create constructor to initialize context and data sent from MainActivity
    public NewsRecyclerAdapter(Context context, List<DataNews> data)
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
        final NewsViewHolder newsHolder= (NewsViewHolder) holder;
        DataNews current=getData().get(position);
        newsHolder.title.setText(current.getTitle());
        newsHolder.description.setText(current.getDescription());
        newsHolder.dateTime.setText(new DateTimeConverter()
                .toHumanLanguage(current.getDateTime(),context));
        String[] urls=new String[current.getAttachmentsUrls().size()];
        for (int i = 0;i<current.getAttachmentsUrls().size();i++)
        {
            urls[i]=current.getAttachmentsUrls().get(i).getUrl();
        }
        newsHolder.setAttachments(urls);
        newsHolder.clearSocNetUrls();
        newsHolder.setSocNetUrls(current.getSocNetUrls());

        loadImageIntoView(current.getLogoUrl(),newsHolder.logo);
        if(current.getAttachmentsUrls().size()!=0)
        {
            loadImageIntoView(current.getAttachmentsUrls().get(0).getUrl(), newsHolder.cover);
            newsHolder.cover.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    startGalleryActivity(newsHolder.attachments,context);
                }
            });
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        NewsViewHolder newsHolder = (NewsViewHolder) holder;
        newsHolder.clearSocNetUrls();
    }

    public static void startGalleryActivity(String[] urls,Context context)
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
        TextView dateTime;
        TextView description;
        ImageView logo;
        ImageView cover;
        ImageButton moreInfoButton;
        String[] attachments;
        String[] socNetUrls;

        PopupMenu socNetUrlsMenu;

        public void clearSocNetUrls() {
            socNetUrlsMenu.getMenu().clear();
        }

        public NewsViewHolder(final View itemView)
        {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            dateTime = (TextView) itemView.findViewById(R.id.dateTime);
            moreInfoButton= (ImageButton) itemView.findViewById(R.id.moreInfoButton);
            socNetUrlsMenu =new PopupMenu(itemView.getContext(),moreInfoButton);
            logo= (ImageView) itemView.findViewById(R.id.logo);
            cover =(ImageView) itemView.findViewById(R.id.cover);
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    socNetUrlsMenu.dismiss();
                    socNetUrlsMenu.show();
                }
            });
            socNetUrlsMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item)
                {
                    ((MainActivity)(itemView.getContext())).invokeUrlIntent(socNetUrls[item.getItemId()]);
                    return false;
                }
            });

        }
        public void setAttachments(String[] attachments)
        {
            this.attachments = attachments;//Should not really do it like that
            ((TextView) itemView.findViewById(R.id.imageCount)).setText(String.valueOf(attachments.length));
            if(attachments.length<2)
            {
                itemView.findViewById(R.id.imageCounter).setVisibility(View.GONE);
                if(attachments.length<1)
                    itemView.findViewById(R.id.frame).setVisibility(View.GONE);
            }
        }
        public void setSocNetUrls(List<SocNetUrl> sc)
        {
            socNetUrls = new String[sc.size()];
            for(int i=0;i<sc.size();i++)
            {
                socNetUrlsMenu.getMenu().add(0,i,0,sc.get(i).getTitle());
                socNetUrls[i]=sc.get(i).getUrl();
            }
        }
    }

}
