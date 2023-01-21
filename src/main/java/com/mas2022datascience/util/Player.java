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

    switch (homeAwayTeamId) {
      case "0":
        result = metadata.getHomeTeam().getPlayers().stream()
            .filter(player -> player.getJerseyNo() == Integer.parseInt(JerseyNumber)).findFirst();
        break;
      case "1":
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

}
