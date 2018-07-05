package com.example.vaadinprototype.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Entity
@Data
public class ProductEntry {
  @Id
  private String id;
  
  private String productName;
  private int productQty;
  
  @Type(type = "text")
  private String productDescription;
}
