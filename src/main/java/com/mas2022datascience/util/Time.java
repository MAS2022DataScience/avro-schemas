package com.mas2022datascience.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Time {

  /**
   * Converts the utc string of type "yyyy-MM-dd'T'HH:mm:ss.SSS" to epoc time in milliseconds.
   * @param utcString of type String of format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
   * @return epoc time in milliseconds
   */
  public static long utcString2epocMs(String utcString) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneOffset.UTC);

    return Instant.from(fmt.parse(utcString)).toEpochMilli();
  }

  /**
   * Calculates the UTC String out of the offset value and the initial time
   * @param offset
   * @param frameRate
   * @param initialFrameNumber
   * @param initialTime
   * @return UTC String
   */
  public static String getUTCStringFromOffsetValue(long offset , long frameRate, long initialFrameNumber,
      String initialTime) {
    if (offset == 0) {
      return null;
    } else {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

      return Instant.ofEpochMilli(Instant.parse(initialTime).toEpochMilli() +
          (offset - initialFrameNumber) *
              (1000 / frameRate)).atZone(ZoneOffset.UTC).format(formatter).toString();
    }
  }

}
