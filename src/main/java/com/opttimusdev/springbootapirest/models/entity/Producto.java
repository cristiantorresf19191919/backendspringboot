package com.opttimusdev.springbootapirest.models.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {
    public Producto() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nombre;
    @Column()
    private Double precio;
    @Column(name = "created_at")
    private Date createdAt;



    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }




    private static final long serialVersionUID = 1l;
}
