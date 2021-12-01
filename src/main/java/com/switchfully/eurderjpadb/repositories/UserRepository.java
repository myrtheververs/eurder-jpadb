package com.switchfully.eurderjpadb.repositories;

import com.switchfully.eurderjpadb.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
