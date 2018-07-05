package com.example.vaadinprototype.dataprovider;

import com.example.vaadinprototype.model.ProductRepository;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Stream;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 7/2/2018.
 */
@Component
public class ProductDataProvider extends AbstractBackEndDataProvider {
  private final ProductRepository repository;
  
  @Inject
  public ProductDataProvider(ProductRepository repository) {
    this.repository = repository;
  }
  
  @Override
  protected Stream fetchFromBackEnd(Query query) {
    return repository.findAllByOrderByIdDesc().parallelStream();
  }
  
  @Override
  protected int sizeInBackEnd(Query query) {
    return (int) repository.count();
  }
}
