package com.rntgroup.jpatask.dto;

import java.util.List;

public record AlbumStatDto(
  Long albumId,
  String albumName,
  String artistName,
  String description,
  List<String> songNames
) {

}
