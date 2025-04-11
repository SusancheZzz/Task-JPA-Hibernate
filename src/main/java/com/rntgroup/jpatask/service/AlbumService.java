package com.rntgroup.jpatask.service;

import com.rntgroup.jpatask.config.AppDataConfig;
import com.rntgroup.jpatask.dto.AlbumStatDto;
import com.rntgroup.jpatask.entity.Album;
import com.rntgroup.jpatask.entity.Artist;
import com.rntgroup.jpatask.entity.Song;
import com.rntgroup.jpatask.repos.AlbumCriteriaDao;
import com.rntgroup.jpatask.repos.AlbumRepo;
import com.rntgroup.jpatask.repos.ArtistRepo;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@DependsOn("artistService")
@Transactional(timeout = 30)
public class AlbumService {

  private final AlbumRepo albumRepo;
  private final AlbumCriteriaDao albumCriteriaDao;
  private final ArtistRepo artistRepo;
  private final AppDataConfig appDataConfig;
  private final Faker faker = new Faker();
  private static final Random RANDOM = new Random();

  public Album createAlbum(Album album) {
    return albumRepo.save(album);
  }

  @Transactional(readOnly = true)
  public List<Album> getAllAlbumsByTitle(String title) {
    return albumRepo.findAllByTitle(title);
  }

  @Transactional(readOnly = true)
  public List<Album> getAllAlbumsByAuthorId(long authorId) {
    return albumRepo.findAllByArtistId(authorId);
  }

  @Transactional(readOnly = true)
  public List<AlbumStatDto> getAlbumsWithMoreThanXsongs(int minAcceptCountSongs) {
    return albumCriteriaDao.findAlbumsWithMoreThanXsongs(minAcceptCountSongs).stream()
      .map(album -> new AlbumStatDto(
        album.getId(),
        album.getTitle(),
        album.getArtist().getName(),
        album.getDescription(),
        album.getSongs().stream()
          .map(Song::getTitle)
          .toList()
        )
      )
      .toList();
  }

  public void updateDescription(long albumId, String newDescription) {
    albumRepo.updateDescriptionById(albumId, newDescription);
  }

  public void deleteById(long albumId) {
    albumRepo.deleteById(albumId);
  }

  @PostConstruct
  private void init() {
    List<Album> albums = new ArrayList<>();
    List<Artist> artists = artistRepo.findAll();
    for (int i = 1; i <= appDataConfig.getAlbumCount(); i++) {
      albums.add(new Album(
          faker.book().title(),
          faker.text().text(),
          artists.get(RANDOM.nextInt(artists.size())),
          new ArrayList<>()
        )
      );
    }
    albumRepo.saveAll(albums);
  }
}
