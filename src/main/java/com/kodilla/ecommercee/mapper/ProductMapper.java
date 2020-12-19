package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Autowired
    private GroupDbService groupDbService;

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getGroupName(),
                product.getCartsWhichContainsThisProduct()
        );
    }

    public Product mapToProduct(final ProductDto productDto) {

        Group assignedGroupStub = new Group(productDto.getGroupName());
        groupDbService.saveGroup(assignedGroupStub);
        Group assignedGroup = groupDbService.getGroup(assignedGroupStub.getId()).get();
        //DOCELOWO ZAMIAST STUBA Group assignedGroup = groupDbService.getGroupByName(productDto.getGroupName()); -> do przygotowania

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                assignedGroup,
                productDto.getCartsWhichContainsThisProduct()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
