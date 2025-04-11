package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.entity.Album;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {

  List<Album> findAllByTitle(String title);

  @Modifying
  @Query("update Album a set a.description = :newDescription where a.id = :id")
  void updateDescriptionById(long id, String newDescription);

  @Query("select a from Album a where a.artist.id = :authorId")
  List<Album> findAllByArtistId(long authorId);
}
