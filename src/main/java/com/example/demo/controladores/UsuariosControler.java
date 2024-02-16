package com.example.demo.controladores;

import com.example.demo.entidades.Usuarios;
import com.example.demo.repositorios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController
public class UsuariosControler {

    @Autowired
    UsuariosRepository usuariosRepository;

    /////////////////////////////////////
    // LEER - Obtener todos los autores
    /////////////////////////////////////
    @GetMapping("/usuarios2")
    public List<Usuarios> usuarios() {
        // Recuperar todos los autores de la base de datos
        List<Usuarios> autores = usuariosRepository.findAll();
        return autores; // Devolver la lista de autores
    }


    //////////////////////////////////////////
    // CREAR - Crear un nuevo autor
    /////////////////////////////////////////
    @PostMapping("/usuarios2")
    public Usuarios crear(@RequestBody Usuarios usuarios) {
        return usuariosRepository.save(usuarios); // Guardar el nuevo autor en la base de datos
    }

    ////////////////////////////////////////////////
    // ELIMINAR - Eliminar un autor por ID
    ///////////////////////////////////////////////
    @DeleteMapping("/usuarios2/{id}")
    public ResponseEntity<Boolean> eliminarAutor(@PathVariable int id) {
        // Buscar el autor por ID
        Optional<Usuarios> usuarios = usuariosRepository.findById(id);

        // Verificar si el autor existe
        if (usuarios.isPresent()) {
            usuariosRepository.delete(usuarios.get()); // Eliminar el autor de la base de datos
            return ResponseEntity.ok(true); // Devolver éxito
        } else {
            return ResponseEntity.ok(false); // Devolver falso si el autor no existe
        }
    }


    ///////////////////////////////////////////////////////////
    // EDITAR - Actualizar la información de un autor por ID
    //////////////////////////////////////////////////////////
    @PutMapping("/usuarios2/{id}")
    public ResponseEntity<Usuarios> updateUser(@PathVariable int id, @RequestBody Usuarios usuariosData) {
        // Buscar el autor por ID
        Optional<Usuarios> optionalAutor = usuariosRepository.findById(id);

        // Verificar si el autor existe
        if (optionalAutor.isPresent()) {
            Usuarios usuarios = optionalAutor.get();

            // Actualizar los campos del autor con los datos proporcionados
            usuarios.setNombre(usuariosData.getNombre());
            usuarios.setApellido(usuariosData.getApellido());
            usuarios.setEmail(usuariosData.getEmail());

            // Guardar los cambios en la base de datos
            Usuarios userSaved = usuariosRepository.save(usuarios);
            return ResponseEntity.ok(userSaved); // Devolver el autor actualizado
        } else {
            return ResponseEntity.notFound().build(); // Devolver error si el autor no existe
        }
    }

}