package com.opttimusdev.springbootapirest.controllers;

import com.opttimusdev.springbootapirest.models.entity.Cliente;
import com.opttimusdev.springbootapirest.services.IClientesServices;
import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClientesServices iClientesServices;

    @GetMapping("/clientes")
    public List<Cliente> index(){
        return iClientesServices.findAll();
    }

    @GetMapping("/clientes/page/{numPag}")
    public Page<Cliente> index(@PathVariable Integer numPag){
        Pageable pageable = PageRequest.of(numPag,4);
        return iClientesServices.findAll(pageable);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Cliente cliente = null;
        Map<String,Object> response = new HashMap<>();
        try{
            cliente = iClientesServices.findById(id);

        } catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (cliente == null){
            response.put("mensaje","el cliente con ID ".concat(id.toString()).concat("no existe en la base de datos"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create (@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente clienteNuevo = null;
        Map<String,Object> response = new HashMap<>();
        if (result.hasErrors()){

        List<String> errores = result.getFieldErrors()
                .stream()
                .map(err -> "el campo ( "+err.getField()+") "+err.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errors",errores);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

    }
        try {
        clienteNuevo = iClientesServices.save(cliente);

        } catch (DataAccessException e){
            response.put("mensaje","error a realizar la consulta en la base de datos");
            response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","el cliente ha sido creado con exito");
        response.put("cliente",clienteNuevo);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Long id ){
    Cliente clienteActual = iClientesServices.findById(id);
    Cliente clienteActualizado = null;
    Map<String,Object> response = new HashMap<>();
    if (clienteActual == null){
        response.put("mensaje","Error con el cliente id "+ id.toString());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    if (result.hasErrors()){
        List<String> errores = result.getFieldErrors()
                .stream()
                .map(err -> "el campo "+ err.getField() + " error = "+ err.getDefaultMessage())
                .collect(Collectors.toList());
        response.put("errores",errores);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            }

    try{
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setEmail(cliente.getEmail());
        clienteActual.setCreatedAt(cliente.getCreatedAt());
        clienteActual.setRegion(cliente.getRegion());
        clienteActualizado = iClientesServices.save(clienteActual);
        response.put("mensaje","cliente ha sido actualizado con exito");
        response.put("cliente",clienteActualizado);

    } catch (DataAccessException e){
        response.put("mensaje","error al actualizar cliente");
        response.put("error", e.getMessage()+" "+e.getMostSpecificCause().getMessage());

    }

    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }





}
