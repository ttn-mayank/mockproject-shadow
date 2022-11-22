package com.ttn.bootcamp.amc.mockproject.core.services.impl;

import com.ttn.bootcamp.amc.mockproject.core.services.PublishedBlogs;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = PublishedBlogs.class, immediate = true)
@Designate(ocd= PublishedBlogsConfig.class)
public class PublishedBlogsImpl implements PublishedBlogs {

    int noOfBlogs;

    @Activate
    public void init(PublishedBlogsConfig config){
        noOfBlogs = config.getNoOfBlogs();
    }

    @Override
    public int getNoOfBlogsDisplay() {
        return noOfBlogs;
    }
}
