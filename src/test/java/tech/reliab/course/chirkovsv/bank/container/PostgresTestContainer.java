package tech.reliab.course.chirkovsv.bank.container;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import tech.reliab.course.chirkovsv.bank.ChirkovSVLabApplication;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.testcontainers.containers.PostgreSQLContainer;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;

import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(classes = ChirkovSVLabApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresTestContainer {
  @Container
  public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
      DockerImageName.parse("postgres:latest")
  )
      .withDatabaseName("online_bank")
      .withUsername("admin")
      .withPassword("11111111");

  static {
    postgresContainer.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
    registry.add("spring.datasource.driver-class-name", postgresContainer::getDriverClassName);
  }
}