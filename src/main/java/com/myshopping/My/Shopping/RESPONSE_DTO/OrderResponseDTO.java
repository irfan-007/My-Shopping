package com.myshopping.My.Shopping.RESPONSE_DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private List<OrderItemResponseDTO> orderItems;
    private Double totalPrice;
    private String status;
}