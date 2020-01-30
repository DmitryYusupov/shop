package ru.yusdm.shop.common.utils;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public final class ZdtConverter {

  private ZdtConverter() {

  }

  public static ZonedDateTime from(Date date) {
    return TimeUtils.zonedDateTime(date);
  }

  public static ZonedDateTime from(Timestamp databaseObject) {
    return TimeUtils.zonedDateTime(databaseObject);
  }

  public static Timestamp to(ZonedDateTime userObject) {
    return Optional.ofNullable(userObject)
        .map(o -> Timestamp.from(o.toInstant()))
        .orElse(null);
  }

  public static Class<Timestamp> fromType() {
    return Timestamp.class;
  }

  public static Class<ZonedDateTime> toType() {
    return ZonedDateTime.class;
  }


  public Timestamp empty() {
    return null;
  }
}
