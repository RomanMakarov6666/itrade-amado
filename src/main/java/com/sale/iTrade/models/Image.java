package com.sale.iTrade.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images") // Название таблицы в базе данных для хранения
//изображений
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Название поля для Id в таблице images базы
//    данных.
    private Long id; // Id изображения
    @Column(name = "name") // Название для поля name в таблице images.
    private String name; // Имя файла изображения
    @Column(name = "originalFileName") // Название для поля originalFileName
//    в таблице images.
    private String originalFileName; // Имя для передачи изображения
    @Column(name = "size") // Название для поля size в таблице images.
    private Long size; // Размер файла изображения
    @Column(name = "contentType") // Название для поля contentType в таблице images.
    private String contentType; // Тип файла изображения
    @Column(name = "previewImage") // Название для поля previewImage в
//    таблице images.
    private boolean previewImage; // Изображение по умолчанию для превью

    @Lob // Тип поля для б.д. MySQL - LONGBLOB (двоичный объект большого
//    размера)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes; // Массив байтов изображения

    // Отношение таблиц многие-к-одному в базе данных (много изображений для
//    одного товара). Аргумент cascade определяет, как повлияет какое-либо
//    действие (например, удаление) сущности изображения на сущность товара. REFRESH - обновить. fetch (фетч) – способ загрузки сущности, EAGER (Ига) - загружать все (LAZY – загружать выборочно).
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;
}
