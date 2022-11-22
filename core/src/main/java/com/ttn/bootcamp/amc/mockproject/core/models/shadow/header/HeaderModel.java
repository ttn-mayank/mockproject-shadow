package com.ttn.bootcamp.amc.mockproject.core.models.shadow.header;

import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
    @ValueMapValue
    @Default(values = "Page Title")
    private String pageTitle;

    private String userName;
    @ValueMapValue
    @Default(values = "white")
    private String bgColor;

    @SlingObject
    private ResourceResolver resourceResolver;

    @PostConstruct
    public void init(){
        final Session session = resourceResolver.adaptTo(Session.class);
        final String userId = session.getUserID();
        final UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        try {
            final User user = (User) userManager.getAuthorizable(userId);
            this.userName=user.getID();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getUserName() {
        return userName;
    }

    public String getBgColor() {
        return bgColor;
    }



}
