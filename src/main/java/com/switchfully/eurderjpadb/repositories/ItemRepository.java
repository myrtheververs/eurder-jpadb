package com.switchfully.eurderjpadb.repositories;

import com.switchfully.eurderjpadb.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
