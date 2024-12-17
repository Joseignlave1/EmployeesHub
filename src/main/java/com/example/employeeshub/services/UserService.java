package com.example.employeeshub.services;

import com.example.employeeshub.models.RoleModel;
import com.example.employeeshub.models.UserModel;
import com.example.employeeshub.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.employeeshub.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    public UserService(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public List<UserModel> getAllUsers() {
     return userRepository.findAll();
    }
    //Cuando tengo un endpoint de buscar por id  PUEDO definirlo como optional esto quiere decir que si no
    //encuentra un elemento en los registros que matchee ese id la funcion no tira un error, sino que simplemente no devuelve nada
    public UserModel getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The username was not found"));
    }

    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel postUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel putUser(Long id, UserModel updatedUser) { //Primero encontramos el usuario con el id que queremos modificar
        return userRepository.findById(id)
                .map(user -> {
                    user.setNombre(updatedUser.getNombre());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("The username was not found"));

    }
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (UsernameNotFoundException e) {
            return false; // Si nos salta una excepción no existe un usuario con ese id
        }
    }

    public Optional<UserModel> findByNombreAndEmail(String nombre, String email) {
        return userRepository.findByNombreAndEmail(nombre, email);
    }

    public void addRolToUser(Long user_id, Long role_id) {
        //Verificamos que tanto el usuario como el rol existan.

        UserModel user = userRepository.findById(user_id)
                        .orElseThrow(() -> new UsernameNotFoundException("The username was not found"));
        RoleModel rol = rolesRepository.findById(role_id)
                .orElseThrow(() -> new NoSuchElementException("The rol does not exist"));
        if(!user.getRoles().contains(rol)) {
            user.addRole(rol);
        }
    }

    public void deleteRolToUser(Long user_id, Long role_id) {
        //Verificamos que tanto el usuario como el rol existan.

        UserModel user = userRepository.findById(user_id)
                .orElseThrow(() -> new NoSuchElementException("The username does not exist"));

        RoleModel rol = rolesRepository.findById(role_id)
                .orElseThrow(() ->  new NoSuchElementException("The rol does not exist"));

        if(user.getRoles().contains(rol)) {
            user.deleteRol(rol);
        }
    }
}
