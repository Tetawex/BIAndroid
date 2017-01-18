package net.styleru.biandroid.model.dto;

/**
 * Created by Tetawex on 28.10.2016.
 */

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class DataNews {

    private int id;
    private String title;
    private String description;
    private String logoUrl;
    private String dateTime;
    private DateTime trueDateTime;
    private List<SocNetUrl> socNetUrls = new ArrayList<SocNetUrl>();
    private List<AttachmentUrl> attachmentsUrls = new ArrayList<AttachmentUrl>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     *
     * @param logoUrl
     * The logoUrl
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     *
     * @return
     * The dateTime
     */
    public DateTime getDateTime()
    {
        if(trueDateTime==null)
            trueDateTime=new DateTime(dateTime);
        return trueDateTime;
    }

    /**
     *
     * @param dateTime
     * The dateTime
     */
    public void setDateTime(DateTime dateTime) {
        this.trueDateTime = dateTime;
    }

    /**
     *
     * @return
     * The socNetUrls
     */
    public List<SocNetUrl> getSocNetUrls() {
        return socNetUrls;
    }

    /**
     *
     * @param socNetUrls
     * The socNetUrls
     */
    public void setSocNetUrls(List<SocNetUrl> socNetUrls) {
        this.socNetUrls = socNetUrls;
    }

    /**
     *
     * @return
     * The attachmentsUrls
     */
    public List<AttachmentUrl> getAttachmentsUrls() {
        return attachmentsUrls;
    }

    /**
     *
     * @param attachmentsUrls
     * The attachmentsUrls
     */
    public void setAttachmentsUrls(List<AttachmentUrl> attachmentsUrls) {
        this.attachmentsUrls = attachmentsUrls;
    }

}