package com.ttn.bootcamp.amc.mockproject.core.models.shadow.Footer;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class FooterBlockModel {
    @Inject
    private List<FooterBlockItemModel> itemList;

    public List<FooterBlockItemModel> getItemList() {
        return itemList;
    }
}
