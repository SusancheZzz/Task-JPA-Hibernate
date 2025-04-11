package com.rntgroup.jpatask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Table(name = "purchase")
@Getter
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private User user;
  @ManyToOne
  private Song song;
  private int price;
  private LocalDateTime purchaseDate;

  protected Purchase() {}

  public Purchase(User user, Song song, int price, LocalDateTime purchaseDate) {
    this.user = user;
    this.song = song;
    this.price = price;
    this.purchaseDate = purchaseDate;
  }
}
