package ru.yusdm.shop.common.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public final class OdtConverter {

  private OdtConverter(){

  }

  public static OffsetDateTime from(Date date) {
    return TimeUtils.zonedDateTime(date).toOffsetDateTime();
  }

  public static ZonedDateTime from(OffsetDateTime databaseObject) {
    return Optional.ofNullable(databaseObject).map(TimeUtils::zonedDateTime).orElse(null);
  }

  public static OffsetDateTime to(ZonedDateTime userObject) {
    return Optional.ofNullable(userObject)
        .map(o -> OffsetDateTime.ofInstant(o.toInstant(), ZoneOffset.UTC))
        .orElse(null);
  }

  public static Class<OffsetDateTime> fromType() {
    return OffsetDateTime.class;
  }

  public static Class<ZonedDateTime> toType() {
    return ZonedDateTime.class;
  }


  public static OffsetDateTime empty() {
    return null;
  }
}
