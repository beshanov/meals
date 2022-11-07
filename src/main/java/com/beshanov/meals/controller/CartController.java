package com.beshanov.meals.controller;

import com.beshanov.meals.domain.cart.Cart;
import com.beshanov.meals.domain.cart.CartRequest;
import com.beshanov.meals.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping
  public Cart generate(@RequestParam Integer numberOfPerson, @RequestParam(required = false) Integer numberOfDays) {
    return cartService.generate(new CartRequest(numberOfPerson, numberOfDays));
  }
}
