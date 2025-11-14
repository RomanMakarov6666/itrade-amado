package com.sale.iTrade.services;

import com.sale.iTrade.models.Image;
import com.sale.iTrade.models.User;
import com.sale.iTrade.models.enums.Role;
import com.sale.iTrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Метод создания пользователя
    public boolean createUser(User user) {
        // Создаем имейл пользователя
        String email = user.getEmail();
        // Валидация
        if (userRepository.findByEmail(email) != null) return false;
        // Назначаем статус активного пользователя
        user.setActive(true);
        // Шифрование пароля
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Добавляем роль пользователя
        user.getRoles().add(Role.ROLE_USER);
        // Лог создания пользователя с имейлом
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public User findByUsername(String mail) {return userRepository.findByEmail(mail);}
    public User findById(Long id){return userRepository.findById(id).orElse(null);}

    public void changeAvatar(User user, MultipartFile file)throws IOException {
        user.setAvatar(toImageEntity(file));
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return userRepository.findByEmail(principal.getName());
    }



    // Метод создания сущности изображения (преобразование из файла в модель изобра-жения). Принимаем в метод файл как параметр
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image(); // Создаем новый объект
        image.setName(file.getName()); // Получаем имя из переданного файла
        image.setOriginalFileName(file.getOriginalFilename()); // Получаем
//        оригинальное имя файла
        image.setContentType(file.getContentType()); // Получаем тип файла
        image.setSize(file.getSize()); // Получаем размер файла
        image.setBytes(file.getBytes()); // Получаем содержимое файла - байты
        return image;
    }
}
