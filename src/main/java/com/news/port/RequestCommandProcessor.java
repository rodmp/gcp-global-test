package com.news.port;

@FunctionalInterface
public interface RequestCommandProcessor<R, T> {

  R execute(T command);
}
