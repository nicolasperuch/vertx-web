package dev.peruch.vertxpoc.mocks;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Mocks {

  private static Map<String, JsonObject> products = new HashMap<>();

  public static void loadMocks() {
    addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
    addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
    addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
  }

  private static void addProduct(JsonObject product) {
    products.put(product.getString("id"), product);
  }

  public static Map<String, JsonObject> getProducts(){
    return products;
  }

  public static JsonObject getProduct(String id){
    return products.get(id);
  }
}
