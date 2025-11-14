package com.sale.iTrade.services;

import com.sale.iTrade.models.Image;
import com.sale.iTrade.models.Product;
import com.sale.iTrade.models.User;
import com.sale.iTrade.repositories.ProductRepository;
import com.sale.iTrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ProductService {

    public final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Вывод списка товаров
    public List<Product> listProducts(String title) {
        if (title != null && title.length() >= 3)
            return productRepository.findByTitleContainingIgnoreCase(title);
        return productRepository.findAll();
    }


    // Сохранение товара. Нет необходимости поочередно принимать поля title, description и т.д., фреймворк Spring сам может из html-формы собрать модель и мы ее получим уже с сервера
    public void saveProduct(Principal principal, Product product, MultipartFile file1,
           MultipartFile file2, MultipartFile file3, MultipartFile file4) throws IOException
    {

        product.setUser(getUserByPrincipal(principal));

        Image image1; // Выделяем память под каждое изображение (создаем переменные)
        Image image2;
        Image image3;
        Image image4;


        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1); // вызов метода создания сущности изображения
            image1.setPreviewImage(true); // первую фото ставим в превью
            product.addImageToProduct(image1);
        }

        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        if (file4.getSize() != 0) {
            image4 = toImageEntity(file4);
            product.addImageToProduct(image4);
        }


        log.info("Saving new Product. Title: {}; Author email: {}",
                product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
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



    // Удаление товара
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    //    Поиск товара по Id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product findById(Long productId) {return productRepository.findById(productId).orElse(null);}

    public List<Product> listOfLatestProducts(int hours) {
        return productRepository.findByDateOfCreatedAfter(LocalDateTime.now().minusHours(hours));
    }
}

