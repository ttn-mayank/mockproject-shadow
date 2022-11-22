package com.ttn.bootcamp.amc.mockproject.core.servlets;

import com.ttn.bootcamp.amc.mockproject.core.util.ResolverUtil;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(
        service = { Servlet.class },
        property = {
                SLING_SERVLET_RESOURCE_TYPES + "=/apps/my/type",
                SLING_SERVLET_METHODS + "=GET",
                SLING_SERVLET_EXTENSIONS + "=html",
                SLING_SERVLET_SELECTORS + "=hello",
        }
)
public class MyServlet extends SlingSafeMethodsServlet {


    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
            IOException {

        String str;
        try {
            ResourceResolver resourceResolver= ResolverUtil.newResolver(resourceResolverFactory);
             str = resourceResolver.getResource("/content/mockproject").getValueMap().get("jcr:primaryType",String.class);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }


        response.getWriter().write("This is MyServlet from doGet");
        response.getWriter().write(str);
        response.setContentType("text/plain");


    }
}
