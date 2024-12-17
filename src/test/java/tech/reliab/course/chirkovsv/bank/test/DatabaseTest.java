package tech.reliab.course.chirkovsv.bank.test;

import tech.reliab.course.chirkovsv.bank.container.PostgresTestContainer;

import tech.reliab.course.chirkovsv.bank.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest extends PostgresTestContainer {
  private static final String SCHEMA_NAME = "public";
  private static final String TABLE_NAME = "bank";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testDatabaseConnection() {
    assertNotNull(userRepository);
  }

  @Test
  public void testTableExists() {
    String query =
        "select exists(select 1 " +
            "            from information_schema.tables " +
            "           where table_schema = ?" +
            "             and table_name = ?);";

    Boolean doesTableExist = jdbcTemplate.queryForObject(
        query,
        Boolean.class,
        SCHEMA_NAME,
        TABLE_NAME
    );

    assertNotNull(doesTableExist, "ошибка: не удалось получить данные о таблице 'users'");
    assertTrue(doesTableExist, "таблица 'users' не существует в БД");
  }

  @Test
  public void testTableColumns() {
    testTableExists();

    String query =
        "select exists(select 1 " +
            "            from information_schema.columns" +
            "           where table_schema = ?" +
            "             and table_name = ?" +
            "         ); ";

    List<String> columnsNames = jdbcTemplate.queryForList(
        query,
        String.class,
        SCHEMA_NAME,
        TABLE_NAME
    );

    List<String> userFields = Arrays.asList(
        "id", "fullName", "birthDate", "workplace", "monthlyIncome", "creditRating",
        "banks", "creditAccounts", "paymentAccounts"
    );

    assertThat(columnsNames).containsAll(userFields);
  }
}
