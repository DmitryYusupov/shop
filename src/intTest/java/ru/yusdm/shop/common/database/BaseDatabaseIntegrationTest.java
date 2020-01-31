package ru.yusdm.shop.common.database;


import ru.yusdm.shop.common.BaseIntegrationTest;

public class BaseDatabaseIntegrationTest extends BaseIntegrationTest {

  static  PostgresqlDockerContainer POSTGRESQL_DOCKER_CONTAINER;

  static {
    try {
      POSTGRESQL_DOCKER_CONTAINER = new PostgresqlDockerContainer();
      POSTGRESQL_DOCKER_CONTAINER.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
