package com.opttimusdev.springbootapirest.dao;
import com.opttimusdev.springbootapirest.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {


}
