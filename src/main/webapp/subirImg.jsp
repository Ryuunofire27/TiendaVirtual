<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form action="ServletImg" method="POST"
          enctype="multipart/form-data">
      <input type="file" name="foto">
      <input type="submit" value="enviar">
    </form>
  </body>
</html>
