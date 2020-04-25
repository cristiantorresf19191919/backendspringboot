package com.opttimusdev.springbootapirest.controllers;

import com.opttimusdev.springbootapirest.models.entity.Cliente;
import com.opttimusdev.springbootapirest.services.IClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
    @Autowired
    private IClienteService iClienteService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return iClienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = iClienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta de la base de datos");
            response.put("error", Objects.requireNonNull(
                    e.getMessage()).concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (cliente == null) {
            response.put(
                    "mensaje", "El cliente ID:"
                            .concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNuevo = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
           /* List<String> errors = new ArrayList<>();
           for (FieldError cadaError : result.getFieldErrors()){
               errors.add("El campo "+cadaError.getField()+" ' ' "+cadaError.getDefaultMessage());
           }*/
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "el campo '"+err.getField()+" ' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clienteNuevo = iClienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        Cliente clientActual = iClienteService.findById(id);
        Cliente clienteUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if (clientActual == null) {
            response.put("mensaje", "Error no se pudo editar el cliente con el ID: ".concat(id.toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (result.hasErrors()){
           /* List<String> errors = new ArrayList<>();
           for (FieldError cadaError : result.getFieldErrors()){
               errors.add("El campo "+cadaError.getField()+" ' ' "+cadaError.getDefaultMessage());
           }*/
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "el campo '"+err.getField()+" ' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clientActual.setApellido(cliente.getApellido());
            clientActual.setNombre(cliente.getNombre());
            clientActual.setEmail(cliente.getEmail());
            clientActual.setCreatedAt(cliente.getCreatedAt());
            clienteUpdated = iClienteService.save(clientActual);
            response.put("mensaje", "el ciente ha sido actualizado con exito");
            response.put("cliente", clienteUpdated);
        } catch (DataAccessException e) {
            response.put("message", "error al actualizar al cliente en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("test");
        Cliente clientePoderoso = null;
        Map<String, Object> response = new HashMap<>();
        try {
            clientePoderoso = iClienteService.findById(id);
            if (clientePoderoso == null) {
                response.put("mensaje", "el cliente no se encuentra en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            iClienteService.delete(id);
            response.put("mensaje", "cliente eliminado correcamente de la base de datos");
        } catch (DataAccessException e) {
            response.put("mensaje","server error".concat(Objects.requireNonNull(e.getMessage())).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);

    }
}
