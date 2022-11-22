package com.ttn.bootcamp.amc.mockproject.core.models.shadow.Footer;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class FooterBlockItemModel {
    @ValueMapValue
    private String navlabel;

    @ValueMapValue
    private String navlink;
    @ValueMapValue
    private String isExternal;

    public String getNavlabel() {
        return navlabel;
    }

    public String getNavlink() {
        return navlink;
    }

    public String isExternal() {
        return isExternal;
    }





}
