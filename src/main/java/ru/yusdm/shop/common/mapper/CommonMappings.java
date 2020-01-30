package ru.yusdm.shop.common.mapper;

import ru.yusdm.shop.common.utils.OdtConverter;
import ru.yusdm.shop.common.utils.UuidConverter;
import ru.yusdm.shop.common.utils.ZdtConverter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

public interface CommonMappings {

  String DELIMETERS = ";";

  default Timestamp zonedDateTimeToTimestamp(ZonedDateTime zonedDateTime) {
    return ZdtConverter.to(zonedDateTime);
  }

  default ZonedDateTime timestampToZonedDateTime(Timestamp timestamp) {
    return ZdtConverter.from(timestamp);
  }

  default ZonedDateTime zonedDateTimeToOffset(OffsetDateTime offsetDateTime) {
    return OdtConverter.from(offsetDateTime);
  }

  default OffsetDateTime offsetToZonedDateTime(ZonedDateTime zonedDateTime) {
    return OdtConverter.to(zonedDateTime);
  }

  Set<String> uuidSetToString(Set<UUID> source);

  default String uuidToString(UUID uuid) {
    return UuidConverter.from(uuid);
  }

  default UUID stringToUUID(String string) {
    return UuidConverter.to(string);
  }


  default String setToString(Set<String> set) {
    if (set == null) {
      return null;
    }
    if (set.isEmpty()) {
      return "";
    }
    return String.join(DELIMETERS, set);
  }


}
