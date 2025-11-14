package com.sale.iTrade.models;
import com.sale.iTrade.models.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true) // уникальное поле
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "name")
    private String name;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;

    // Поле active будет определять активен пользователь или нет. Если он
//    активен (true), то сможет войти в аккаунт, размещать товары и т.д. Это нужно для того, например, чтобы не допускать пользователя к аккаунту, пока он не подтвердит свой имейл, или блокировать (банить) подтвержденного пользователя в случае необходимости и др.
    @Column(name = "active")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;

    // Поле пароля будет шифроваться, его длина будет выходить за пределы 255 символов (тип VARCHAR), поэтому увеличим длину поля, например, до 1000
//    символов.
    @Column(name = "password", length = 1000)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //Элемент коллекции (роли)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) // Имя таблицы в базе данных для ролей user_role с полем
//    первичного ключа user_id
    @Enumerated(EnumType.STRING) // тип поля


    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<Product> products = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
//            mappedBy = "user")
//    private List<Product> cartProducts = new ArrayList<>();

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    // Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email; // email является логином
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

//    public void addProduct(Product product){
//        cartProducts.add(product);
//    }

}


