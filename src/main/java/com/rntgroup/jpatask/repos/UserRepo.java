package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.entity.User;
import com.rntgroup.jpatask.proj.UserStatProj;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  @Modifying
  @Query("update User u set u.email = :newEmail where u.id = :userId")
  void updateEmailUserById(long userId, String newEmail);

  Optional<User> findByUsername(String username);

  void deleteUserById(Long id);

  @Query("""
      select
        u.id as userId,
        u.username as username,
        count(p.id) as totalSongs,
        sum(p.price) as totalSum
      from User u
        join u.purchases p
      group by u.id, u.username
      having count(p.id) >= :minCountPurchases
      order by sum(p.price) desc
    """)
  List<UserStatProj> findAllRichUserOrderBySumDesc(
    @Param("minCountPurchases") int minCountPurchases);
}
