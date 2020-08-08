package com.opttimusdev.springbootapirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regiones")
public class Region {
    public Region() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "agrega region")
    private String nombre;

    private static final long serialVersionUID = 1L;
}
