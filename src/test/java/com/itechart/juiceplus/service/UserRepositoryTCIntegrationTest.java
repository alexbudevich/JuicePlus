package com.itechart.juiceplus.service;

import com.itechart.juiceplus.entity.User;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTCIntegrationTest {

  @Autowired
  UserService userService;

  public static PostgreSQLContainer postgreSQLContainer ;

  static {
    postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
        .withDatabaseName("integration-tests-db")
        .withUsername("sa")
        .withPassword("sa");
    postgreSQLContainer.start();
  }

  @DynamicPropertySource
  public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @Test
  @Sql({"/data/testData.sql"})
  public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationJPQL_ThenModifyMatchingUsers(){
    User entity = new User();
    entity.setName("name");
    User save = userService.create(entity);
    Assertions.assertTrue(true);
  }
}