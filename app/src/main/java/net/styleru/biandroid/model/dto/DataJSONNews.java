package net.styleru.biandroid.model.dto;

import org.joda.time.DateTime;

/**
 * Created by Tetawex on 02.09.2016.
 */
public class DataJSONNews
{
    public String title;
    public String description;
    public String content;
    public String logoUrl;
    public String[] images;
    public DateTime dateTime;

    public SupportDataSocialNetworkReference[] references;
}
