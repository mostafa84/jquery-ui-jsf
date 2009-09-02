package org.jquery.ui;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent(value = "org.jquery.ui.TabsComponent")
public class UITabsComponent extends UINamingContainer {
    @Override
    public String getFamily() {
        return "javax.faces.Panel";
    }
}
