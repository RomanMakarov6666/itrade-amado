
package com.sale.iTrade.controllers;

import com.sale.iTrade.models.Image;
import com.sale.iTrade.repositories.
        ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.
        InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.
        annotation.GetMapping;
import org.springframework.web.bind.
        annotation.PathVariable;
import org.springframework.web.bind.
        annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        // Создаем объект для изображения из класса Image (выбор по Id)
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                // Получаем поля изображения, в том числе содержимое (байты) из объекта image
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
