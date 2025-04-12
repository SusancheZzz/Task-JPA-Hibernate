package com.rntgroup.jpatask.service;

import com.rntgroup.jpatask.config.AppDataConfig;
import com.rntgroup.jpatask.entity.Purchase;
import com.rntgroup.jpatask.entity.Song;
import com.rntgroup.jpatask.entity.User;
import com.rntgroup.jpatask.repos.PurchaseRepo;
import com.rntgroup.jpatask.repos.SongRepo;
import com.rntgroup.jpatask.repos.UserRepo;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@DependsOn({"userService", "songService"})
@Transactional(timeout = 30)
public class PurchaseService {

  private final PurchaseRepo purchaseRepo;
  private final SongRepo songRepo;
  private final UserRepo userRepo;
  private final AppDataConfig appDataConfig;
  private static final Random RANDOM = new Random();

  public Purchase createPurchase(Purchase purchase) {
    return purchaseRepo.save(purchase);
  }

  @Transactional(readOnly = true)
  public Purchase getPurchase(Long id) {
    return purchaseRepo.findById(id).orElse(null);
  }

  @Transactional(readOnly = true)
  public List<Purchase> getAllPurchasesByUserId(Long userId) {
    return purchaseRepo.findAllByUserId(userId);
  }

  public Purchase updatePurchase(Purchase purchase) {
    return purchaseRepo.save(purchase);
  }

  public void deletePurchase(Long id) {
    purchaseRepo.deleteById(id);
  }

  @PostConstruct
  public void init() {
    List<Purchase> purchases = new ArrayList<>();
    List<Song> songs = songRepo.findAll();
    List<User> users = userRepo.findAll();
    for (int i = 1; i <= appDataConfig.getPurchaseCount(); i++) {
      purchases.add(new Purchase(
          users.get(RANDOM.nextInt(users.size())),
          songs.get(RANDOM.nextInt(songs.size())),
          RANDOM.nextInt(1000, 50000),
          LocalDateTime.now()
        )
      );
    }
    purchaseRepo.saveAll(purchases);
  }
}
