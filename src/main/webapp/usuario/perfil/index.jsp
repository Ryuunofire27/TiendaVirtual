<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil - Earth Virtual Shop</title>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/perfil.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/header.jspf" %>
        <div id="cuerpo">
            <div id="container">
                <h2>Datos Personales</h2>
                <div id="perfil">
                    <div class="datos">
                        <span>Nombre</span>
                        <span>${nombre}</span>
                    </div>
                    <div class="datos">
                        <span>Apellido Paterno</span>
                        <span>${apePat}</span>
                    </div>
                    <div class="datos">
                        <span>Apellido Materno</span>
                        <span>${apeMat}</span>
                    </div>
                    <div class="datos">
                        <span>Email</span>
                        <span>${email}</span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
