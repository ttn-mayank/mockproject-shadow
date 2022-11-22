package com.ttn.bootcamp.amc.mockproject.core.models.blogs;

import com.ttn.bootcamp.amc.mockproject.core.models.header.HeaderListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListingModel {


    @Inject
    private List<BlogListingItemModel> pagelist;


    public List<BlogListingItemModel> getPagelist() {
        return pagelist;
    }

    /*public void setPagelist(ArrayList<HeaderListItem> pagelist) {
        this.pagelist = pagelist;
    }*/


}
