package com.mas2022datascience.util;

import static com.mas2022datascience.util.Time.utcString2epocMs;

import com.mas2022datascience.avro.v1.GeneralMatchPhase;
import com.mas2022datascience.avro.v1.Object;
import com.mas2022datascience.avro.v1.TracabGen5TF01Metadata;
import java.time.Instant;

public class Team {

  /**
   * The method returns the team id out of the home/away team ID.
   * @param homeAwayTeamId of type Integer
   * @param metadata of type TracabGen5TF01Metadata
   * @return team id of type String
   */
  public static String getTeamIdFromHomeOrAwayId(Integer homeAwayTeamId,
      TracabGen5TF01Metadata metadata) {
    if (homeAwayTeamId == 0) {
      return String.valueOf(metadata.getHomeTeam().getTeamID());
    } else {
      if (homeAwayTeamId == 1) {
        return String.valueOf(metadata.getAwayTeam().getTeamID());
      } else {
        return null;
      }
    }
  }

  /**
   * The method returns the team id or "0" if it is the ball.
   * @param playerObject of type Object
   * @return team id or "0" (ball) of type String
   */
  public static String getTeamId(Object playerObject) {
    if (playerObject.getType() == 0 || playerObject.getType() == 1) {
      // Player
      return playerObject.getTeamId();
    } else {
      // Referee or ball
      return null;
    }
  }

  /**
   * The method returns the left team id depending on the time stamp
   * @param ts timer stamp of type Instant
   * @param phases of type GeneralMatchPhase
   * @return left team id of type Integer depending on the timestamp
   */
  public static Integer getLeftTeamByTimestamp(Instant ts, GeneralMatchPhase phases) {
    // in phase 1
    if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(0).getEnd())) {
      return phases.getPhases().get(0).getLeftTeamID();
    }
    // in phase 2
    else if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(1).getEnd())) {
      return phases.getPhases().get(1).getLeftTeamID();
    }
    // in phase 3
    else if(phases.getPhases().size() == 3) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(2).getEnd())) {
        return phases.getPhases().get(2).getLeftTeamID();
      }
    }
    // in phase 4
    else if(phases.getPhases().size() == 4) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(3).getEnd())) {
        return phases.getPhases().get(3).getLeftTeamID();
      }
    }
    // in phase 5
    else if(phases.getPhases().size() == 5) {
      if (ts.toEpochMilli() <= utcString2epocMs(phases.getPhases().get(4).getEnd())) {
        return phases.getPhases().get(4).getLeftTeamID();
      }
    }
    return -1;
  }

  /**
   * checks which team (majority of the players) is on the left pitch side
   * @param playerList of chunk 2 of type String[]
   * @return TeamID of the left team of type Integer
   */
  public static Integer getLeftTeamID(String[] playerList, TracabGen5TF01Metadata metadata) {

    int homeTeamCount = 0;
    for (String player : playerList) {
      String[] playerSplit = player.split(",");
      // Valid values: 1=Hometeam, 0=Awayteam, 3=Referee. Other values are used for internal purposes.
      if (playerSplit[0].equals("1") &&
          Player.isPlayerOnLeftPitchSide(Integer.parseInt(playerSplit[3]),
              Integer.parseInt(playerSplit[4]), metadata)) {
        homeTeamCount++;
      }
    }

    // if home team has more than 8 players on the left pitch side, then home team is the left team
    if (homeTeamCount > 8) {
      return metadata.getHomeTeam().getTeamID();
    } else {
      return metadata.getAwayTeam().getTeamID();
    }
  }
}
