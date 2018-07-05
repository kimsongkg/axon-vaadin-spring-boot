package com.example.vaadinprototype.ui;

import com.example.vaadinprototype.ui.mainview.MainViewDisplay;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import javax.inject.Inject;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/29/2018.
 */
@Title("Prototype Title")
@SpringUI
@Theme("prototype")
@StyleSheet({"https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"})
public class VaadinUI extends UI {
  
  private MainViewDisplay mainViewDisplay;
  
  @Inject
  public VaadinUI(MainViewDisplay mainViewDisplay) {
    this.mainViewDisplay = mainViewDisplay;
  }
  
  @Override
  protected void init(VaadinRequest vaadinRequest) {
    setContent(mainViewDisplay);
  }
}
