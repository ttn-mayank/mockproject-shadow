package com.ttn.bootcamp.amc.mockproject.core.models.blogs;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class ,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListingItemModel {

    @Inject
    private String navlabel;
    @Inject
    private String navlink;

    public String getNavlink() {
        return navlink;
    }

    public void setNavlink(String navlink) {
        this.navlink = navlink;
    }

    public String getNavlabel() {
        return navlabel;
    }

    public void setNavlabel(String navlabel) {
        this.navlabel = navlabel;
    }
}
