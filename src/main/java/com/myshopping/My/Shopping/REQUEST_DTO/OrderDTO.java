package com.myshopping.My.Shopping.REQUEST_DTO;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private List<OrderItemDTO> orderItems;
    private Double totalPrice;
    private String status;
    private String paymentMethod;
}