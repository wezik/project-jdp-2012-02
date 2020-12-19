package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.ProductListDto;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                product.getGroup().getId(),
                product.getCartsWhichContainsThisProduct()
        );
    }

    public ProductListDto mapToProductListDto (final Product product) {
        return new ProductListDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId()
        );
    }

    public Product mapToProduct(final ProductDto productDto) {

        Optional<Group> assignedGroup = groupDbService.getGroup(productDto.getGroupId());

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                assignedGroup.get(),
                productDto.getCartsWhichContainsThisProduct()
        );
    }

    public List<ProductListDto> mapProductListToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(this::mapToProductListDto)
                .collect(Collectors.toList());
    }
}
