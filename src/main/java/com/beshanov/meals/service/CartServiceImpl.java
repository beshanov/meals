package com.beshanov.meals.service;

import com.beshanov.meals.domain.Dish;
import com.beshanov.meals.domain.Ingredient;
import com.beshanov.meals.domain.MealCategory;
import com.beshanov.meals.domain.Recipe;
import com.beshanov.meals.domain.cart.Cart;
import com.beshanov.meals.domain.cart.CartRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.catalog.Catalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final DishService dishService;

  @Override
  public Cart generate(CartRequest request) {
    int numberOfPersons = request.numberOfPersons();

    List<Dish> allDishes = dishService.getAll();

    List<Ingredient> allIngredients = allDishes.stream().map(Dish::getRecipe)
        .map(Recipe::getIngredients)
        .flatMap(Collection::stream)
        .collect(
            Collectors.toList());

    Map<String, Ingredient> resultIngredients = new HashMap<>();
    for (Ingredient ingredient : allIngredients) {
      Ingredient newIngredient = new Ingredient(ingredient.getName(), ingredient.getMeasure(),
          ingredient.getQuantity() * numberOfPersons);
      Ingredient previous = resultIngredients.putIfAbsent(newIngredient.getName(),
          newIngredient);
      if (previous != null) {
        previous.setQuantity(previous.getQuantity() + newIngredient.getQuantity());
      }
    }

    Cart cart = new Cart();
    cart.setIngredientsSummary(new ArrayList<>(resultIngredients.values()));
    cart.setDishesToCook(allDishes);
    return cart;
  }
}
