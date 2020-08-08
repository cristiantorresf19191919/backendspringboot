package com.opttimusdev.springbootapirest.models.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    public Usuario(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60)
    private String password;

    private Boolean enabled;

    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;

    //    aca vamos a relaciones las clases roles con las de usuario
//    una relacion de muchos a muchos
    // cuando crea un manytomany crea una tabla intermedia
    // y el nombre concatena el nombre de las tablas usuarios __ y roles
    // CascadeType.all significa que cada vez que se elimine el usuario
    // se van a eliminar todos los roles automaticamente
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //    // que no se pueda repetir
    @JoinTable
            (name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"),
                    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})}
            )
    private List<Rol> roles;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    private static final long serialVersuionUID = 1L;
}