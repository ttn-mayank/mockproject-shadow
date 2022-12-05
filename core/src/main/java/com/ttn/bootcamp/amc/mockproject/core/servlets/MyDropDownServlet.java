package com.ttn.bootcamp.amc.mockproject.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.ttn.bootcamp.amc.mockproject.core.services.DropdownList;
import com.ttn.bootcamp.amc.mockproject.core.util.ResolverUtil;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(
        service = { Servlet.class },
        property = {
                SLING_SERVLET_PATHS + "=/bin/mydropdownlist",
                SLING_SERVLET_METHODS + "=GET",
        }
)
public class MyDropDownServlet extends SlingSafeMethodsServlet {

    @Reference
    private DropdownList dropdownService;
    transient ResourceResolver resourceResolver;
    transient ValueMap valueMap;
    transient List<Resource> resourceList;
    Map<String,String> dropdownList;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
            IOException {

        resourceResolver = request.getResourceResolver();
        dropdownList = dropdownService.getListItem();

        resourceList = new ArrayList<>();

        for (Map.Entry<String,String> entry : dropdownList.entrySet()) {
            valueMap = new ValueMapDecorator(new HashMap<>());
            valueMap.put("value", entry.getValue());
            valueMap.put("text", entry.getKey());
            resourceList.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", valueMap));
        }

        DataSource dataSource = new SimpleDataSource(resourceList.iterator());
        request.setAttribute(DataSource.class.getName(), dataSource);

    }
}
