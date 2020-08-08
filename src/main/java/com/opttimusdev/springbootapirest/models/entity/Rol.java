package com.opttimusdev.springbootapirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
    public Rol(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String nombre;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    private static final long serialVersuionUID = 1L;

        /*no hay necesidad de que sea bidireccional
    a partir de usuarios se sacan los rolos
    si descomento aca se puede sacar usuarios a partir de los
    roles*/
 /*   @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuario;
*/
    /*public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }*/
}
