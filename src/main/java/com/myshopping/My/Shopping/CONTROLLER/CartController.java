package com.myshopping.My.Shopping.CONTROLLER;

import com.myshopping.My.Shopping.REQUEST_DTO.CartDTO;
import com.myshopping.My.Shopping.RESPONSE_DTO.CartResponseDTO;
import com.myshopping.My.Shopping.SERVICE.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDTO> getMyCart() {
        CartResponseDTO cart = cartService.getMyCart();
        return ResponseEntity.ok(cart);
    }

    @PutMapping
    public ResponseEntity<CartResponseDTO> updateCart(@RequestBody CartDTO cart) {
        CartResponseDTO updatedCart = cartService.updateCart(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}