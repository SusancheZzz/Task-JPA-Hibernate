package com.rntgroup.jpatask;

import com.rntgroup.jpatask.service.AlbumService;
import com.rntgroup.jpatask.service.ArtistService;
import com.rntgroup.jpatask.service.SongService;
import com.rntgroup.jpatask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaApplication implements CommandLineRunner {

  private final UserService userService;
  private final SongService songService;
  private final ArtistService artistService;
  private final AlbumService albumService;

  public static void main(String[] args) {
    SpringApplication.run(JpaApplication.class, args);
  }

  @Override
  public void run(String... args) {
    var jpqlRichUserStats = userService.getRichUsers(2);
    var namedPopularTracks = songService.getPopularSongsOfArtist(
      "Helene_08102f44-9cad-4b64-8f6c-16d4bed63a3f",
      2
    );
    var nativeTopRichArtist = artistService.getRichArtistsBySumPurchases(20_000);
    var criteriaAlbums = albumService.getAlbumsWithMoreThanXsongs(2);

    System.out.println();
  }

}
