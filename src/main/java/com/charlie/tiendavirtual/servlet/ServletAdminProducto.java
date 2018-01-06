/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlie.tiendavirtual.servlet;

import com.charlie.tiendavirtual.dao.CategoriaDao;
import com.charlie.tiendavirtual.dao.ImagenDao;
import com.charlie.tiendavirtual.dao.MarcaDao;
import com.charlie.tiendavirtual.dao.ProductoDao;
import com.charlie.tiendavirtual.entity.Categoria;
import com.charlie.tiendavirtual.entity.Imagen;
import com.charlie.tiendavirtual.entity.Marca;
import com.charlie.tiendavirtual.entity.Producto;
import com.charlie.tiendavirtual.util.StorageTV;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "ServletImg", urlPatterns = {"/producto/admi/Producto"})
@MultipartConfig(location = "/tmp")
public class ServletAdminProducto extends HttpServlet {

    @Inject
    private CategoriaDao categoriaDao;

    @Inject
    private MarcaDao marcaDao;

    @Inject
    private ProductoDao productoDao;
    
    @Inject
    private ImagenDao imagenDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Marca> marcas = marcaDao.obtenerLista();
        List<Categoria> categorias = categoriaDao.obtenerLista();
        if (marcas.isEmpty()) {
            marcas = null;
        }
        if (categorias.isEmpty()) {
            categorias = null;
        }
        request.setAttribute("marcas", marcas);
        request.setAttribute("categorias", categorias);

        request.getRequestDispatcher("productoIns.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje = null;
        String target = "Producto";
        String accion = request.getParameter("accion");
        if (accion == null) {
            String nombre = request.getParameter("nombre");
            Double precio = request.getParameter("precio") != null
                    ? Double.parseDouble(request.getParameter("precio")) : null;
            Integer stock = request.getParameter("stock") != null
                    ? Integer.parseInt(request.getParameter("stock")) : null;
            String categoria = request.getParameter("categoria");
            String marca = request.getParameter("marca");
            Double descuento = request.getParameter("descuento") != null
                    ? Double.parseDouble(request.getParameter("descuento")) : null;
            Integer nuevo = request.getParameter("nuevo") != null
                    ? Integer.parseInt(request.getParameter("nuevo")) : null;
            String descripcion = request.getParameter("descripcion");

            mensaje = validar(nombre, precio, stock, categoria, marca, nuevo);

            if (mensaje == null) {
                Producto producto = new Producto();
                producto.setId();
                producto.setIdCat(categoria);
                producto.setIdMar(marca);
                producto.setStock(stock);
                producto.setDescripcion(descripcion);
                producto.setDescuento(descuento);
                producto.setNombre(nombre);
                producto.setNuevo(nuevo);
                producto.setPrecio(precio);
                try {
                    producto = productoDao.insertar(producto);
                    request.getSession().setAttribute("idProducto", producto.getId());
                    request.setAttribute("insertar", "exitoso");
                    target = "insImg.jsp";
                } catch (Exception ex) {
                    request.setAttribute("mensaje", mensaje);
                }
                
            }
            request.setAttribute("nombreP", nombre);
            request.setAttribute("precio", precio);
            request.setAttribute("stock", stock);
            request.setAttribute("categoria", categoria);
            request.setAttribute("marca", marca);
            request.setAttribute("descuento", descuento);
            request.setAttribute("nuevo", nuevo);
            request.setAttribute("descripcion", descripcion);

        } else {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                StorageTV storage = new StorageTV();
                List<FileItem> list = recogeParam(request);
                Iterator<FileItem> ite = list.iterator();
                while (ite.hasNext()) {
                    FileItem item = ite.next();
                    String name = item.getName();
                    String contentType = item.getContentType();

                    String url = storage.subirImagen(name, item.get(), contentType);

                    if (url != null) {
                        Imagen imagen = new Imagen();
                        imagen.setIdproducto(String.valueOf(request.getSession().getAttribute("idProducto")));
                        imagen.setUrl(url);
                        request.getSession().removeAttribute("idProducto");
                        try {
                            imagenDao.insertar(imagen);
                        } catch (Exception ex) {
                            mensaje = "<ul><li>Problema al subir imagen</li></ul>";
                        }
                    }else{
                        mensaje = "<ul><li>Problema al subir imagen</li></ul>";
                        request.setAttribute("mensaje", mensaje);
                    }
                }
            }
        }
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher(target).forward(request, response);
    }

    private List<FileItem> recogeParam(HttpServletRequest request) {
        List<FileItem> list = null;

        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            list = upload.parseRequest(request);

        } catch (FileUploadException ex) {
        }

        return list;
    }

    private String validar(String nombre, Double precio,
            Integer stock, String categoria, String marca,
            Integer nuevo) {

        StringBuilder mensaje = new StringBuilder("<ul>");

        if (nombre == null) {
            mensaje.append("<li>Falta digitar el nombre del producto</li>");
        }
        if (precio == null) {
            mensaje.append("<li>Falta digitar el precio del producto</li>");
        }
        if (stock == null) {
            mensaje.append("<li>Falta digitar el stock del producto</li>");
        }
        if (categoria == null || categoria.equals("0")) {
            mensaje.append("<li>Falta seleccionar la categoria del producto</li>");
        }
        if (marca == null || marca.equals("0")) {
            mensaje.append("<li>Falta seleccionar la marca del producto</li>");
        }
        if (nuevo == null || nuevo == 0) {
            mensaje.append("<li>Falta seleccionar si el producto es nuevo</li>");
        }
        mensaje.append("</ul>");
        if (mensaje.toString().equals("<ul></ul>")) {
            return null;
        }
        return mensaje.toString();
    }

}
