package com.memejob.app.data;

/**
 * Created by graeme on 19/04/14.
 */
public class RssItem {

    // item title
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // item link
    private String link;
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    // item image
    private String image;
    public String getImage(){return image;}
    public void setImage(String image){this.image=image;}


    // item image
    private String description;
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}

    @Override
    public String toString() {
        return title;
    }

}
