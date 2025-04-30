package com.myshopping.My.Shopping.SERVICE;

import com.myshopping.My.Shopping.MODEL.Order;
import com.myshopping.My.Shopping.MODEL.Payment;
import com.myshopping.My.Shopping.REPOSITORY.OrderRepository;
import com.myshopping.My.Shopping.REPOSITORY.PaymentRepository;
import com.myshopping.My.Shopping.REQUEST_DTO.PaymentDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.PaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentResponseDTO createPayment(PaymentDTO payment) {
        Order order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Payment p=Payment.builder()
                .paymentDate(LocalDateTime.now())
                .order(order)
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .build();

        return makePaymentResponseDto(paymentRepository.save(p));
    }

    public PaymentResponseDTO getPaymentById(Long id) {
        Payment p= paymentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Payment not found !"));
        return makePaymentResponseDto(p);
    }

    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
        Payment p= paymentRepository.findByOrderId(orderId)
                .orElseThrow(()->new RuntimeException("Payment not found !"));
        return makePaymentResponseDto(p);
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::makePaymentResponseDto)
                .collect(Collectors.toList());
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentResponseDTO makePaymentResponseDto(Payment payment){
        return PaymentResponseDTO.builder()
                .paymentDate(payment.getPaymentDate())
                .paymentStatus(payment.getPaymentStatus())
                .id(payment.getId())
                .paymentMethod(payment.getPaymentMethod())
                .orderId(payment.getOrder().getId())
                .build();
    }
}