package net.styleru.biandroid.view.adapter.recycler;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import net.styleru.biandroid.R;
import net.styleru.biandroid.model.dto.DataCommunity;
import net.styleru.biandroid.model.dto.SocNetUrl;
import net.styleru.biandroid.view.activity.MainActivity;

import java.util.List;

public class CommunityRecyclerAdapter extends MutableRecyclerAdapter<DataCommunity>
{
    public CommunityRecyclerAdapter(Context context, List<DataCommunity> data)
    {
        super(context,data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.card_community, parent,false);
        CommunityViewHolder holder=new CommunityViewHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        CommunityViewHolder commHolder= (CommunityViewHolder) holder;
        DataCommunity current=getData().get(position);
        commHolder.title.setText(current.getTitle());
        commHolder.description.setText(current.getDescription());
        commHolder.subTitle.setText(current.getSubTitle());
        commHolder.clearSocNetUrls();
        commHolder.setSocNetUrls(current.getSocNetUrls());

        loadImageIntoView(current.getLogoUrl(),commHolder.logo);

    }


    class CommunityViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        TextView subTitle;
        TextView description;
        ImageView logo;
        ImageButton moreInfoButton;
        String[] socNetUrls;

        PopupMenu socNetUrlsMenu;

        // create constructor to get widget reference
        public CommunityViewHolder(View itemView)
        {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            subTitle = (TextView) itemView.findViewById(R.id.dateTime);
            logo= (ImageView) itemView.findViewById(R.id.logo);
            moreInfoButton= (ImageButton) itemView.findViewById(R.id.moreInfoButton);
            socNetUrlsMenu =new PopupMenu(itemView.getContext(),moreInfoButton);
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
        public void clearSocNetUrls() {
            socNetUrlsMenu.getMenu().clear();
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
