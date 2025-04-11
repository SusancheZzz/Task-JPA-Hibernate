package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.dto.UserStatDto;
import com.rntgroup.jpatask.entity.User;
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
      select new com.rntgroup.jpatask.dto.UserStatDto(u.id, u.username, count(p.id), sum(p.price))
      from User u
      join u.purchases p
      group by u.id, u.username
      having count(p.id) >= :minCountPurchases
      order by sum(p.price) desc
    """)
  List<UserStatDto> findAllRichUserOrderBySumDesc(
    @Param("minCountPurchases") int minCountPurchases);
}
