package com.opttimusdev.springbootapirest.services;

import com.opttimusdev.springbootapirest.controllers.ClienteRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    // loguear por consola
    Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        //        solo manda mensajitos por consola
        logger.info(rutaArchivo.toString());
        Resource recurso = null;
//        no hay necesidad de try catch porque arriba se lanza el throws exception
        recurso = new UrlResource(rutaArchivo.toUri());
        if (!recurso.exists() && !recurso.isReadable()) {
//                si se borro el archivo o no existe cree la ruta con el icono por defecto
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();
//                y sobreescribe el nuevo recurso
            //            cea el recurco con la ruta como una URL
            // vuelve y juega no hay necesidad de try catch porque esta arriba
            recurso = new UrlResource(rutaArchivo.toUri());
            logger.error("se borro la imagen no existe");
        }
        return recurso;

    }

    @Override
    public boolean eliminar(String nombreFoto) {

        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior = getPath(nombreFoto);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }

    @Override
    public String copiar(MultipartFile archivo, Long id) throws IOException {
        String nombreFoto = UUID.randomUUID().toString() + "_userid__" + id + "__" + archivo.getOriginalFilename().replace(" ", "");

        //            generar un id unico pasarlo a string y tener el nombre de la foto y quitarle los espacios en blanco
        assert nombreFoto != null;
        // crea la ruta con el nombre de la foto
        Path ruta = getPath(nombreFoto);
        logger.info("sube la foto borra si ya hay una");
        logger.info(ruta.toString());
        // aca no usamos try catch porque esta arriba el throws IOException
        // copia el archivo
        Files.copy(archivo.getInputStream(), ruta);
        return nombreFoto;
    }
}
