package com.example.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)

                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/h2-console/**").permitAll()

                        // Kitap isteklerini hem ÖĞRENCİ (USER) hem KÜTÜPHANECİ (ADMIN) görebilir
                        .requestMatchers(HttpMethod.GET, "/books/**").hasAnyRole("USER", "ADMIN")

                        // Kitap ekleme, güncelleme ve silme işlemlerini sadece KÜTÜPHANECİ (ADMIN) yapabilir
                        .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())

                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // Öğrenci Kullanıcısı (Sadece Kitapları Listeler)
        UserDetails user = User.builder()
                .username("ogrenci")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();

        // Kütüphaneci Kullanıcısı (Tüm Yetkilere Sahip)
        UserDetails admin = User.builder()
                .username("kutuphaneci")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}