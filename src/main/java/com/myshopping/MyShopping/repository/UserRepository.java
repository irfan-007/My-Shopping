package com.myshopping.MyShopping.repository;

import com.myshopping.MyShopping.models.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUsers, UUID> {
    // jpa can handle saving of data to the user table in database
}
