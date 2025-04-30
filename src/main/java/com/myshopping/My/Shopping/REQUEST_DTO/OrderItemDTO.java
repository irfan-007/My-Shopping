package com.myshopping.My.Shopping.REQUEST_DTO;

import com.myshopping.My.Shopping.RESPONSE_DTO.ProductResponseDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private ProductResponseDTO product;
    private Integer quantity;
    private Double price;
}