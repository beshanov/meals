package com.beshanov.meals.domain;


import java.io.Serializable;
import java.net.URI;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dish implements Serializable {
  private UUID id;
  private String name;
  private String description;
  private Recipe recipe;
  private URI photo;
  private MealCategory category;


  public Dish(String name, String description, Recipe recipe, URI photo,
      MealCategory category) {
    this.name = name;
    this.description = description;
    this.recipe = recipe;
    this.photo = photo;
    this.category = category;
    this.id = UUID.randomUUID();
  }
}
