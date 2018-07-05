package com.example.vaadinprototype.api.event;

import com.example.vaadinprototype.model.ProductEntry;
import lombok.Value;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Value
public class ProductAddedEvent {
  String id;
  ProductEntry productEntry;
}
