package com.sale.iTrade.controllers;

import com.sale.iTrade.models.Cart;
import com.sale.iTrade.models.Product;
import com.sale.iTrade.models.User;
import com.sale.iTrade.repositories.CartRepository;
import com.sale.iTrade.repositories.UserRepository;
import com.sale.iTrade.services.CartService;
import com.sale.iTrade.services.ProductService;
import com.sale.iTrade.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequiredArgsConstructor
public class CartController{

    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final CartService cartService;
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @AuthenticationPrincipal UserDetails userDetails) {
        // Fetch user directly from authenticated principal
        User user = userService.findByUsername(userDetails.getUsername());
        Product product = productService.findById(productId);

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        cart.addProduct(product);
        cartRepository.save(cart);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpServletRequest request) {
        request.getSession();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", token);
        User user = userService.findByUsername(userDetails.getUsername());
        Cart cart = cartRepository.findByUser(user).orElse(new Cart());
        model.addAttribute("products", cart.getProducts());
        model.addAttribute("user", user);
        model.addAttribute("amount_of_products_in_cart", cart.getProducts().size());
        model.addAttribute("costs", cart.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum());
        return "cart";
    }

    @PostMapping("/cart/delete")
    public String deleteProduct(@RequestParam Long id,
                                @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails==null) return "redirect:/login";
        User user = userService.findByUsername(userDetails.getUsername());
        Cart cart = cartService.getCartForUser(user);
        cartService.removeProductFromCart(cart, id);

        return "redirect:/cart";
    }
}


