package com.example.vaadinprototype.model;

import com.example.vaadinprototype.api.event.ProductAddedEvent;
import com.example.vaadinprototype.api.event.ProductDeletedEvent;
import com.example.vaadinprototype.api.event.ProductUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Component
public class ProductEventListener {
  private final ProductRepository repository;
  
  @Inject
  public ProductEventListener(ProductRepository repository) {
    this.repository = repository;
  }
  
  @EventHandler
  public void on(ProductAddedEvent event) {
    ProductEntry entry = new ProductEntry();
    entry.setId(event.getId());
    entry.setProductName(event.getProductEntry().getProductName());
    entry.setProductQty(event.getProductEntry().getProductQty());
    entry.setProductDescription(event.getProductEntry().getProductDescription());
    repository.save(entry);
  }
  
  @EventHandler
  public void on(ProductUpdatedEvent event) {
    Optional<ProductEntry> entry = repository.findById(event.getId());
    entry.get().setProductName(event.getProductEntry().getProductName());
    entry.get().setProductQty(event.getProductEntry().getProductQty());
    entry.get().setProductDescription(event.getProductEntry().getProductDescription());
    repository.save(entry.get());
  }
  
  @EventHandler
  public void on(ProductDeletedEvent event) {
    repository.deleteById(event.getId());
  }
}
