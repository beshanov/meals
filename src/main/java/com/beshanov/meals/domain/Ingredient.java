package com.beshanov.meals.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient implements Serializable {
  private String name;
  private Measure measure;
  private Double quantity;
}
