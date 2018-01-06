/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlie.tiendavirtual.servlet;

import com.charlie.tiendavirtual.dao.UsuarioDao;
import com.charlie.tiendavirtual.entity.Usuario;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletLogin", urlPatterns = {"/usuario/login/Login"})
public class ServletLogin extends HttpServlet {

    @Inject
    private UsuarioDao usuarioDao;
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String mensaje;
        
        String nomUsuario = request.getParameter("usuario");
        String password = request.getParameter("password");
       
        mensaje = validar(nomUsuario, password);
        
        if(mensaje==null){
            Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(nomUsuario, password);
            if(usuario==null){
                mensaje="<ul><li>Usuario no registrado</li></ul>";
                request.setAttribute("usuario", usuario);
                request.setAttribute("mensaje", mensaje);
            }else{
                request.getSession().setAttribute("id", request.getSession().getId());
                request.getSession().setAttribute("usuario", usuario.getUsuario());
                request.getSession().setAttribute("nombre", usuario.getNombre());
                request.getSession().setAttribute("apePat", usuario.getApePat());
                request.getSession().setAttribute("apeMat", usuario.getApeMat());
                request.getSession().setAttribute("email", usuario.getEmail());
                if(usuario.getIdpermiso()==2 || usuario.getIdpermiso()==3){
                    request.getSession().setAttribute("admi", usuario.getIdpermiso());
                }
                
                response.sendRedirect("/TiendaVirtual/");
            }
        }else{
            request.setAttribute("mensaje", mensaje);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }
    
    private String validar(String nomUsuario, String password){
        StringBuilder mensaje = new StringBuilder("<ul>");
        if(nomUsuario==null){
            mensaje.append("<li>Falta digitar usuario</li>");
        }
        if(password == null){
            mensaje.append("<li>Falta digitar password</li>");
        }
        mensaje.append("</ul>");
        if(mensaje.toString().equals("<ul></ul>")){
            return null;
        }
        return mensaje.toString();
    }

}
