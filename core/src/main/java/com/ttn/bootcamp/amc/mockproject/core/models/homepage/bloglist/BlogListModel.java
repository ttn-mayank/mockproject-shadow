package com.ttn.bootcamp.amc.mockproject.core.models.homepage.bloglist;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListModel {

    @Inject
    private List<BlogListItemModel> blogList;

    public List<BlogListItemModel> getBlogList() {
        return blogList;
    }
}
