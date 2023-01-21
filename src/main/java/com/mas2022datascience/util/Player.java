package com.mas2022datascience.util;

import com.mas2022datascience.avro.v1.TracabGen5TF01Metadata;
import com.mas2022datascience.avro.v1.TracabGen5TF01Player;
import java.util.Optional;

public class Player {

  public static String getPlayerIdJerseyNumber(String JerseyNumber,
      String homeAwayTeamId, TracabGen5TF01Metadata metadata) {
    Optional<TracabGen5TF01Player> result;
    if (homeAwayTeamId.equals(String.valueOf(metadata.getHomeTeam().getTeamID()))) {
      result = metadata.getHomeTeam().getPlayers().stream()
          .filter(player -> player.getJerseyNo() == Integer.parseInt(JerseyNumber)).findFirst();
    } else {
      result = metadata.getAwayTeam().getPlayers().stream()
          .filter(player -> player.getJerseyNo() == Integer.parseInt(JerseyNumber)).findFirst();
    }
    if (result.isPresent()) {
      return String.valueOf(result.get().getPlayerID());
    } else {
      return null;
    }
  }
}
