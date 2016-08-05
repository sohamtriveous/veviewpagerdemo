
package cc.soham.newsapplicationvodafone.objects;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlsToLogos")
    @Expose
    private UrlsToLogos urlsToLogos;
    @SerializedName("sortBysAvailable")
    @Expose
    private List<String> sortBysAvailable = new ArrayList<String>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
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
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The urlsToLogos
     */
    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    /**
     * 
     * @param urlsToLogos
     *     The urlsToLogos
     */
    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    /**
     * 
     * @return
     *     The sortBysAvailable
     */
    public List<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    /**
     * 
     * @param sortBysAvailable
     *     The sortBysAvailable
     */
    public void setSortBysAvailable(List<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }

}
