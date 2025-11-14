package com.sale.iTrade.services;

import com.sale.iTrade.models.Cart;
import com.sale.iTrade.models.User;
import com.sale.iTrade.repositories.CartRepository;
import com.sale.iTrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    public Cart getCartForUser(User user) {
        if (user == null) return null; // no user, no cart

        // Ensure the user is persisted
        if (user.getId() == null) {
            user = userRepository.save(user);
        }

        // Now safely fetch or create cart
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart); // safe now
        }

        user.setCart(cart); // optional for bidirectional
        return cart;
    }

    @Transactional
    public void removeProductFromCart(Cart cart, Long id) {
        if(cart==null) return;
        cart.getProducts().removeIf(p -> p.getId().equals(id));
        cartRepository.save(cart);
    }
}
