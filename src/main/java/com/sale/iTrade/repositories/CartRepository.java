package com.sale.iTrade.repositories;

import com.sale.iTrade.models.Cart;
import com.sale.iTrade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}

