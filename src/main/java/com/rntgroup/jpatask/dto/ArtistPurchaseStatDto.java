package com.rntgroup.jpatask.dto;

public record ArtistPurchaseStatDto(
  Long artistId,
  String artistName,
  Integer totalSumPurchases,
  Integer countPurchases
) {

}
