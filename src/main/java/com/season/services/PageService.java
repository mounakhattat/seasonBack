package com.season.services;

import com.season.entities.Page;
import com.season.entities.Site;
import com.season.entities.User;
import com.season.iservices.IPageService;
import com.season.repositories.PageRepository;
import com.season.repositories.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PageService implements IPageService {
    @Autowired
    PageRepository pageRepository;
    @Autowired
  SiteRepository siteRepository;

    @Override
    public Page createPage(Page page, Site site, User user) {
        Site existingSite = siteRepository.findById(site.getId())
                .orElseThrow(() -> new IllegalArgumentException("Site not found"));
        if (!existingSite.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        page.setSite(existingSite);
        return pageRepository.save(page);
    }

    @Override
    public List<Page> getSitePages(Site site) {
        return pageRepository.findBySite(site);
    }

    @Override
    public Page updatePage(Long pageId, Page page, User user) {
        Page existingPage = pageRepository.findById(pageId)
                .orElseThrow(() -> new IllegalArgumentException("Page not found"));
        if (!existingPage.getSite().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        existingPage.setTitle(page.getTitle());
        existingPage.setSlug(page.getSlug());
        existingPage.setOrder(page.getOrder());
        return pageRepository.save(existingPage);
    }

    @Override
    public void deletePage(Long pageId, User user) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new IllegalArgumentException("Page not found"));
        if (!page.getSite().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        pageRepository.delete(page);
    }
}

