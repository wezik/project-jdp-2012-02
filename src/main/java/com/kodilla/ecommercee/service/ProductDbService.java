package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.repository.GenericEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDbService {

    @Autowired
    private final GenericEntityRepository dbService;

    public ProductDbService(GenericEntityRepository dbService) {
        this.dbService = dbService;
    }
}
