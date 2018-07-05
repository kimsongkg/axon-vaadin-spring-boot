package com.example.vaadinprototype.api.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Kimsong San (k.san@gl-f.com) on 6/30/2018.
 */
@Value
public class DeleteProductCommand {
  @TargetAggregateIdentifier
  String id;
}
