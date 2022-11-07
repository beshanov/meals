package com.beshanov.meals.domain.cart;

public record CartRequest(Integer numberOfPersons, Integer numberOfDays) {

  public CartRequest {
    if (numberOfPersons < 1) {
      throw new IllegalStateException("Number of persons must be at least one");
    }
  }
}
