package com.opttimusdev.springbootapirest.controllers;

import com.opttimusdev.springbootapirest.models.entity.Cliente;
import com.opttimusdev.springbootapirest.models.entity.Region;
import com.opttimusdev.springbootapirest.services.IClientesServices;
import com.opttimusdev.springbootapirest.services.IUploadFileService;
import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class    ClienteRestController {

    @Autowired
    private IClientesServices iClientesServices;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUploadFileService iUploadFileService;

    @GetMapping("/generar/{clave}")
    public ResponseEntity<?> hashear(@PathVariable String clave){
        String passwordBcrypt = " ";
        Map<String,Object> response = new HashMap<>();

        for (int i = 0; i < 10; i++){
            passwordBcrypt = passwordEncoder.encode(clave);
        }
        response.put("clave_original",clave);
        response.put("clave_hash",passwordBcrypt);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @GetMapping("/clientes")
    public List<Cliente> index(){
        List<Cliente> clientes = iClientesServices.findAll();
        return clientes;
    }

    @GetMapping("/clientes/page/{numPag}")
    public Page<Cliente> index(@PathVariable Integer numPag){
        Pageable pageable = PageRequest.of(numPag,4);
        return iClientesServices.findAll(pageable);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        Map<String,Object> response=  new HashMap<>();
        Cliente clienteTest = null;
        try {
            Cliente cliente = iClientesServices.findById(id);
            String nombreFotoAnterior = cliente.getFoto();
            Boolean borrado = iUploadFileService.eliminar(nombreFotoAnterior);
            clienteTest = iClientesServices.findById(id);
            if (clienteTest == null){
                response.put("mensaje","el cliente no se encuentra en la base de datos");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            }
            iClientesServices.deleteById(id);
            response.put("mensaje","clente eliminado correctamente de la base de datos");
            }
        catch(DataAccessException e) {
            response.put("mensaje",e.getMessage()+" "+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
    }
    //Subir una foto
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) throws IOException {
        Map<String, Object> response = new HashMap<>();
//        primero encuentre el cliente por el id que viene desde el frontend
        Cliente cliente = iClientesServices.findById(id);
//    si el archivo no es vacio
        if (!archivo.isEmpty()) {
            String nombreArchivo = null;

            try{
                nombreArchivo = iUploadFileService.copiar(archivo, cliente.getId());
            } catch (IOException e){
                response.put("mensaje","error a subir la imagen "+nombreArchivo);
                response.put("error",e.getMessage() + e.getCause().getMessage());
            }
            String nombreFotoAnterior = cliente.getFoto();
            Boolean borrado = iUploadFileService.eliminar(nombreFotoAnterior);
            cliente.setFoto(nombreArchivo);
            iClientesServices.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    //VER FOTO
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Resource recurso = null;
        try{
            recurso = iUploadFileService.cargar(nombreFoto);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
//        CREA LOS HEADERS
        HttpHeaders cabecera = new HttpHeaders();
//        cabecera en el headers para forzar la descarga de imagen en el navegador
//        AGREGA CONTENT DISPOSITION PARA FORZAR QUE EL NAVEGADOR DESCARGUE EL RECURSO
        assert recurso != null;
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \" " + recurso.getFilename() + " \"  ");
//      DEVUELVE LA ENTIDAD RESPUESTA DE TIPO RECURSO JUNTO CON LA CABEZERA Y CODIGO 200
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones() {
        List<Region> regiones = iClientesServices.findAllRegiones();
        return regiones;
    }

}
