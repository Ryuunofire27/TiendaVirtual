package com.charlie.tiendavirtual.servlet;

import com.charlie.tiendavirtual.dao.UsuarioDao;
import com.charlie.tiendavirtual.entity.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletRegistro", urlPatterns = {"/usuario/registro/Registro"})
public class ServletRegistro extends HttpServlet {

    @Inject
    private UsuarioDao usuarioDao;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje;
        String target;

        String nombre = request.getParameter("nombre");
        String apePat = request.getParameter("apePat");
        String apeMat = request.getParameter("apeMat");
        String email = request.getParameter("email");
        String usuario = request.getParameter("usuario");
        String pssw = request.getParameter("pssw");
        String repssw = request.getParameter("repssw");

        mensaje = validar(nombre, apePat, apeMat, email, usuario, pssw, repssw);
        if (mensaje == null) {
            Usuario usu = new Usuario();

            usu.setNombre(nombre);
            usu.setApePat(apePat);
            usu.setApeMat(apeMat);
            usu.setEmail(email);
            usu.setUsuario(usuario);
            usu.setContrasenia(pssw);

            try {
                usu = usuarioDao.insertar(usu);
            } catch (Exception ex) {
                Logger.getLogger(ServletRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }

            target = "index.jsp";

            HttpSession session = request.getSession();
            session.setAttribute("id", session.getId());
            session.setAttribute("usuario", usu.getUsuario());
            session.setAttribute("nombre", usu.getNombre());
            session.setAttribute("apePat", usu.getApePat());
            session.setAttribute("apeMat", usu.getApeMat());
            session.setAttribute("email", usu.getEmail());

            response.sendRedirect("/TiendaVirtual/");
        } else {

            request.setAttribute("nombre", nombre);
            request.setAttribute("apePat", apePat);
            request.setAttribute("apeMat", apeMat);
            request.setAttribute("email", email);
            request.setAttribute("usuario", usuario);
            request.setAttribute("mensaje", mensaje);
            target = "index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    private String validar(String nombre, String apePat, String apeMat,
            String email, String usuario, String pssw, String repssw) {

        StringBuilder respuesta = new StringBuilder("<ul>");
        if (nombre == null) {
            respuesta.append("<li>Falta digitar nombre</li>");
        }
        if (apePat == null) {
            respuesta.append("<li>Falta digitar el apellido paterno</li>");
        }
        if (apeMat == null) {
            respuesta.append("<li>Falta digitar el apellido materno</li>");
        }
        if (email == null) {
            respuesta.append("<li>Falta digitar el email</li>");
        }
        if (usuario == null) {
            respuesta.append("<li>Falta digitar el usuario</li>");
        }
        if (pssw == null) {
            respuesta.append("<li>Falta digitar el password</li>");
        }
        if (repssw == null) {
            respuesta.append("<li>Falta digitar el repassword</li>");
        }
        if (pssw != null && repssw != null && !pssw.equals(repssw)) {
            respuesta.append("<li>password y repassword no coinciden</li>");
        }
        if (usuarioDao.obtenerPorEmail(email) != null) {
            respuesta.append("<li>Ya existe un usuario registrado con el email digitado</li>");
        }
        if (usuarioDao.obtenerPorNomUsuario(usuario) != null) {
            respuesta.append("<li>Ya existe un usuario con ese nombre de usuario registrado</li>");
        }
        respuesta.append("</ul>");
        if (respuesta.toString().equals("<ul></ul>")) {
            return null;
        }
        return respuesta.toString();
    }

}
