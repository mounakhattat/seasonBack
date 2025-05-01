package com.season.controllers;

import com.season.entities.Page;
import com.season.entities.Site;
import com.season.entities.User;
import com.season.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {
    @Autowired
  PageService pageService;

    @PostMapping
    public ResponseEntity<Page> createPage(@RequestBody Page page, @RequestParam Long siteId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Site site = new Site();
        site.setId(siteId);
        Page createdPage = pageService.createPage(page, site, user);
        return ResponseEntity.ok(createdPage);
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<List<Page>> getSitePages(@PathVariable Long siteId) {
        Site site = new Site();
        site.setId(siteId);
        List<Page> pages = pageService.getSitePages(site);
        return ResponseEntity.ok(pages);
    }

    @PutMapping("/{pageId}")
    public ResponseEntity<Page> updatePage(@PathVariable Long pageId, @RequestBody Page page, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Page updatedPage = pageService.updatePage(pageId, page, user);
        return ResponseEntity.ok(updatedPage);
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<Void> deletePage(@PathVariable Long pageId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        pageService.deletePage(pageId, user);
        return ResponseEntity.noContent().build();
    }
}
