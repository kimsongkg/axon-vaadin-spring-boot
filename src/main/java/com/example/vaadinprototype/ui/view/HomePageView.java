package com.example.vaadinprototype.ui.view;

import com.example.vaadinprototype.ui.uiscope.UIScopePrototypeViews;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/29/2018.
 */
@SpringComponent
@UIScope
@SpringView(name = UIScopePrototypeViews.HOME)
public class HomePageView extends HomePageViewDesign implements View {
  
  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
  
  }
  
}
