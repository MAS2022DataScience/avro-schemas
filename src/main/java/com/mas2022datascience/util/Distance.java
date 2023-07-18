package com.mas2022datascience.util;

import static com.mas2022datascience.util.Time.getTimeDifference;
import com.mas2022datascience.avro.v1.Object;
import java.util.Optional;
import java.util.OptionalDouble;

public class Distance {

  // represents the divisor that is needed to get m. Ex. cm to m means 100 as 100cm is 1m
  private static final int distanceUnitDivisor = 100;

   /**
   * calculates the euclidian distance [m] between two points in a 3 dimensional space
   *
   * @param actualObject  of type Object
   *      <Obj type="7" id="0" x="4111" y="2942" z="11" sampling="0" />
   * @param actualUtc UTC time as a String
   * @param oldObject     of type Object
   *      <Obj type="7" id="0" x="4111" y="2942" z="11" sampling="0" />
   * @param oldUtc UTC time as a String
   * @return distance     of type double in meters
   *                      between 2 points in a 3 dimensional space.
   */
  public static Optional<Double> getEuclidianDistance(Object actualObject, String actualUtc,
      Object oldObject, String oldUtc) {

    // v_max= velocity world record holder Arjen Robben with 37 km/h (10.277777778m/s)
    // http://www.deinfussballtrainer.de/top-10-die-schnellsten-fussballer-der-welt/
    final double VELOCITY_MAXIMAL_VALUE_STATIC = 10.277777778;

    double euclidianDistance = Math.sqrt(
        Math.pow(oldObject.getX()-actualObject.getX(), 2)
            + Math.pow(oldObject.getY()-actualObject.getY(), 2)
            + Math.pow(oldObject.getZ()-actualObject.getZ(), 2)
    ) / distanceUnitDivisor;
    double timeDifference = getTimeDifference(actualUtc, oldUtc);

    // if the distance is higher than the 37km/h, then an empty optional is returned
    // v_max= velocity world record holder Arjen Robben mit 37 km/h (10.277777778m/s)
    // s_max=v_max*timedifference=10,2m/s∗0.04s= 0.408m = 40.8cm
    if (euclidianDistance < (VELOCITY_MAXIMAL_VALUE_STATIC * timeDifference)) {
      return Optional.of(euclidianDistance);
    } else {
      // except the ball
      if (actualObject.getType() == 7) {
        return Optional.of(euclidianDistance);
      } else {
        return Optional.empty();
      }
    }
  }

  /**
   * calculates the euclidian distance [m] between two points in a 3 dimensional space
   *
   * @param firstObject  of type Object
   *      <Obj type="7" id="0" x="4111" y="2942" z="11" sampling="0" />
   * @param secondObject     of type Object
   *      <Obj type="7" id="0" x="4111" y="2942" z="11" sampling="0" />
   * @return distance     of type double in meters between 2 points
   *                      in a 3 dimensional space.
   *                      It is always a positive number.
   */
  public static Double getEuclidianDistanceNoChecks(Object firstObject, Object secondObject) {

    return Math.abs(Math.sqrt(
        Math.pow(secondObject.getX()-firstObject.getX(), 2)
            + Math.pow(secondObject.getY()-firstObject.getY(), 2)
            + Math.pow(secondObject.getZ()-firstObject.getZ(), 2)
    ) / distanceUnitDivisor);
  }

}
