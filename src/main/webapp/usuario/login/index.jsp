<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login - Earth Virtual Shop</title>
        <link href="../../css/usuario.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="container">
        <h2>Login</h2>
        <div id="login">
            <form action="Login" id="login-form" method="POST">
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
                        <input type="password" name="password" required>
                </div>
                <div id="submit">
                        <input type="submit" value="Ingresar">
                </div>
                <c:if test="${mensaje!=null}">
                    <div id="mensaje">${mensaje}</div>
                </c:if>
            </form>
        </div>
    </div>
    
</body>
</html>