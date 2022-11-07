package com.beshanov.meals.controller;

import com.beshanov.meals.domain.Dish;
import com.beshanov.meals.service.DishService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {

  private final DishService dishService;

  @PostMapping()
  public String create(@RequestBody Dish dish) {
    dishService.create(dish);
    return dish.toString();
  }

  @GetMapping()
  public List<Dish> create() {
    return dishService.getAll();
  }

}
