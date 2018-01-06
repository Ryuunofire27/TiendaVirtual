<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/producto-img.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/header.jspf" %>
        <div id="cuerpo">
            <div id="container">
                <h2>Producto</h2>
                <div id="producto">
                    <div class="datos">
                        <span>Nombre</span>
                        <span>${nombreP}</span>
                    </div>
                    <div class="datos">
                        <span>Precio</span>
                        <span>${precio}</span>
                    </div>
                    <div class="datos">
                        <span>Stock</span>
                        <span>${stock}</span>
                    </div>
                    <div class="datos">
                        <span>Categoria</span>
                        <span>${categoria}</span>
                    </div>
                    <div class="datos">
                        <span>Marca</span>
                        <span>${marca}</span>
                    </div>
                    <div class="datos">
                        <span>Descuento</span>
                        <span>${descuento}</span>
                    </div>
                    <div class="datos">
                        <span>Nuevo</span>
                        <span>${nuevo}</span>
                    </div>
                    <div class="datos">
                        <span>Descripcion</span>
                        <span>${descripcion}</span>
                    </div>
                    <form action="Producto?accion=img" enctype="multipart/form-data">
                        <div class="imagen">
                            <input type="file" name="imagen">
                        </div>
                        <input type="submit" value="registrar producto">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
