package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.entity.Artist;
import com.rntgroup.jpatask.proj.ArtistPurchaseStatProj;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {

  @Query("select a from Artist a where a.name = :name")
  Optional<Artist> findArtistByName(@Param("name") String name);

  @Modifying
  @Query("update Artist a set a.name = :newName where a.id = :artistId")
  void updateNameById(@Param("artistId") long artistId, @Param("newName") String newName);

  @Query(
    nativeQuery = true,
    value = """
      select
        a.id as artistId,
        a.name as artistName,
        sum(p.price) as totalSum,
        count(p.id) as totalPurchases
      from artist as a
        join song as s on a.id = s.artist_id
        join purchase as p on s.id = p.song_id
      group by a.id, a.name
      having sum(p.price) >= :minSumPurchases
      order by sum(p.price) desc
      """
  )
  List<ArtistPurchaseStatProj> findRichArtistsBySumPurchases(
    @Param("minSumPurchases") int minSumPurchases);
}
