package com.mas2022datascience.util;

import com.mas2022datascience.avro.v1.TracabGen5TF01Metadata;

public class Team {

  public static String getTeamIdFromHomeOrAwayId(Integer homeAwayTeamId,
      TracabGen5TF01Metadata metadata) {
    if (homeAwayTeamId == 0) {
      return String.valueOf(metadata.getHomeTeam().getTeamID());
    } else {
      return String.valueOf(metadata.getAwayTeam().getTeamID());
    }
  }
}
