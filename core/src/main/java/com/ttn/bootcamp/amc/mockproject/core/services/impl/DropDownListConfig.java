package com.ttn.bootcamp.amc.mockproject.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.util.List;

@ObjectClassDefinition(name = "Dropdown List Configuration")
public @interface DropDownListConfig {

    @AttributeDefinition(
            name = "services",
            type = AttributeType.STRING,
            cardinality = Integer.MAX_VALUE,
            description = "Provide key value pair separated by '-' eg: key - value "
    )
    String[] listItems();
}
