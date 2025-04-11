package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.entity.Album;
import com.rntgroup.jpatask.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlbumCriteriaDao {

  private final EntityManager entityManager;

  public List<Album> findAlbumsWithMoreThanXsongs(int minSongs) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Album> cq = cb.createQuery(Album.class);
    Root<Album> album = cq.from(Album.class);

    Join<Album, Song> songs = album.join("songs");
    cq.groupBy(album.get("id"));
    cq.having(cb.gt(cb.count(songs), minSongs - 1));
    cq.select(album).orderBy(cb.desc(cb.count(songs)));

    return entityManager.createQuery(cq).getResultList();
  }
}
