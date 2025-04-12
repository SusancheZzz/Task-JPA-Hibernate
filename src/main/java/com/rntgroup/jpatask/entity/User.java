package com.rntgroup.jpatask.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "app_user")
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  @Email
  private String email;

  @OneToMany(
    mappedBy = "user",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Purchase> purchases;

  protected User() {}

  public User(String username, String email, List<Purchase> purchases) {
    this.username = username;
    this.email = email;
    this.purchases = purchases;
  }
}
