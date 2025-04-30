package com.myshopping.My.Shopping.SERVICE;

import com.myshopping.My.Shopping.MODEL.Order;
import com.myshopping.My.Shopping.MODEL.OrderItem;
import com.myshopping.My.Shopping.MODEL.Product;
import com.myshopping.My.Shopping.MODEL.User;
import com.myshopping.My.Shopping.REPOSITORY.OrderRepository;
import com.myshopping.My.Shopping.REPOSITORY.ProductRepository;
import com.myshopping.My.Shopping.REPOSITORY.UserRepository;
import com.myshopping.My.Shopping.REQUEST_DTO.OrderDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.OrderItemResponseDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.OrderResponseDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public OrderResponseDTO createOrder(OrderDTO orderDto) {
        User user = getCurrentUser();

        List<OrderItem> orderItems = orderDto.getOrderItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            return OrderItem.builder()
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
        }).collect(Collectors.toList());

        Order order = Order.builder()
                .user(user)
                .orderItems(orderItems)
                .totalPrice(orderDto.getTotalPrice())
                .status(orderDto.getStatus())
                .paymentMethod(orderDto.getPaymentMethod())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Set back-reference from OrderItem to Order
        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDto(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        Order updated = orderRepository.save(order);
        return mapToDto(updated);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderResponseDTO mapToDto(Order order) {
        List<OrderItemResponseDTO> orderItemDtos = order.getOrderItems().stream()
                .map(item -> {
                    Product p=item.getProduct();
                    ProductResponseDTO prespond=ProductResponseDTO.builder()
                            .name(p.getName())
                            .price(p.getPrice())
                            .description(p.getDescription())
                            .id(p.getId())
                            .imageUrl(p.getImageUrl())
                            .category(p.getCategory())
                            .build();

                    return OrderItemResponseDTO.builder()
                            .id(item.getId())
                            .product(prespond)
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .build();
 
                }).collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .orderItems(orderItemDtos)
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // assuming username/email is principal
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
