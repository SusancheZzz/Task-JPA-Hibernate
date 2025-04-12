package com.rntgroup.jpatask.proj;

import java.util.List;

public record AlbumStatProj(
  Long albumId,
  String albumName,
  String artistName,
  String description,
  List<String> songNames
) {

}
