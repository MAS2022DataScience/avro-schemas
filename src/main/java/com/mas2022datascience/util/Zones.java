package com.mas2022datascience.util;

import static com.mas2022datascience.util.Time.utcString2epocMs;

import com.mas2022datascience.avro.v1.PlayerBall;
import java.time.Instant;
import com.mas2022datascience.avro.v1.GeneralMatchPhase;

public class Zones {

  /**
   * Returns the zone of the given x and y coordinates depending on the team.
   * @param x of type int
   * @param y of type int
   * @param ts of type Instant
   * @param HomeAwayTeamId of type Integer
   * @param phases of type GeneralMatchPhase
   * @return zone of the away or the home team of type int
   */
  public static int getZone(Integer x, Integer y, Instant ts, String HomeAwayTeamId,
      GeneralMatchPhase phases) {

    switch (phases.getPhases().size()) {
      case 5:
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
      case 4:
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
      case 3:
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
      case 2:
        if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(1).getEnd())) {
          if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(1).getLeftTeamID()))) {
            // left team
            if (inZone1(x, y, "left")) {
              return 1;
            }
            if (inZone3(x, y, "left")) {
              return 3;
            }
          } else {
            // right team
            if (inZone1(x, y, "right")) {
              return 1;
            }
            if (inZone3(x, y, "right")) {
              return 3;
            }
          }
          if (inZone2(x, y)) {
            return 2;
          }
        }
      case 1:
        if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(0).getEnd())) {
          if (HomeAwayTeamId.equals(String.valueOf(phases.getPhases().get(0).getLeftTeamID()))) {
            // left team
            if (inZone1(x, y, "left")) {
              return 1;
            }
            if (inZone3(x, y, "left")) {
              return 3;
            }
          } else {
            // right team
            if (inZone1(x, y, "right")) {
              return 1;
            }
            if (inZone3(x, y, "right")) {
              return 3;
            }
          }
          if (inZone2(x, y)) {
            return 2;
          }
        }
      default:
        return -1;
    }
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

  /**
   * returns true if the player is in the same zone otherwise false
   * @param oldPlayerBall of type PlayerBall
   * @param newPlayerBall of type PlayerBall
   * @return true if the player is in the same zone otherwise false
   */
  public static boolean isInSameZone(PlayerBall oldPlayerBall, PlayerBall newPlayerBall) {
    if (inZone1(newPlayerBall.getX(), newPlayerBall.getY(), "left")
        && inZone1(oldPlayerBall.getX(), oldPlayerBall.getY(), "left")) {
      return true;
    }
    if (inZone2(newPlayerBall.getX(), newPlayerBall.getY())
        && inZone2(oldPlayerBall.getX(), oldPlayerBall.getY())) {
      return true;
    }
    return inZone3(newPlayerBall.getX(), newPlayerBall.getY(), "left")
        && inZone3(oldPlayerBall.getX(), oldPlayerBall.getY(), "left");
  }

  /**
   * returns the zone of the player under the estimation to be the left team
   * @param x of type Integer
   * @param y of type Integer
   * @return the zone of the player
   */
  public static int getZoneLeft(Integer x, Integer y) {
    if (inZone1(x, y,"left")) {
      return 1;
    }
    if (inZone2(x, y)) {
      return 2;
    }
    if (inZone3(x, y,"left")) {
      return 3;
    } else {
      return -1;
    }
  }

  /**
   * returns the inverted zone numbering
   * @param zone of type Integer
   * @return the inverted zone numbering
   */
  public static int invertZoneNumbering(int zone) {
    if (zone == 1) {
      return 3;
    }
    if (zone == 2) {
      return 2;
    }
    if (zone == 3) {
      return 1;
    } else {
      return -1;
    }
  }

}