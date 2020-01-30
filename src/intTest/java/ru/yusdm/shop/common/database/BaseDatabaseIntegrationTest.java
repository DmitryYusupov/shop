package ru.yusdm.shop.common.database;


import ru.yusdm.shop.common.BaseIntegrationTest;

public class BaseDatabaseIntegrationTest extends BaseIntegrationTest {

  static final PostgresqlDockerContainer POSTGRESQL_DOCKER_CONTAINER;
  static {
    POSTGRESQL_DOCKER_CONTAINER = new PostgresqlDockerContainer();
    POSTGRESQL_DOCKER_CONTAINER.start();
  }

}
