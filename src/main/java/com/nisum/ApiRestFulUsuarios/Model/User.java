package com.nisum.ApiRestFulUsuarios.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"users\"")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @NotNull(message = "El campo name no puede ser null")

    @Column(name = "name", nullable = false)
    private String name;
    @Email(message = "El correo es invalido")
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull(message = "El campo password no puede ser null")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "El password de usuario solo puede contener letras y números")
    @Column(name = "password", nullable = false)
    private String password;
    @Transient
    private List<Phone> phones;
    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "modified", nullable = true)
    private Date modified;
    @Column(name = "last_login", nullable = false)
    private Date lastLogin;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
