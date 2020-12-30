package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.repository.CartEntryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartEntryDbService {

    final CartEntryRepository repository;

    public CartEntry saveEntry(CartEntry cartEntry) {
        return repository.save(cartEntry);
    }

    public CartEntry getEntry(Long entryId) {
        return repository.findById(entryId).get();
    }

    public void deleteEntry(Long entryId) {
        repository.deleteById(entryId);
    }
}
