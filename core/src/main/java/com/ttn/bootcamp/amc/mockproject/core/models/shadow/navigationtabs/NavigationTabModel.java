package com.ttn.bootcamp.amc.mockproject.core.models.shadow.navigationtabs;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.vault.util.JcrConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Model(adaptables = {Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class NavigationTabModel {

    @ValueMapValue
    private String logo;
    @ValueMapValue
    private String logolink;

    private List<String> navLink ;

    private Map<String,String> navPageList;

    public Map<String, String> getNavPageList() {
        return navPageList;
    }



    @SlingObject
    private ResourceResolver resolver;

    @SlingObject
    private ResourceResolver resourceResolver;
    private Iterator<Group> groupIterator;
    @PostConstruct
    protected void init() {

        final Session session = resourceResolver.adaptTo(Session.class);
        final String userId = session.getUserID();

        final UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        try {
            final User user = (User) userManager.getAuthorizable(userId);
            groupIterator = user.memberOf();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        navLink = new ArrayList<String>();
        navPageList = new HashMap<String,String>();
        if(logolink != "") {
            Resource resource = resourceResolver.getResource(logolink);
            Iterator<Resource> children = resource.listChildren();
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);


            while (children.hasNext()) {
                Resource child = children.next();
                String path = child.getPath();
                Page rootPage = pageManager.getPage(path);

                if (rootPage != null) {
                    //navLink.add(rootPage.getName());
                    String[] grpx = rootPage.getProperties().get("dropdown", String[].class);
                    int flag=0;

                    if(grpx == null) {
                        flag++;
                    }else{
                        while (groupIterator.hasNext()) {

                            try {
                                if (Arrays.asList(grpx).contains(groupIterator.next().getID())) {
                                    flag++;
                                }
                            } catch (RepositoryException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }


                    String hide = rootPage.getProperties().get("hidenav", String.class);


                    if(flag>0 && hide == null){
                        navPageList.put(rootPage.getName(), rootPage.getPath());
                    }

                }

            }
        }


    }


    public String getLogo() {
        return logo;
    }

    public String getLogolink() {
        return logolink;
    }


    public List<String> getNavLink() {
        return navLink;
    }
}
