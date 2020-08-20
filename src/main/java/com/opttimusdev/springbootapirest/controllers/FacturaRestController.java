package com.opttimusdev.springbootapirest.controllers;

import com.opttimusdev.springbootapirest.models.entity.Factura;
import com.opttimusdev.springbootapirest.models.entity.Producto;
import com.opttimusdev.springbootapirest.services.IClientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
    @Autowired
    private IClientesServices iClientesServices;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return iClientesServices.findFacturaById(id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        iClientesServices.deleteFactura(id);
    }

   @Secured({"ROLE_ADMIN"})
    @GetMapping("/facturas/filtrar-productos/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable String term){

        return iClientesServices.findProductByName(term);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    public Factura crear(@RequestBody Factura factura)
    {
        return iClientesServices.saveFactura(factura);
    }
    @GetMapping("/facturas/productos")
    public ResponseEntity<?> showAllProducts(){
        Map<String,Object> json = new HashMap<>();
        List<Producto> productos = null;
        try{
        productos = iClientesServices.showAllProducts();
            json.put("productos",productos);
            json.put("success","todo bien");
       }
       catch (DataAccessException e){
           json.put("error",e.getMessage()+e.getMostSpecificCause().getMessage());
       }
        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.ACCEPTED);
    }

}
