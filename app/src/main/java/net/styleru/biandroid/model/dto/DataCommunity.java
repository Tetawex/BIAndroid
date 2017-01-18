
package net.styleru.biandroid.model.dto;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCommunity {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @SerializedName("subTitle")
    @Expose
    private String subTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("socNetUrls")
    @Expose
    private List<SocNetUrl> socNetUrls = new ArrayList<SocNetUrl>();

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 
     * @param logoUrl
     *     The logoUrl
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * 
     * @return
     *     The socNetUrls
     */
    public List<SocNetUrl> getSocNetUrls() {
        return socNetUrls;
    }

    /**
     * 
     * @param socNetUrls
     *     The socNetUrls
     */
    public void setSocNetUrls(List<SocNetUrl> socNetUrls) {
        this.socNetUrls = socNetUrls;
    }

}
