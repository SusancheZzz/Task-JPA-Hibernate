package com.rntgroup.jpatask.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "album")
@Getter
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  @ManyToOne
  private Artist artist;
  @OneToMany(
    mappedBy = "album",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Song> songs;

  protected Album() {}

  public Album(String title, String description, Artist artist, List<Song> songs) {
    this.title = title;
    this.description = description;
    this.artist = artist;
    this.songs = songs;
  }
}
