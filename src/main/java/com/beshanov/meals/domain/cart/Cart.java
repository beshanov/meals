package com.beshanov.meals.domain.cart;

import com.beshanov.meals.domain.Dish;
import com.beshanov.meals.domain.Ingredient;
import java.util.List;
import lombok.Data;

@Data
public class Cart {
  List<Ingredient> ingredientsSummary;

  List<Dish> dishesToCook;
}
