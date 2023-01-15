package com.mas2022datascience.util;

import static com.mas2022datascience.util.Time.utcString2epocMs;

import java.time.Instant;
import com.mas2022datascience.avro.v1.GeneralMatchPhase;

public class Zones {

  /**
   * Returns the zone of the given x and y coordinates depending on the team.
   * @param x of type int
   * @param y of type int
   * @param ts of type Instant
   * @param HomeAwayTeamId of type Integer
   * @return zone of type int
   */
  public static int getZone(Integer x, Integer y, Instant ts, String HomeAwayTeamId,
      GeneralMatchPhase phases) {
    // in phase 1
    if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(0).getEnd())) {
      if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(0).getLeftTeamID()))) {
        // left team
        if (inZone1(x, y,"left")) {
          return 1;
        }
        if (inZone3(x, y,"left")) {
          return 3;
        }
      } else {
        // right team
        if (inZone1(x, y,"right")) {
          return 1;
        }
        if (inZone3(x, y,"right")) {
          return 3;
        }
      }
      if (inZone2(x, y)) {
        return 2;
      }
    }
    // in phase 2
    else if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(1).getEnd())) {
      if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(1).getLeftTeamID()))) {
        // left team
        if (inZone1(x, y,"left")) {
          return 1;
        }
        if (inZone3(x, y,"left")) {
          return 3;
        }
      } else {
        // right team
        if (inZone1(x, y,"right")) {
          return 1;
        }
        if (inZone3(x, y,"right")) {
          return 3;
        }
      }
      if (inZone2(x, y)) {
        return 2;
      }
    }
    // in phase 3
    else if(phases.getPhases().size() == 3) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(2).getEnd())) {
        if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(2).getLeftTeamID()))) {
          // left team
          if (inZone1(x, y,"left")) {
            return 1;
          }
          if (inZone3(x, y,"left")) {
            return 3;
          }
        } else {
          // right team
          if (inZone1(x, y,"right")) {
            return 1;
          }
          if (inZone3(x, y,"right")) {
            return 3;
          }
        }
        if (inZone2(x, y)) {
          return 2;
        }
      }
    }
    // in phase 4
    else if(phases.getPhases().size() == 4) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(3).getEnd())) {
        if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(3).getLeftTeamID()))) {
          // left team
          if (inZone1(x, y,"left")) {
            return 1;
          }
          if (inZone3(x, y,"left")) {
            return 3;
          }
        } else {
          // right team
          if (inZone1(x, y,"right")) {
            return 1;
          }
          if (inZone3(x, y,"right")) {
            return 3;
          }
        }
        if (inZone2(x, y)) {
          return 2;
        }
      }
    }
    // in phase 5
    else if(phases.getPhases().size() == 5) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(4).getEnd())) {
        if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(4).getLeftTeamID()))) {
          // left team
          if (inZone1(x, y,"left")) {
            return 1;
          }
          if (inZone3(x, y,"left")) {
            return 3;
          }
        } else {
          // right team
          if (inZone1(x, y,"right")) {
            return 1;
          }
          if (inZone3(x, y,"right")) {
            return 3;
          }
        }
        if (inZone2(x, y)) {
          return 2;
        }
      }
    }
    return -1;
  }
//  Zone1
//10500 / 2 = 5250 - 1500 = 3750
//  x = -5250 bis -3750
//      6800 / 2 = 3400
//  y = -3400 bis 3400
  public static boolean inZone1(Integer x, Integer y, String leftRight) {
    if (leftRight.equals("left")) {
      return x >= -5250 && x <= -1500 && y >= -3400 && y <= 3400;
    } else {
      return x >= 1500 && x <= 5250 && y >= -3400 && y <= 3400;
    }
  }

//  Zone 2
//      10500 / 2 = 5250 - 1500 = 3750
//  x = -1500 bis 1500
//      6800 / 2 = 3400
//  y = -3400 bis 3400
  public static boolean inZone2(Integer x, Integer y) {
      return x >= -1500 && x <= 1500 && y >= -3400 && y <= 3400;
  }

//  Zone 3
//      10500 / 2 = 5250 - 1500 = 3750
//  x = 5250 bis 3750
//      6800 / 2 = 3400
//  y = -3400 bis 3400
  public static boolean inZone3(Integer x, Integer y, String leftRight) {
    if (leftRight.equals("left")) {
      return x >= 1500 && x <= 5250 && y >= -3400 && y <= 3400;
    } else {
      return x >= -5250 && x <= -1500 && y >= -3400 && y <= 3400;
    }
  }

}