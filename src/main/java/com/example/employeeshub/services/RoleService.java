package com.example.employeeshub.services;

import com.example.employeeshub.models.RoleModel;
import com.example.employeeshub.repository.RolesRepository;
import com.example.employeeshub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleService {
    private final RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<RoleModel> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Optional<RoleModel> findByNombreRol(String nombreRol) {
        return rolesRepository.findByNombreRol(nombreRol);
    }
    public RoleModel getRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("The id was not found"));
    }
    public RoleModel postRol(RoleModel request) {
        if(request.getNombreRol().isEmpty()) {
            throw new IllegalArgumentException("The rol cannot be empty");
        }
        return rolesRepository.save(request);
    }

    public RoleModel putRol(Long id, RoleModel updatedRol) {
        return rolesRepository.findById(id)
                .map(rol -> {
                    rol.setNombreRol(updatedRol.getNombreRol());
                    return rolesRepository.save(updatedRol);
                })
                .orElseThrow(() -> new NoSuchElementException("The rol was not found"));
    }

    public boolean deleteRol(Long id) {
        try {
            rolesRepository.deleteById(id);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
