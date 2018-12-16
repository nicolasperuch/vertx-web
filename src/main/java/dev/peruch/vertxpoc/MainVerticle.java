package dev.peruch.vertxpoc;

import dev.peruch.vertxpoc.api.ProductApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

import static dev.peruch.vertxpoc.mocks.Mocks.loadMocks;

public class MainVerticle extends AbstractVerticle {

  private ProductApi productApi = new ProductApi();

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    loadMocks();
    Router router = Router.router(vertx);
    router = productApi.buildApi(router);

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
}
