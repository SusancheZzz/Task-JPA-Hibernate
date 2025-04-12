package com.rntgroup.jpatask.service;

import com.rntgroup.jpatask.config.AppDataConfig;
import com.rntgroup.jpatask.entity.User;
import com.rntgroup.jpatask.proj.UserStatProj;
import com.rntgroup.jpatask.repos.UserRepo;
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
public class UserService {

  private final UserRepo userRepo;
  private final AppDataConfig appDataConfig;
  private final Faker faker = new Faker();

  @Transactional(readOnly = true)
  public List<UserStatProj> getRichUsers(int minCountPurchases) {
    return userRepo.findAllRichUserOrderBySumDesc(minCountPurchases);
  }

  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User createUser(User user) {
    return userRepo.save(user);
  }

  @Transactional(readOnly = true)
  public User getUserByUsername(String username) {
    return userRepo.findByUsername(username).orElse(null);
  }

  public void updateEmailUser(long userId, String newEmail) {
    userRepo.updateEmailUserById(userId, newEmail);
  }

  public void deleteUserById(long userId) {
    userRepo.deleteUserById(userId);
  }

  @PostConstruct
  private void init() {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < appDataConfig.getUserCount(); i++) {
      users.add(new User(
          faker.name().firstName() + "_" + UUID.randomUUID(),
          faker.internet().emailAddress(),
          new ArrayList<>()
        )
      );
    }
    userRepo.saveAll(users);
  }
}
