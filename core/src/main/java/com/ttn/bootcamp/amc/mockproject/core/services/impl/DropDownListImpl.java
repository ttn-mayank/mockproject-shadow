package com.ttn.bootcamp.amc.mockproject.core.services.impl;

import com.ttn.bootcamp.amc.mockproject.core.services.DropdownList;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.util.HashMap;
import java.util.Map;

@Component(service = DropdownList.class, immediate = true)
@Designate(ocd= DropDownListConfig.class)
public class DropDownListImpl implements DropdownList {



    private HashMap<String, String> map;

    @Activate
    public void init(DropDownListConfig config){

        map = new HashMap<String, String>();
        for(String item : config.listItems()){
            String[] keyValue = item.split("=");
            map.put(keyValue[0],keyValue[1]);
        }
    }

    @Override
    public HashMap<String, String> getListItem() {
        return map;
    }

}
