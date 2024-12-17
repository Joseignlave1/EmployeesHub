package com.example.employeeshub.controllers;

import com.example.employeeshub.models.RoleModel;
import com.example.employeeshub.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public List<RoleModel> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleModel getRolById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    @GetMapping("/{email}")
    public Optional<RoleModel> findByNombreRol(@PathVariable String nombreRol) {
        return roleService.findByNombreRol(nombreRol);
    }
    @PostMapping
    public RoleModel postRol(@RequestBody RoleModel rol) {
        return roleService.postRol(rol);
    }
    @PutMapping("/{id}")
    public RoleModel putRol(@PathVariable Long id, @RequestBody RoleModel role) {
        return roleService.putRol(id, role);
    }
    @DeleteMapping("/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return roleService.deleteRol(id);
    }

}
