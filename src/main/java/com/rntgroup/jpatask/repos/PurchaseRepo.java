package com.rntgroup.jpatask.repos;

import com.rntgroup.jpatask.entity.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

  @Query("select p from Purchase p where p.user.id = :userId")
  List<Purchase> findAllByUserId(Long userId);
}
