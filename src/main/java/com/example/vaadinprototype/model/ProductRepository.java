package com.example.vaadinprototype.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
public interface ProductRepository extends PagingAndSortingRepository<ProductEntry, String> {
  List<ProductEntry> findAllByOrderByIdDesc();
}
