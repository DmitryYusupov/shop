package ru.yusdm.shop.common.utils;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.ZoneOffset.UTC;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import javax.validation.constraints.NotNull;

public final class TimeUtils {

  private TimeUtils() {

  }

  public static final ZoneId DEF_ZONE = UTC;
  public static final Locale DEF_LOCALE = Locale.US;

  @NotNull
  public static ZonedDateTime now() {
    return ZonedDateTime.now(DEF_ZONE);
  }

  public static ZonedDateTime startDayForToday() {
    return startDay(now().toLocalDate());
  }

  private static ZonedDateTime startDay(LocalDate date) {
    return date.atStartOfDay(DEF_ZONE);
  }

  public static ZonedDateTime zonedDateTime(long epochMilli) {
    return epochMilli > 0 ? zonedDateTime(Instant.ofEpochMilli(epochMilli)) : null;
  }

  public static ZonedDateTime zonedDateTime(Date date) {
    return Optional.ofNullable(date)
        .map(d -> d instanceof java.sql.Date ? startDay(((java.sql.Date)d).toLocalDate()) : zonedDateTime(d.toInstant()))
        .orElse(null);
  }

  public static ZonedDateTime zonedDateTime(Timestamp timestamp) {
    return Optional.ofNullable(timestamp).map(ts -> zonedDateTime(ts.toInstant())).orElse(null);
  }

  public static ZonedDateTime zonedDateTimeAsLocalDate(Timestamp timestamp) {
    return timestamp.toLocalDateTime().atZone(UTC);
  }


  public static ZonedDateTime zonedDateTime(OffsetDateTime offsetDateTime) {
    return Optional.ofNullable(offsetDateTime).map(dt -> zonedDateTime(dt.toInstant())).orElse(null);
  }

  private static ZonedDateTime zonedDateTime(Instant instant) {
    return Optional.ofNullable(instant).map(i -> ZonedDateTime.ofInstant(i, DEF_ZONE)).orElse(null);
  }

  public static ZonedDateTime zonedDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
    return ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, DEF_ZONE);
  }

  public static String format(long epochMilli, @NotNull String pattern) throws DateTimeParseException {
    return format(epochMilli > 0 ? new Date(epochMilli) : null, pattern);
  }

  public static String format(ZonedDateTime dateTime, @NotNull String pattern) throws DateTimeParseException {
    return Optional.ofNullable(dateTime).map(dt -> format(Date.from(dt.toInstant()), pattern)).orElse(null);
  }

  public static String format(Date date, @NotNull String pattern) throws DateTimeParseException {
    return Optional.ofNullable(date).map(d -> dateFormat(pattern).format(d)).orElse(null);
  }

  public static Date parseDate(String str, @NotNull String pattern) throws DateTimeParseException {
    try {
      return StringUtils.isBlank(str) ? null : dateFormat(pattern).parse(str);
    } catch (ParseException e) {
      throw new DateTimeParseException(e.getMessage(), str, e.getErrorOffset(), e);
    }
  }

  public static ZonedDateTime parseZonedDateTime(String str, @NotNull String pattern) throws DateTimeParseException {
    return Optional.ofNullable(parseDate(str, pattern)).map(d -> ZonedDateTime.ofInstant(d.toInstant(), DEF_ZONE)).orElse(null);
  }

  public static ZonedDateTime parseZonedDate(String date, @NotNull String pattern) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)).atStartOfDay(DEF_ZONE);
  }

  private static DateFormat dateFormat(String pattern) throws DateTimeParseException {
    if (StringUtils.isBlank(pattern)) {
      throw new DateTimeParseException("Illegal pattern", StringUtils.trimToEmpty(pattern), -1);
    }

    try {
      DateFormat dateFormat = new SimpleDateFormat(pattern, DEF_LOCALE);
      dateFormat.setTimeZone(TimeZone.getTimeZone(DEF_ZONE));
      return dateFormat;
    } catch (Exception e) {
      throw new DateTimeParseException(e.getMessage(), StringUtils.trimToEmpty(pattern), -1, e);
    }
  }

  public static long daysBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.DAYS);
  }

  public static long monthsBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.MONTHS);
  }
  public static ZonedDateTime truncatedToMonths(ZonedDateTime dateTimeFrom) {
    return dateTimeFrom.truncatedTo(ChronoUnit.DAYS).with(ChronoField.DAY_OF_MONTH, 1);
  }

  public static long hoursBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.HOURS);
  }

  public static long minutesBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.MINUTES);
  }

  public static long secondsBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.SECONDS);
  }

  public static long millisBetween(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
    return difference(dateTimeFrom, dateTimeTo, ChronoUnit.MILLIS);
  }

  private static long difference(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo, ChronoUnit unit) {
    return unit.between(dateTimeFrom, dateTimeTo);
  }

  public static boolean areDateOnlyEquals(ZonedDateTime first, ZonedDateTime second) {
    if (first != null && second != null) {
      return ChronoUnit.DAYS.between(first, second) == 0;
    }
    return false;
  }

  public static boolean isInFirstFullWeek(ZonedDateTime dateTime) {
    WeekFields wf = WeekFields.of(SUNDAY, 7); // first full week starting with Sunday
    if (dateTime.get(wf.weekOfMonth()) == 1) {
      return true;
    }
    return false;
  }

  public static ZonedDateTime fromYear(int year) {
    return now().withYear(year);
  }

  public static ZonedDateTime fromMonth(YearMonth month) {
    return Objects.requireNonNull(month).atDay(1).atStartOfDay(DEF_ZONE);
  }

  public static ZonedDateTime truncatedToDays(ZonedDateTime zonedDateTime) {
    return Optional.ofNullable(zonedDateTime)
        .map(date -> date.truncatedTo(ChronoUnit.DAYS))
        .orElse(null);
  }

  public static boolean equalsUpToDays(ZonedDateTime firstDate, ZonedDateTime secondDate) {
    return Objects.equals(truncatedToDays(firstDate), truncatedToDays(secondDate));
  }
}
