package com.opttimusdev.springbootapirest.models.entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "no puede estar vacio")
    @Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
    @Column(nullable=false)
    private String  nombre;

    @NotEmpty(message = "no puede estar vacio")
    private String apellido;

    @NotEmpty(message = "no puede estar vacio")
    @Email(message = "el correo no tiene un formato valido")
    @Column(nullable=false, unique = true)
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private static final long serialVersuionUID = 1L;
}
