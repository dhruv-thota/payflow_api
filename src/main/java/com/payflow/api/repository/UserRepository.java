package com.payflow.api.repository;

import com.payflow.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Derived Query Method: Spring Data JPA automatically generates SQL based on method name
    Optional<User> findByUpiId(String upiId);

    // Custom JPQL Query Method: Operates on Java Entity (User) and entity attributes (u.balance)
    @Query("SELECT u FROM User u WHERE u.balance > :amount")
    List<User> findUsersWithBalanceGreaterThan(@Param("amount") Double amount);
}
