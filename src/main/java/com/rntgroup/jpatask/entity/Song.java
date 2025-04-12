package com.rntgroup.jpatask.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "song")
@Getter
@NamedQuery(name = "Song.findPopularSongsOfArtistBuPurchase",
  query = """
      select
      s.id as songId,
      s.title as songTitle,
      s.duration as duration,
      alb.title as albumName,
      s.artist.name as artistName,
      count(p.id) as countPurchase
      from  Song s
        join s.artist art
        join s.album alb
        join s.purchases p
      where art.name = :artistName
      group by s.id, s.title, s.duration, alb.title, s.artist.name
      having count(p.id) >= :minCountPurchase
      order by count(p.id) desc
    """)
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer duration;
  @ManyToOne
  private Artist artist;
  @ManyToOne
  private Album album;
  @OneToMany(
    mappedBy = "song",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Purchase> purchases;

  protected Song() {}

  public Song(String title, int duration, Artist artist, Album album, List<Purchase> purchases) {
    this.title = title;
    this.duration = duration;
    this.artist = artist;
    this.album = album;
    this.purchases = purchases;
  }
}
