package com.ttn.bootcamp.amc.mockproject.core.models.homepage.bloglist;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListItemModel {


    @ValueMapValue
    private String mainTitle;
    @ValueMapValue
    private String img;

    @ValueMapValue
    private  String date;

    @ValueMapValue
    private  String tag;
    @ValueMapValue
    private String linkimage;
    @ValueMapValue
    private String content;

    @ValueMapValue
    private String tabsButtonText;

    @ValueMapValue
    private String tabsLinkitem;


    public String getTabsButtonText() {
        return tabsButtonText;
    }



    public String getContent() {
        return content;
    }

    public String getMainTitle() {
        return mainTitle;
    }


    public String getImg() {
        return img;
    }


    public String getTabsLinkitem() {
        return tabsLinkitem;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public String getDate() {
        return date;
    }

    public String getTag() {
        return tag;
    }
}
