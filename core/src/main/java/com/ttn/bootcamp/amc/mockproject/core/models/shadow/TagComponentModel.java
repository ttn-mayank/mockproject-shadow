package com.ttn.bootcamp.amc.mockproject.core.models.shadow;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagComponentModel {
    @ValueMapValue
    private String path;


    public String getPath() {
        return path;
    }

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }



    @Self
    Resource resource;

    private HashMap<String, String> navPageList;

    public HashMap<String, String> getNavPageList() {
        return navPageList;
    }



    @SlingObject
    private ResourceResolver resourceResolver;

    @PostConstruct
    public void init() {

        final Session session = resourceResolver.adaptTo(Session.class);
        final String userId = session.getUserID();
        final UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        try {
            final User user = (User) userManager.getAuthorizable(userId);

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        PageManager pm = resource.getResourceResolver().adaptTo(PageManager.class);
        Page containingPage = pm.getContainingPage (resource);
        Tag tag[] = containingPage.getTags();
        tags= Arrays.asList(tag);

        navPageList = new HashMap<String,String>();
        if(path != "") {
            Resource resource = resourceResolver.getResource(path);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page rootPage = pageManager.getPage(path);
            performTask(rootPage,containingPage);

        }

    }

    private void performTask(Page rootPage,Page containingPage){

            if (rootPage != null) {

                if(checkTags(rootPage.getTags()) && !rootPage.equals(containingPage)  ){
                    navPageList.put(rootPage.getName(), rootPage.getPath());
                }
                Iterator<Page> cpage = rootPage.listChildren();

                while(cpage.hasNext()){
                    performTask(cpage.next(),containingPage);
                }

            }


    }

    private boolean checkTags(Tag[] tagList){
        for(Tag t : tagList){
            if(tags.contains(t)){
                return true;
            }
        }

        return false;
    }



}
