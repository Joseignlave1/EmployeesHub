package com.example.employeeshub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "roles")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombreRol;

    //Relacion bidireccional, tambien podemos ver los usuarios que tiene cada rol
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserModel> usuarios = new ArrayList<>();
}
