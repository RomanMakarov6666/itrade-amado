package com.sale.iTrade.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Делает данный клас эмулирующим таблицу из базы данных
@Entity

// Название таблицы базы данных
@Table(name = "products")

@Data
@AllArgsConstructor // конструктор со всеми полями
@NoArgsConstructor // конструктор по умолчанию, геттеры и сеттеры и т.д.
public class Product {
    @Id // первичный ключ записи базы данных (primary key) для автоинкремента
    @GeneratedValue(strategy = GenerationType.AUTO) // тип генерации Id
    @Column(name = "id") // Название поля для Id в таблице products базы
//    данных. По умолчанию имя для поля таблицы назначается по имени переменной, но
//    можно назначить самостоятельно
    private Long id; // уникальный идентификатор (номер) товара

    @Column(name = "title") // имя поля для заголовка товара в таблице products. Для String по умолчанию в б.д. создается тип поля VARCHAR
//(максимальная длина поля - 255 символов)
    private String title; // заголовок товара

    @Column(name = "description", columnDefinition = "text") // имя поля для описания товара в таблице products, с указанием типа text (максимальная длина - 65535 символов (64 Кб))
    private String description; // описание товара

    @Column(name = "price") // имя поля для цены товара в таблице products
    private Integer price; // цена товара

    @Column(name = "city") // имя поля для города товара в таблице products
    private String city; // город локации товара

//    @Column(name = "seller") // имя поля для продавца товара в таблице products
//    private String seller; // продавец товара

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

}

