package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
