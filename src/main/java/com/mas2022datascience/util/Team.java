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
}
