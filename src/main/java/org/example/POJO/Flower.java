package org.example.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flower {
  private String name;
  private double inPrice;
  private long totalNumber;
  private long buyNumber;

}
