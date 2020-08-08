package com.opttimusdev.springbootapirest.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {
    public Factura(){
        this.items = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String observacion;
    @Column
    private String descripcion;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "facturas",cascade = CascadeType.ALL)
    @JoinColumn(name = "facturas_id")
    private List<ItemFactura> items;





    private static final long serialVersionUID = 1l;



}
