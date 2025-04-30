package com.myshopping.My.Shopping.REQUEST_DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    @NotNull
    private Long orderId;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String paymentStatus;
}
