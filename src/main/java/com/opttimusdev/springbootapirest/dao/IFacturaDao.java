package com.opttimusdev.springbootapirest.dao;

import com.opttimusdev.springbootapirest.models.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacturaDao extends JpaRepository<Factura,Long> {

}
