package com.example.vaadinprototype.ui.mainview;

import com.example.vaadinprototype.ui.view.MainMenuBar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/29/2018.
 */
@SpringViewDisplay
public class MainViewDisplay extends CustomComponent implements ViewDisplay {
  
  private final VerticalLayout content = new VerticalLayout();
  
  @Inject
  public MainViewDisplay(MainMenuBar menuBar) {
    final VerticalLayout root = new VerticalLayout();
    root.setSpacing(false);
    root.setSizeFull();
    root.setMargin(false);
  
    root.addComponent(menuBar);
    root.addComponent(content);
    setCompositionRoot(root);
  }
  
  @Override
  public void showView(View view) {
    content.removeAllComponents();
    content.setSpacing(false);
    content.setSizeFull();
    content.setMargin(false);
    content.addComponent((Component) view);
  }
}
