package com.season.iservices;

import com.season.entities.Component;
import com.season.entities.Page;
import com.season.entities.User;

import java.util.List;

public interface IComponentService {
    Component createComponent(Component component, Page page, User user);
    List<Component> getPageComponents(Page page);
    Component updateComponent(Long componentId, Component component, User user);
    void deleteComponent(Long componentId, User user);
}
