package com.example.vaadinprototype.api;

import com.example.vaadinprototype.api.command.AddProductCommand;
import com.example.vaadinprototype.api.command.DeleteProductCommand;
import com.example.vaadinprototype.api.command.UpdateProductCommand;
import com.example.vaadinprototype.api.event.ProductAddedEvent;
import com.example.vaadinprototype.api.event.ProductDeletedEvent;
import com.example.vaadinprototype.api.event.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Aggregate
public class Product {
  @AggregateIdentifier
  private String id;
  
  public Product(String id) {
    this.id = id;
  }
  
  private Product(){}
  
  @CommandHandler
  public Product(AddProductCommand command) {
    apply(new ProductAddedEvent(command.getId(), command.getProductEntry()));
  }
  
  @EventSourcingHandler
  private void on(ProductAddedEvent event) {
    this.id = event.getId();
  }
  
  @CommandHandler
  public void on(UpdateProductCommand command) {
    apply(new ProductUpdatedEvent(command.getId(), command.getProductEntry()));
  }
  
  @CommandHandler
  public void on(DeleteProductCommand command) {
    apply(new ProductDeletedEvent(command.getId()));
  }
  
}
