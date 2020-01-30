package ru.yusdm.shop.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class UuidConverter {

  private UuidConverter() {

  }

  public static final UUID[] EMPTY = new UUID[0];

  public static String from(UUID uuid) {
    return uuid.toString();
  }

  public static List<String> from(Collection<UUID> uuids) {
    return from(uuids, Collectors.toList());
  }

  public static Map<String, String> from(Map<UUID, UUID> uuids) {
    return Optional.ofNullable(uuids).orElse(Collections.emptyMap()).entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
  }

  public static <R> R from(Collection<UUID> uuids, Collector<String, ?, R> collector) {
    return Optional.ofNullable(uuids).orElse(Collections.emptyList()).stream()
        .map(UuidConverter::from)
        .collect(collector);
  }

  public static UUID to(String uuid) {
    return UUID.fromString(uuid);
  }


  public static Class<UUID> fromType() {
    return UUID.class;
  }

  public static Class<String> toType() {
    return String.class;
  }

  public static Collection<UUID> to(Collection<String> collection) {
    if (CollectionUtils.isEmpty(collection)) {
      return CollectionUtils.emptyCollection();
    }
    return collection.stream().map(UuidConverter::to).collect(Collectors.toList());
  }

  public static UUID[] toArray(Collection<String> userObjects) {
    if (CollectionUtils.isEmpty(userObjects)) {
      return EMPTY;
    }

    return userObjects.stream().map(UuidConverter::to).toArray(UUID[]::new);
  }
}
