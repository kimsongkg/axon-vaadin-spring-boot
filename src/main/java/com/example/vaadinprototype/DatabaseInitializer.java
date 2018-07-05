package com.example.vaadinprototype;

import com.example.vaadinprototype.api.command.AddProductCommand;
import com.example.vaadinprototype.model.ProductEntry;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Component
@Profile(value = {"default"})
public class DatabaseInitializer implements CommandLineRunner {
  
  private final CommandGateway commandGateway;
  
  @Inject
  public DatabaseInitializer(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }
  
  @Override
  public void run(String... strings) throws Exception {
    //createProduct();
  }
  
  private void createProduct(){
    commandGateway.sendAndWait(new AddProductCommand(UUID.randomUUID().toString(), new ProductEntry()));
  }
}
