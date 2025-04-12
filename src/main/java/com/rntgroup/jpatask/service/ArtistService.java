package com.rntgroup.jpatask.service;

import com.rntgroup.jpatask.config.AppDataConfig;
import com.rntgroup.jpatask.entity.Artist;
import com.rntgroup.jpatask.proj.ArtistPurchaseStatProj;
import com.rntgroup.jpatask.repos.ArtistRepo;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(timeout = 30)
public class ArtistService {

  private final ArtistRepo artistRepo;
  private final AppDataConfig appDataConfig;
  private final Faker faker = new Faker();

  public Artist createArtist(Artist artist) {
    return artistRepo.save(artist);
  }

  @Transactional(readOnly = true)
  public Artist getByName(String name) {
    return artistRepo.findArtistByName(name).orElse(null);
  }

  @Transactional(readOnly = true)
  public List<Artist> getAll() {
    return artistRepo.findAll();
  }

  @Transactional(readOnly = true)
  public List<ArtistPurchaseStatProj> getRichArtistsBySumPurchases(int minSumPurchases) {
    return artistRepo.findRichArtistsBySumPurchases(minSumPurchases);
  }

  public void updateName(long artistId, String newName) {
    artistRepo.updateNameById(artistId, newName);
  }

  public void deleteById(long id) {
    artistRepo.deleteById(id);
  }

  @PostConstruct
  private void init() {
    List<Artist> artists = new ArrayList<>();
    for (int i = 0; i < appDataConfig.getArtistCount(); i++) {
      artists.add(new Artist(
          faker.name().firstName() + "_" + UUID.randomUUID(),
          new ArrayList<>()
        )
      );
    }
    artistRepo.saveAll(artists);
  }
}
