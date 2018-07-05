package com.example.vaadinprototype.ui.product;

import com.example.vaadinprototype.api.command.AddProductCommand;
import com.example.vaadinprototype.api.command.DeleteProductCommand;
import com.example.vaadinprototype.api.command.UpdateProductCommand;
import com.example.vaadinprototype.dataprovider.ProductDataProvider;
import com.example.vaadinprototype.model.ProductEntry;
import com.example.vaadinprototype.service.ProductService;
import com.example.vaadinprototype.ui.uiscope.UIScopePrototypeViews;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@SpringComponent
@UIScope
@SpringView(name = UIScopePrototypeViews.LIST)
public class ProductListView extends ProductListViewDesign implements View {
  private final ProductDataProvider dataProvider;
  private final Binder<ProductEntry> productEntryBinder;
  private final CommandGateway commandGateway;
  private final ProductService productService;
  
  @Inject
  public ProductListView(ProductDataProvider dataProvider, CommandGateway commandGateway, ProductService productService) {
    this.dataProvider = dataProvider;
    this.commandGateway = commandGateway;
    this.productService = productService;
    productEntryBinder= createProductBinder();
    productEntryBinder.addStatusChangeListener(e -> saveButton.setEnabled(e.getBinder().isValid()));
    
    fetchData();
    initAddListener();
  }
  
  private void fetchData() {
    productGrid.setDataProvider(dataProvider);
    productGrid.addComponentColumn(productEntry -> {
      Button deleteButton = new Button();
      deleteButton.setIcon(VaadinIcons.TRASH);
      deleteButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
      deleteButton.addClickListener(clickEvent -> {
        commandGateway.sendAndWait(new DeleteProductCommand(productEntry.getId()));
        dataProvider.refreshAll();
      });
      return deleteButton;
    });
    productGrid.addComponentColumn(productEntry -> {
      Button editButton = new Button();
      editButton.setIcon(VaadinIcons.EDIT);
      editButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
      editButton.addClickListener(clickEvent -> {
        saveButton.setCaption("Save change");
        updateBeans(productEntry.getId());
      });
      return editButton;
    });
  }
  
  private void updateBeans(String id){
    Objects.requireNonNull(id);
    ProductEntry entry = productService.getProductEntry(id);
    productEntryBinder.setBean(entry);
  }
  
  private Binder<ProductEntry> createProductBinder() {
    Binder<ProductEntry> resultBinder = new BeanValidationBinder<>(ProductEntry.class);
    resultBinder.forField(productNameTextField).asRequired("Enter product name").bind("productName");
    resultBinder.forField(productQtyTextField).withNullRepresentation("").withConverter(new StringToIntegerConverter("Enter number qty of product")).bind("productQty");
    resultBinder.bind(productDescriptionTextArea, "productDescription");
    resultBinder.setBean(new ProductEntry());
    return resultBinder;
  }
  
  private void initAddListener() {
    saveButton.addClickListener(clickEvent -> {
      if(productEntryBinder.getBean().getId() != null) {
        commandGateway.sendAndWait(new UpdateProductCommand(
                productEntryBinder.getBean().getId(),
                productEntryBinder.getBean()));
      } else {
        commandGateway.sendAndWait(new AddProductCommand(
                UUID.randomUUID().toString(),
                productEntryBinder.getBean()));
      }
      clearForm();
      dataProvider.refreshAll();
    });
    cancelButton.addClickListener(clickEvent -> {
      clearForm();
    });
  }
  
  private void clearForm() {
    saveButton.setCaption("Save");
    productNameTextField.clear();
    productDescriptionTextArea.clear();
    productQtyTextField.clear();
  }
  
}
