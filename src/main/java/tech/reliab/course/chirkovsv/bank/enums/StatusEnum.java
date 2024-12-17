package tech.reliab.course.chirkovsv.bank.enums;

import java.util.Random;

public interface StatusEnum {
  static <T extends Enum<?>> T getRandomStatus(Class<T> enumClass) {
    T[] values = enumClass.getEnumConstants();

    return values[new Random().nextInt(values.length)];
  }
}