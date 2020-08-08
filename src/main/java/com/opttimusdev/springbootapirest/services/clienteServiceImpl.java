package com.opttimusdev.springbootapirest.services;

import com.opttimusdev.springbootapirest.dao.IClienteDao;
import com.opttimusdev.springbootapirest.dao.IFacturaDao;
import com.opttimusdev.springbootapirest.models.entity.Cliente;
import com.opttimusdev.springbootapirest.models.entity.Factura;
import com.opttimusdev.springbootapirest.models.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class clienteServiceImpl implements IClientesServices {

    @Autowired
    private IClienteDao iClienteDao;

    @Autowired
    private IFacturaDao iFacturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) iClienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return iClienteDao.findAll(pageable);
    }

    @Override
    @Transactional()
    public Cliente save(Cliente cliente) {
        return iClienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)

    public Cliente findById(Long id) {
        return iClienteDao.findById(id).orElse(null);
    }

    @Override
    public List<Region> findAllRegiones() {
        return iClienteDao.findAllRegiones();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iClienteDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findAllFacturas() {
        return (List<Factura>) iFacturaDao.findAll();
        }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return iFacturaDao.save(factura);
    }

    @Override
    @Transactional
    public void deleteFactura(Long id) {
        iFacturaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return iFacturaDao.findById(id).orElse(null);
    }
}
