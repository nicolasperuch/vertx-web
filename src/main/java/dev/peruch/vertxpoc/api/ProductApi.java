package dev.peruch.vertxpoc.api;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static dev.peruch.vertxpoc.mocks.Mocks.getProduct;
import static dev.peruch.vertxpoc.service.ProductService.buildProductsResponse;
import static java.util.Objects.isNull;

public class ProductApi extends BaseApi {

  public Router buildApi(Router router){
    router.route().handler(BodyHandler.create());
    router.get("/product/:id").handler(this::fetchProduct);
    router.get("/products").handler(this::fetchProducts);
    return router;
  }

  private void fetchProduct(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();

    if(isNull(id)){
      sendError(BAD_REQUEST, response, BAD_REQUEST_MESSAGE);
    } else {
      JsonObject product = getProduct(id);
      if(isNull(product)){
        sendError(NOT_FOUND, response, NOT_FOUND_MESSAGE);
      } else {
        routingContext
          .response()
          .putHeader("content-type", "application/json")
          .end(product.encodePrettily());
      }
    }
  }

  private void fetchProducts(RoutingContext routingContext) {
    routingContext
      .response()
      .putHeader("content-type", "application/json")
      .end(buildProductsResponse().encodePrettily());
  }

  private void sendError(int statusCode, HttpServerResponse response, String message) {
    response.setStatusCode(statusCode).end(message);
  }
}
