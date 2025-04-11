package com.rntgroup.jpatask.dto;

public record UserStatDto(
  Long userId,
  String userName,
  Long totalSongs,
  Long totalSum
) {

}
