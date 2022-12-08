package org.example.POJO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  private String buyUserName;
  private String buyFlowerName;
  private long buyNumber;
  private double totalPrice;
  private java.sql.Date buyTime;

}
