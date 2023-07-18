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

  /**
   * calculates the time difference in seconds
   * @param actualUtc UTC time as a String
   * @param oldUtc UTC time as a String
   * @return time difference in seconds
   */
  public static float getTimeDifference(String actualUtc, String oldUtc) {
    // represents the divisor that is needed to get s. Ex. ms to s means 1000 as 1000ms is 1s
    float timeUnitDivisor = 1000;
    return (Time.utcString2epocMs(actualUtc) - Time.utcString2epocMs(oldUtc))/timeUnitDivisor;
  }

}
