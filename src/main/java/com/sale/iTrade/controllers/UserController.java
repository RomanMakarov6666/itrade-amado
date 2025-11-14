package com.sale.iTrade.controllers;

import com.sale.iTrade.models.User;
import com.sale.iTrade.services.ProductService;
import com.sale.iTrade.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final ProductService productService;
    private final UserService userService;

    // Страница логина
    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "login";
    }


    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "registration";
    }

    // Переход на страницу логина после регистрации, с сообщением о
//    существовании пользователя при совпадении имейла

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь: \"" + user.getEmail() + "\" уже существует");
            return "registration"; // если пользователь существует, остаемся на странице регистрации
        }
//        return "redirect:/hello"; // переход на временную защищенную тестовую стра-ницу в случае успешного входа
        return "redirect:/";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
    @GetMapping("/me/products")
    public String principalInfo(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "me-info";
    }

    @PostMapping("/me/avatar/change")
    public String principalAvatarChange(Principal principal, @RequestParam("file1") MultipartFile file)
    throws IOException {
        userService.changeAvatar(productService.getUserByPrincipal(principal), file);
        return "redirect:/me/products";
    }

    

    // Тестовая страница для успешного входа
//    @GetMapping("/hello")
//    public String securityUrl() {
//        return "hello";
//    }


}
