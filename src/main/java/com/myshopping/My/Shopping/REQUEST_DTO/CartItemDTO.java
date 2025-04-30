package com.myshopping.My.Shopping.REQUEST_DTO;

import com.myshopping.My.Shopping.MODEL.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
}
