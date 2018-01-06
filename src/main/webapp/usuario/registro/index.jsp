<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Registro - Earth Virtual Shop</title>
        <link href="../../css/usuario.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="container">
            <h2>Registro</h2>
            <div id="registro">
                <form action="Registro" id="registro-form" method="POST">
                    <div class="form-input">
                        <span>Nombre:</span>
                        <input type="text" name="nombre" 
                               <c:if test="${nombre!=null}">
                                   value="${nombre}"
                               </c:if>
                               required>
                    </div>
                    <div class="form-input">
                        <span>Apellido Paterno:</span>
                        <input type="text" name="apePat"
                               <c:if test="${apePat!=null}">
                                   value="${apePat}"
                               </c:if>
                               required>
                    </div>
                    <div class="form-input">
                        <span>Apellido Materno:</span>
                        <input type="text" name="apeMat" 
                               <c:if test="${apeMat!=null}">
                                   value="${apeMat}"
                               </c:if>
                               required>
                    </div>
                    <div class="form-input">
                        <span>Email:</span>
                        <input type="text" name="email" 
                               <c:if test="${email!=null}">
                                   value="${email}"
                               </c:if>
                               required>
                    </div>
                    <div class="form-input">
                        <span>Nombre de usuario:</span>
                        <input type="text" name="usuario" 
                               <c:if test="${usuario!=null}">
                                   value="${usuario}"
                               </c:if>
                               required>
                    </div>
                    <div class="form-input">
                        <span>Contrase&ntilde;a:</span>
                        <input type="password" name="pssw" required>
                    </div>
                    <div class="form-input">
                        <span>Repetir Contrase&ntilde;a:</span>
                        <input type="password" name="repssw" required>
                    </div>
                    <div id="submit">
                        <input type="submit" value="REGISTRARSE">
                    </div>
                    <c:if test="${mensaje!=null}">
                        <div id="mensaje">
                            ${mensaje}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>

    </body>
</html>