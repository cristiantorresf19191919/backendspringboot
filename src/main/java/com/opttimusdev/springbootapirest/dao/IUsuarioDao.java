package com.opttimusdev.springbootapirest.dao;

import com.opttimusdev.springbootapirest.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface IUsuarioDao extends CrudRepository<Usuario, Id> {
    // select Usuario where username= parametro
    //public Usuario findByUsernameAndEmail para agregar
    // mas palabras

    public Usuario findByUsername(String username);
    // aca si le podemos meter el nombre que queramos
    // porque usamos la notacion query
    // select objeto usuario
    // select username from usuarios where username = 'leopoldo'
    // select username from Usuarios where username = 'leopoldo'
    @Query("select u from Usuario u where u.username=?1")
    public Usuario findByUsername2(String username);



}
