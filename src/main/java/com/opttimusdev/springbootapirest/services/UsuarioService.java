package com.opttimusdev.springbootapirest.services;

import com.opttimusdev.springbootapirest.dao.IUsuarioDao;
import com.opttimusdev.springbootapirest.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    // inyectamos el dao que creamos en el usuario
    @Autowired
    private IUsuarioDao IusuarioDao;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = IusuarioDao.findByUsername(username);

        if(usuario ==null){
            logger.error("error en el login: no existe el usuario "+username);
            throw new UsernameNotFoundException("no existe el usuario "+username);
        }
        //convertir List<Rol> a List<GrantedAuthority>
        List<GrantedAuthority> authorities = usuario.getRoles()
                                .stream()
                                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                                .peek(authority -> logger.info("Role "+ authority.getAuthority()))
                                .collect(Collectors.toList());
        return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
    }
    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return IusuarioDao.findByUsername(username);
    }
}
