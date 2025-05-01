package com.season.iservices;

import com.season.entities.Page;
import com.season.entities.Site;
import com.season.entities.User;

import java.util.List;

public interface IPageService {
    Page createPage(Page page, Site site, User user);
    List<Page> getSitePages(Site site);
    Page updatePage(Long pageId, Page page, User user);
    void deletePage(Long pageId, User user);
}
