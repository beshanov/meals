package com.beshanov.meals.service;

import com.beshanov.meals.domain.Dish;
import java.util.List;
import java.util.UUID;

public interface DishService {

  List<Dish> getAll();

  Dish create(Dish dish);

  boolean delete(UUID id);
}
