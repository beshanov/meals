package com.beshanov.meals.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Recipe implements Serializable {

  private List<Ingredient> ingredients;
  private String text;
}
