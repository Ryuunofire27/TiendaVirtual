package com.charlie.tiendavirtual.dao;

import com.charlie.tiendavirtual.entity.Usuario;
import java.util.List;
import javax.persistence.Query;

public class UsuarioDao extends GenericDao<Usuario>{

    @Override
    protected Class<Usuario> getClase() {
        return Usuario.class;
    }
    
    public Usuario obtenerPorEmail(String email){
        
        List<Usuario> usuario;
        
        Query query = crearQuery("Select t from Usuario t where t.email = :email");
        
        query.setParameter("email", email);
        
        usuario = (List<Usuario>) query.getResultList();
        
        if(usuario.isEmpty()){
            return null;
        }else{
            return usuario.get(0);
        }
        
    }
    
    public Usuario obtenerPorNomUsuario(String nomUsuario){
        List<Usuario> usuario;
        
        Query query = crearQuery("Select t from Usuario t where t.usuario = :usuario");
        
        query.setParameter("usuario", nomUsuario);
        
        usuario = (List<Usuario>) query.getResultList();
        
        if(usuario.isEmpty()){
            return null;
        }else{
            return usuario.get(0);
        }
        
    }
    
    public Usuario obtenerUsuarioPorLogin(String nomUsuario, String password){
        List<Usuario> usuario;
        
        Query query = crearQuery("Select t from Usuario t where t.usuario = :usuario AND t.contrasenia = :password");
        
        query.setParameter("usuario", nomUsuario);
        query.setParameter("password", password);
        
        usuario =(List<Usuario>) query.getResultList();
        
        if(usuario.isEmpty()){
            return null;
        }else{
            return usuario.get(0);
        }
        
    }
    
}
