package com.beshanov.meals.service;

import com.beshanov.meals.domain.Dish;
import com.beshanov.meals.domain.Ingredient;
import com.beshanov.meals.domain.MealCategory;
import com.beshanov.meals.domain.Measure;
import com.beshanov.meals.domain.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

  private final List<Dish> storage = new ArrayList<>();

  {
    Ingredient eggs = new Ingredient("яйца", Measure.PIECE, 2.0);
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(eggs);
    String recipeText = "1.разбить 2.жарить до готовности";
    Recipe recipe = new Recipe(ingredients, recipeText);
    Dish dish = new Dish("яичница", "вкусная яишка", recipe, null, MealCategory.BREAKFAST);
    storage.add(dish);
  }

  @Override
  public List<Dish> getAll() {
    return storage;
  }

  @Override
  public Dish create(Dish dish) {
    storage.add(dish);
    return dish;
  }

  @Override
  public boolean delete(UUID id) {
    return false;
  }
}
