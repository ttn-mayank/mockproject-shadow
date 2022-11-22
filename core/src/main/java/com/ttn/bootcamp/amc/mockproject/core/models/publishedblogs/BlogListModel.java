package com.ttn.bootcamp.amc.mockproject.core.models.publishedblogs;

import com.ttn.bootcamp.amc.mockproject.core.services.PublishedBlogs;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListModel {

    @Inject
    private List<BlogListItemModel> blogList;
    @OSGiService
    private PublishedBlogs publishedBlogs;

    private int value;

    @PostConstruct
    public void setValue() {
        this.value = publishedBlogs.getNoOfBlogsDisplay() -1;
    }

    public List<BlogListItemModel> getBlogList() {
        return blogList;
    }

    public int getValue() {
        return value;
    }


}
