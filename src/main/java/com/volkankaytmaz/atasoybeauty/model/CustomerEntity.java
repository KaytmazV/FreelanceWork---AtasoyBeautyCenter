package com.volkankaytmaz.atasoybeauty.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "customer_table")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false)

    private Long id;

    @NotBlank(message = "Bu alan boş bırakılamaz!")
    @Size(max = 25, message = "isim 25 harfi geçemez.")
    private String name;

    @NotBlank(message = "Bu alan boş bırakılamaz!")
    @Size(max = 25, message = "soyisim 25 harfi geçemez.")
    private String surName;

    @NotBlank(message = "Bu alan boş bırakılamaz!")
    @Email(message = "Geçerli bir e-posta adresi giriniz!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Geçerli bir e-posta adresi giriniz!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Bu alan boş bırakılamaz!")
    @Pattern(regexp = "^\\d{10}$", message = "Geçerli bir telefon numarası giriniz!")
    @Column(unique = true, length = 10, nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Bu alan boş bırakılamaz!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Şifre en az bir harf, bir rakam ve bir özel karakter içermelidir ve 6 karakterden kısa olmamalıdır.")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
