package com.beshanov.meals.service;

import com.beshanov.meals.domain.cart.Cart;
import com.beshanov.meals.domain.cart.CartRequest;

public interface CartService {
  Cart generate(CartRequest request);
}
