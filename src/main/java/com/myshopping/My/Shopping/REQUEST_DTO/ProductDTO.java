package com.myshopping.My.Shopping.REQUEST_DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
    private Integer quantityInStock;
}