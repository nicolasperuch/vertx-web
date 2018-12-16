package dev.peruch.vertxpoc.service;

import io.vertx.core.json.JsonArray;

import static dev.peruch.vertxpoc.mocks.Mocks.getProducts;

public class ProductService {

  public static JsonArray buildProductsResponse() {
    JsonArray arr = new JsonArray();
    getProducts().forEach((k, v) -> arr.add(v));
    return arr;
  }
}
