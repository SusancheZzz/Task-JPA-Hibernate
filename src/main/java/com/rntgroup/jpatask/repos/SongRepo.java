package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.dto.SongStatDto;
import com.rntgroup.jpatask.entity.Song;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {

  @Modifying
  @Query("update Song s set s.title = :newTitle where s.id = :songId")
  void updateTitleById(Long songId, String newTitle);

  @EntityGraph(attributePaths = {"purchases", "purchases.song"})
  List<Song> findAllByTitle(String title);

  @Query("select s from Song s where s.artist.id = :artistName")
  List<Song> findAllByAuthorName(@Param("artistName") String artistName);

  @Query(name = "Song.findPopularSongsOfArtistBuPurchase")
  List<SongStatDto> findAllSongOfArtistByCountPurchase(
    @Param("artistName") String artistName,
    @Param("minCountPurchase") int minCountPurchase
  );
}
