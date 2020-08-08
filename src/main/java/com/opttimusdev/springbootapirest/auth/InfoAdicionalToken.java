package com.opttimusdev.springbootapirest.auth;

import com.opttimusdev.springbootapirest.models.entity.Usuario;
import com.opttimusdev.springbootapirest.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
// tiene que ser un componente de string

@Component
public class InfoAdicionalToken implements TokenEnhancer {
//    esta clase solo va a agregar informacion adicional al token
    @Autowired
    private IUsuarioService usuarioService;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) throws UnsupportedOperationException{

        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        // con el objeto authentication puedo tener el usuario autenticado
        Map<String,Object> info = new HashMap<>();
        info.put("username", usuario.getUsername());
        info.put("nombre" ,usuario.getNombre());
        info.put("apellido",usuario.getApellido());
        info.put("email",usuario.getApellido());
        info.put("enabled", usuario.getEnabled());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;

    }




}

