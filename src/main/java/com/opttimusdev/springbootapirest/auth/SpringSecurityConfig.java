package com.opttimusdev.springbootapirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*habilitar el decorados security para los endpoints en vez de hacerlo programatica en el resource server
mejor asi que ponerlos en el .antMatcher()*/
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
    public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    // al inyectar la interfaz va a buscar la implementacion completa que
    // sea del tipo UserDetailService
    // como tenemos una sola va a inyectar la clase UsuarioService
    @Autowired
    private UserDetailsService usuarioService;


    //@Bean es para reutilizar este metodo en la clase AuthorizationServerConfig
    @Bean
    public BCryptPasswordEncoder metodoPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usuarioService).passwordEncoder(metodoPasswordEncoder());
    }
    //    permisos de los endpoints por el lado de spring security
    //    csrf().disable() -> desabilita el cross side attack porque angular ya lo protege
    //    sessionManagement no es necesario guardar la sesion en el backend, porque el cliente
    //    es el que guarda la informacion del usuario mediante token

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     }
}
