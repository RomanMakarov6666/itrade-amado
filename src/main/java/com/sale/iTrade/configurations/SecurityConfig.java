package com.sale.iTrade.configurations;

import com.sale.iTrade.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;

    // Переопределение метода конфигурирования HttpSecurity (для доступности url-адресов)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                // Метод запроса авторизации
                .authorizeRequests()
                // Какие url-адреса доступны (** - заменяют произвольный id)
                .antMatchers("/", "/product/**", "/images/**", "/registration", "/user/**", "/static/**")
                // Разрешение на все
                .permitAll()
                // Требование аутентификации пользователя
                .anyRequest().authenticated()
                .and()
                // Форма для входа (логина)
                .formLogin()
                // Адрес страницы для входа (логина)
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                // Конфигурация выхода (логаут)
                .logout()
                .permitAll();
    }

    // Переопределение метода конфигурирования AuthenticationManagerBuilder (для авторизации)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8); // Алгоритм шифрования пароля
    }


}
