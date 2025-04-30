package com.myshopping.My.Shopping.SERVICE;

import com.myshopping.My.Shopping.MODEL.Cart;
import com.myshopping.My.Shopping.MODEL.CartItem;
import com.myshopping.My.Shopping.MODEL.Product;
import com.myshopping.My.Shopping.MODEL.User;
import com.myshopping.My.Shopping.REPOSITORY.CartRepository;
import com.myshopping.My.Shopping.REPOSITORY.ProductRepository;
import com.myshopping.My.Shopping.REPOSITORY.UserRepository;
import com.myshopping.My.Shopping.REQUEST_DTO.CartDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.CartItemResponseDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.CartResponseDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartResponseDTO getMyCart() {
        User user = getCurrentUser();
        Cart cart= cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createEmptyCartForUser(user));
        return makeCartResponseDto(cart);
    }

    public Cart createEmptyCartForUser(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .cartItems(List.of())
                .build();
        return cartRepository.save(cart);
    }

    public CartResponseDTO updateCart(CartDTO updatedCart) {
        User user = getCurrentUser();
        Cart existingCart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItem> itmsList = updatedCart.getCartItems().stream().map(itm->{
            Product p=productRepository.findById(itm.getProductId()).get();
            if(p==null) throw new RuntimeException("Product not found");
            return CartItem.builder()
                    .product(p)
                    .id(itm.getId())
                    .cart(existingCart)
                    .quantity(itm.getQuantity())
                    .build();
        }).collect(Collectors.toList());
        existingCart.setCartItems(itmsList);
        cartRepository.save(existingCart);
        return  makeCartResponseDto(existingCart);
    }


    public void clearCart() {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private CartResponseDTO makeCartResponseDto(Cart cart) {
        List<CartItemResponseDTO> cartItemResponseList=cart.getCartItems().stream().map(itm->{
            ProductResponseDTO p=ProductResponseDTO.builder()
                    .name(itm.getProduct().getName())
                    .price(itm.getProduct().getPrice())
                    .description(itm.getProduct().getDescription())
                    .id(itm.getProduct().getId())
                    .category(itm.getProduct().getCategory())
                    .imageUrl(itm.getProduct().getImageUrl())
                    .build();
            return CartItemResponseDTO.builder().product(p).id(itm.getId()).quantity(itm.getQuantity()).build();
        }).collect(Collectors.toList());

        return CartResponseDTO.builder()
                .cartItems(cartItemResponseList)
                .id(cart.getId())
                .build();
    }
}