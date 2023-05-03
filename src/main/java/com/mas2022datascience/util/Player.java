package com.mas2022datascience.util;

import com.mas2022datascience.avro.v1.Object;
import com.mas2022datascience.avro.v1.TracabGen5TF01Metadata;
import com.mas2022datascience.avro.v1.TracabGen5TF01Player;
import java.util.Optional;

public class Player {

  /**
   * The method returns the player out of the home team and the jersey number.
   * @param JerseyNumber of type String
   * @param homeAwayTeamId of type String
   * @param metadata of type TracabGen5TF01Metadata
   * @return player id or null (Optional of type String)
   */
  public static String getPlayerIdJerseyNumber(String JerseyNumber,
      String homeAwayTeamId, TracabGen5TF01Metadata metadata) {
    Optional<TracabGen5TF01Player> result = null;
    // 1=Hometeam, 0=Awayteam, 3=Referee
    switch (homeAwayTeamId) {
      case "1":
        result = metadata.getHomeTeam().getPlayers().stream()
            .filter(player -> player.getJerseyNo() == Integer.parseInt(JerseyNumber)).findFirst();
        break;
      case "0":
        result = metadata.getAwayTeam().getPlayers().stream()
          .filter(player -> player.getJerseyNo() == Integer.parseInt(JerseyNumber)).findFirst();
        break;
      default:
        result = Optional.empty();
    }

    if (result.isPresent()) {
      return String.valueOf(result.get().getPlayerID());
    } else {
      return null;
    }
  }

  /**
   * The method returns the player id or the ball id (0)
   * @param playerObject
   * @return
   */
  public static String getPlayerOrBallId(Object playerObject) {
    if (playerObject.getType() == 0 || playerObject.getType() == 1) {
      // Player
      return playerObject.getPlayerId();
    } else {
      // Referee
      return String.valueOf(playerObject.getType());
    }
  }

  /**
   * checks if the player x, y coordinates are on the left pitch side
   * @param x coordinate of the player of type Integer
   * @param y coordinate of the player of type Integer
   * @param metadata of the match of type TracabGen5TF01Metadata
   * @return true if the player is on the left pitch side, false otherwise
   */
  public static boolean isPlayerOnLeftPitchSide (int x, int y, TracabGen5TF01Metadata metadata) {
    int pitchMaxYSize = metadata.getPitchShortSide() / 2;
    int pitchMaxXSize = metadata.getPitchLongSide() / 2;

    if (x <= 0 && x >= -pitchMaxXSize && y <= pitchMaxYSize && y >= -pitchMaxYSize) {
      return true;
    } else {
      return false;
    }
  }

}
