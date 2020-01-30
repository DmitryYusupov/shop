package ru.yusdm.shop.common.database;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PostgresqlDockerContainer extends PostgreSQLContainer<PostgresqlDockerContainer> {

  private static final String DEFAULT_TAG = "9.6.12";

  public PostgresqlDockerContainer() {
    super(IMAGE + ":" + DEFAULT_TAG);
  }

  @Override
  public void start() {
    super.start();
    setSysProperties();
    createSchema();
  }

  private void setSysProperties() {
    Map<String, String> props = new HashMap<>();
    props.put("DB_URL", this.getJdbcUrl());
    props.put("DB_USERNAME", this.getUsername());
    props.put("DB_PASSWORD", this.getPassword());

    props.forEach(System::setProperty);
  }

  private void createSchema() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(this.getJdbcUrl());
    hikariConfig.setUsername(this.getUsername());
    hikariConfig.setPassword(this.getPassword());

    HikariDataSource ds = null;
    PreparedStatement preparedStatement = null;
    try {
      ds = new HikariDataSource(hikariConfig);
      preparedStatement = ds.getConnection()
          .prepareStatement("CREATE SCHEMA shop;");
      int i = preparedStatement.executeUpdate();
      System.out.println(i);
    } catch (Exception e) {
      throw new RuntimeException("Error while create schema");
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (ds != null) {
        ds.close();
      }
    }
  }

  @Override
  public void stop() {

  }


}
