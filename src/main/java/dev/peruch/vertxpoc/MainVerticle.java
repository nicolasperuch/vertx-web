package dev.peruch.vertxpoc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.Map;

public class MainVerticle extends AbstractVerticle {

  private Map<String, JsonObject> products = new HashMap<>();

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    setUpInitialData();

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.get("/").handler(this::sayHi);
    router.get("/products").handler(this::fetchProducts);

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

  private void sayHi(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    response
      .putHeader("content-type", "text/plain")
      .end("Hi");
  }

  private void fetchProducts(RoutingContext routingContext) {
      JsonArray arr = new JsonArray();
      products.forEach((k, v) -> arr.add(v));
      routingContext
        .response()
        .putHeader("content-type", "application/json")
        .end(arr.encodePrettily());
  }

  private void setUpInitialData() {
    addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
    addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
    addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
  }

  private void addProduct(JsonObject product) {
    products.put(product.getString("id"), product);
  }
}
