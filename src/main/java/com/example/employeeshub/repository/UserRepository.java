package com.example.employeeshub.repository;

import com.example.employeeshub.models.RoleModel;
import com.example.employeeshub.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Long> {

     Optional<UserModel> findByNombreAndEmail(String nombre, String email);
     Optional<UserModel> findByEmail(String email);
}
