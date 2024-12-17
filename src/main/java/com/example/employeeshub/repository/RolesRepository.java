package com.example.employeeshub.repository;

import com.example.employeeshub.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByNombreRol(String nombreRol);
}
