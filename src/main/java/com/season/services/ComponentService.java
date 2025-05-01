package com.season.services;


import com.season.entities.Component;
import com.season.entities.Page;
import com.season.entities.User;
import com.season.iservices.IComponentService;
import com.season.repositories.ComponentRepository;
import com.season.repositories.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentService implements IComponentService {
    @Autowired
     ComponentRepository componentRepository;
    @Autowired
 PageRepository pageRepository;

    @Override
    public Component createComponent(Component component, Page page, User user) {
        Page existingPage = pageRepository.findById(page.getId())
                .orElseThrow(() -> new IllegalArgumentException("Page not found"));
        if (!existingPage.getSite().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        component.setPage(existingPage);
        return componentRepository.save(component);
    }

    @Override
    public List<Component> getPageComponents(Page page) {
        return componentRepository.findByPage(page);
    }

    @Override
    public Component updateComponent(Long componentId, Component component, User user) {
        Component existingComponent = componentRepository.findById(componentId)
                .orElseThrow(() -> new IllegalArgumentException("Component not found"));
        if (!existingComponent.getPage().getSite().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        existingComponent.setType(component.getType());
        existingComponent.setContent(component.getContent());
        existingComponent.setPosition(component.getPosition());
        return componentRepository.save(existingComponent);
    }

    @Override
    public void deleteComponent(Long componentId, User user) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new IllegalArgumentException("Component not found"));
        if (!component.getPage().getSite().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        componentRepository.delete(component);
    }
}