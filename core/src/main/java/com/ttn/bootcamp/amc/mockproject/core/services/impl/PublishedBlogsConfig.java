package com.ttn.bootcamp.amc.mockproject.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Published Blogs Configuration")
public @interface PublishedBlogsConfig {

    @AttributeDefinition(
            name = "noOfBlogs",
            type = AttributeType.INTEGER
    )
    int getNoOfBlogs();
}
