package com.example.vaadinprototype.service;

import com.example.vaadinprototype.model.ProductEntry;
import com.example.vaadinprototype.model.ProductRepository;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 7/2/2018.
 */
@Component
public class ProductService {
  
  private final ProductRepository repository;
  
  @Inject
  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }
  
  public ProductEntry getProductEntry(String id) {
    Optional<ProductEntry> entry = repository.findById(id);
    return entry.get();
  }
}
