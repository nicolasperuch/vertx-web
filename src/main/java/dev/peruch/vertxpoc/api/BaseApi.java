package dev.peruch.vertxpoc.api;

abstract class BaseApi {
  final String NOT_FOUND_MESSAGE = "product not found!";
  final String BAD_REQUEST_MESSAGE = "id must not be null";
  final int NOT_FOUND = 404;
  final int BAD_REQUEST = 400;
}
