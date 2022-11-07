package com.beshanov.meals.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.beshanov.meals.domain.Dish;
import com.beshanov.meals.domain.Ingredient;
import com.beshanov.meals.domain.MealCategory;
import com.beshanov.meals.domain.Measure;
import com.beshanov.meals.domain.Recipe;
import com.beshanov.meals.domain.cart.Cart;
import com.beshanov.meals.domain.cart.CartRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class CartServiceImplTest {

  @Test
  @DisplayName("Amount of ingredients should be increased proportionally to number of persons")
  @ParameterizedTest
  @ValueSource(ints = {1, 9, 99})
  void generateTest_ingredientsMultipliedByNumberOfPersons_whenNumberOfPersonsProvidedInCartRequest() {
    int numberOfPersons = 6;

    DishService dishServiceMock = Mockito.mock(DishService.class);
    Mockito.when(dishServiceMock.getAll()).thenReturn(getAllMockDishes());
    CartServiceImpl cartService = new CartServiceImpl(dishServiceMock);
    CartRequest cartRequest = new CartRequest(numberOfPersons, 0);
    Cart cart = cartService.generate(cartRequest);

    Map<String, Ingredient> originalRecipeIngredients = new HashMap<>();
    for (Dish dish : cart.getDishesToCook()) {
      List<Ingredient> ingredients = dish.getRecipe().getIngredients();
      for (Ingredient ingredient : ingredients) {
        Ingredient previousValue = originalRecipeIngredients.putIfAbsent(ingredient.getName(),
            ingredient);
        if (previousValue != null) {
          previousValue.setQuantity(previousValue.getQuantity() + ingredient.getQuantity());
        }
      }
    }

    for (Ingredient ingredient : cart.getIngredientsSummary()) {
      Ingredient originalIngredient = originalRecipeIngredients.get(ingredient.getName());
      assertEquals(originalIngredient.getQuantity() * numberOfPersons, ingredient.getQuantity());
    }
  }

  private List<Dish> getAllMockDishes() {
    List<Dish> allDishes = new ArrayList<>();
    Ingredient eggs = new Ingredient("яйца", Measure.PIECE, 2.0);
    Ingredient eggs2 = new Ingredient("яйца", Measure.PIECE, 1.0);
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<Ingredient> ingredients2 = new ArrayList<>();
    ingredients.add(eggs);
    ingredients2.add(eggs2);
    String recipeText = "1.разбить 2.жарить до готовности";
    Recipe recipe = new Recipe(ingredients, recipeText);
    Recipe recipe2 = new Recipe(ingredients2, recipeText);
    Dish dish = new Dish("яичница", "вкусная яишка", recipe, null, MealCategory.BREAKFAST);
    Dish dish2 = new Dish("яичница2", "вкусная яишка", recipe2, null, MealCategory.BREAKFAST);
    allDishes.add(dish);
    allDishes.add(dish2);
    return allDishes;
  }
}