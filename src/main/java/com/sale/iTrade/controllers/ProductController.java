package com.sale.iTrade.controllers;

import com.sale.iTrade.models.Cart;
import com.sale.iTrade.models.Product;
import com.sale.iTrade.models.User;
import com.sale.iTrade.services.CartService;
import com.sale.iTrade.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Controller // аннотация контроллера
@RequiredArgsConstructor // обязательные аргументы конструктора
public class ProductController {
    private final CartService cartService;
    private final ProductService productService;

    // Главная страница сайта, где будут отображаться товары
    @GetMapping("/") // аннотация пакета запроса с указанием корневой
//    страницы на хосте
    public String products(@RequestParam(name = "title", required = false) String title,
                           Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", user);

        int cartSize = 0;
        if (user != null) {
            Cart cart = cartService.getCartForUser(user);
            if (cart != null && cart.getProducts() != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("amount_of_products_in_cart", cartSize);

        return "products";
    }


    //    Добавление нового товара
    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2, @RequestParam("file3")
                                        MultipartFile file3, @RequestParam("file4") MultipartFile file4,
                                Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3, file4);
        return "redirect:/";
    }


    // Просмотр товара
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal, HttpServletRequest request) {
        request.getSession();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", token);
        User user = productService.getUserByPrincipal(principal);
        Product product = productService.getProductById(id);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        int cartSize = 0;
        if (user != null) {
            Cart cart = cartService.getCartForUser(user);
            if (cart != null && cart.getProducts() != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("amount_of_products_in_cart", cartSize);
        return "product-info";
    }


    // Удаление товара
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
    @GetMapping("/product/new") // аннотация пакета запроса с указанием корневой
//    страницы на хосте
    public String latestProducts(@RequestParam(name = "title", required = false) String title,
                           Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("products", productService.listOfLatestProducts(24));
        model.addAttribute("user", user);

        int cartSize = 0;
        if (user != null) {
            Cart cart = cartService.getCartForUser(user);
            if (cart != null && cart.getProducts() != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("amount_of_products_in_cart", cartSize);

        return "products";
    }







}
