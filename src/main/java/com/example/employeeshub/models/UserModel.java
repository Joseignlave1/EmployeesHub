package com.example.employeeshub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//Hago los getters y setters directamente con lombok, libreria que te evita el codigo repetitivo.
@Builder //Requiere AllArgs constructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
//@data ya te crea getters y setters, equals, hashCode, toString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Declara que el campo ID va a ser generado automaticamente en la BD
    private Long id;

    @NotNull
    private String nombre;

    @Email //Verifica que el email tenga el formato correcto.
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    //Relacion bilateral, tenemos los roles que tiene un usuario.
    private List<RoleModel> roles = new ArrayList<>();

    @Transactional
    public void addRole(RoleModel roleModel) {
        //Añadimos el usuario a la lista de roles.
        this.roles.add(roleModel);
        //Añadimos el usuario a la lista de usuarios que contienen ese rol.
        roleModel.getUsuarios().add(this);
    }

    @Transactional
    public void deleteRol(RoleModel roleModel) {
        //Eliminamos el usuario a de la lista de roles.
        this.roles.remove(roleModel);
        //Eliminamos el usuario a de la lista de usuarios que contienen ese rol.
        roleModel.getUsuarios().remove(this);
    }
}
