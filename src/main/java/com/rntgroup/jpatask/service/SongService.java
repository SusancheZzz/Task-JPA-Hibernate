package com.rntgroup.jpatask.service;

import com.rntgroup.jpatask.config.AppDataConfig;
import com.rntgroup.jpatask.dto.SongStatDto;
import com.rntgroup.jpatask.entity.Album;
import com.rntgroup.jpatask.entity.Artist;
import com.rntgroup.jpatask.entity.Song;
import com.rntgroup.jpatask.repos.AlbumRepo;
import com.rntgroup.jpatask.repos.ArtistRepo;
import com.rntgroup.jpatask.repos.SongRepo;
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
@DependsOn({"artistService", "albumService"})
@Transactional(timeout = 30)
public class SongService {

  private final SongRepo songRepo;
  private final ArtistRepo artistRepo;
  private final AlbumRepo albumRepo;
  private final AppDataConfig appDataConfig;
  private final Faker faker = new Faker();
  private static final Random RANDOM = new Random();

  public Song createSong(Song song) {
    return songRepo.save(song);
  }

  @Transactional(readOnly = true)
  public List<SongStatDto> getPopularSongsOfArtist(String artistName, int minCountPurchase) {
    return songRepo.findAllSongOfArtistByCountPurchase(artistName, minCountPurchase);
  }

  @Transactional(readOnly = true)
  public List<Song> getAllSongsByArtistName(String artistName) {
    return songRepo.findAllByAuthorName(artistName);
  }

  @Transactional(readOnly = true)
  public List<Song> getSongsByTitle(String title) {
    return songRepo.findAllByTitle(title);
  }

  public void updateTitleSong(long songId, String newTitle) {
    songRepo.updateTitleById(songId, newTitle);
  }

  public void deleteUserById(long userId) {
    songRepo.deleteById(userId);
  }

  @PostConstruct
  private void init() {
    List<Song> songs = new ArrayList<>();
    List<Artist> artists = artistRepo.findAll();
    List<Album> albums = albumRepo.findAll();
    for (int i = 1; i <= appDataConfig.getSongCount(); i++) {
      songs.add(new Song(
          faker.book().title(),
          RANDOM.nextInt(60, 300),
          artists.get(RANDOM.nextInt(artists.size())),
          albums.get(RANDOM.nextInt(albums.size())),
          new ArrayList<>()
        )
      );
    }
    songRepo.saveAll(songs);
  }
}
