<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/producto.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/header.jspf" %>
        <%@include file="../../WEB-INF/jspf/permisos.jspf"%>
        <div id="cuerpo">
            <div id="container">
            	<h2>Insertar Producto</h2>
                <div id="insertar">
                    <form action="Producto" id="insertar-form" method="POST">
                        <div class="form-input">
                            <span>Nombre</span>
                            <input type="text" name="nombre" required>
                        </div>
                        <div class="form-input">
                            <span>Precio</span>
                            <input type="text" name="precio" required>
                        </div>
                        <div class="form-input">
                            <span>Stock</span>
                            <input type="text" name="stock" required>
                        </div>
                        <div class="form-input">
                            <span>Categoria</span>
                            <select name="categoria">
                                <option value="0">-------</option>
                                <c:if test="${categorias!=null}">
                                    <c:forEach var="categoria" items="${categorias}">
                                        <option value="${categoria.id}">${categoria.nombre}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="form-input">
                            <span>Marca</span>
                            <select name="marca">
                                <option value="0">-------</option>
                                <c:if test="${marcas!=null}">
                                    <c:forEach var="marca" items="${marcas}">
                                        <option value="${marca.id}">${marca.nombre}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="form-input">
                            <span>Descuento</span>
                            <input type="text" name="descuento">
                        </div>
                        <div class="form-input">
                            <span>Nuevo</span>
                            <select name="nuevo">
                                <option value="0">-------</option>
                                <option value="1">SI</option>
                                <option value="2">NO</option>
                            </select>
                        </div>
                        <div class="form-input">
                            <span>Descripcion</span>
                            <textarea name="descripcion"></textarea>
                        </div>
                        <div id="submit">
                            <input type="submit" value="Siguiente">
                        </div>
                        <c:if test="${mensaje!=null}">
                            <div id="mensaje"></div>
                        </c:if>
                    </form>
                </div>
            </div>
            <c:if test="${insertar!=null}">
                <script>
                    if (!confirm("¿Desea ingresar mas productos?")) {
                        window.location = "/TiendaVirtual/";
                    }
                </script>
            </c:if>
        </div>
    </body>
</html>
