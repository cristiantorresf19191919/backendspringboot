package com.opttimusdev.springbootapirest.services;

import com.opttimusdev.springbootapirest.models.entity.Cliente;

import com.opttimusdev.springbootapirest.models.entity.Factura;
import com.opttimusdev.springbootapirest.models.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IClientesServices {

    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente save(Cliente cliente);
    public Cliente findById(Long id);
    public List<Region> findAllRegiones();
    public void deleteById(Long id);

    public List<Factura> findAllFacturas();
    public Factura saveFactura(Factura factura);
    public void deleteFactura(Long id);
    public Factura findFacturaById(Long id);



}
