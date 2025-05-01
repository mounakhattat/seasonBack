package com.season.controllers;

import com.season.entities.Component;
import com.season.entities.Page;
import com.season.entities.User;
import com.season.services.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor
public class ComponentController {
    @Autowired
 ComponentService componentService;

    @PostMapping
    public ResponseEntity<Component> createComponent(@RequestBody Component component, @RequestParam Long pageId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Page page = new Page();
        page.setId(pageId);
        Component createdComponent = componentService.createComponent(component, page, user);
        return ResponseEntity.ok(createdComponent);
    }

    @GetMapping("/page/{pageId}")
    public ResponseEntity<List<Component>> getPageComponents(@PathVariable Long pageId) {
        Page page = new Page();
        page.setId(pageId);
        List<Component> components = componentService.getPageComponents(page);
        return ResponseEntity.ok(components);
    }

    @PutMapping("/{componentId}")
    public ResponseEntity<Component> updateComponent(@PathVariable Long componentId, @RequestBody Component component, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Component updatedComponent = componentService.updateComponent(componentId, component, user);
        return ResponseEntity.ok(updatedComponent);
    }

    @DeleteMapping("/{componentId}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long componentId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        componentService.deleteComponent(componentId, user);
        return ResponseEntity.noContent().build();
    }
}
