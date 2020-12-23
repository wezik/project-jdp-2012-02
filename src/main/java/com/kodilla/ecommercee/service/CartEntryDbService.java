package com.kodilla.ecommercee.service;

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

}
