package com.rntgroup.jpatask.dto;

public record SongStatDto(
  Long id,
  String songTitle,
  Integer duration,
  String albumName,
  String artistName,
  Long countPurchase
) {

}
