package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.repository.CartProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartProductDbService {

    final CartProductRepository repository;

}
