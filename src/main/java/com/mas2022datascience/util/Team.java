package com.mas2022datascience.util;

import com.mas2022datascience.avro.v1.Object;
import com.mas2022datascience.avro.v1.TracabGen5TF01Metadata;

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
