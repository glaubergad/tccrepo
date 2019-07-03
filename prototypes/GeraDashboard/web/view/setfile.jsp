<%-- 
    Document   : setfile
    Created on : 23 de mar de 2019, 08:46:42
    Author     : glaubergad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerador de Dashboards - Selecionar CSV</title>
    </head>
    <body>
        <h1>Olá!</h1>
        <h3>Seja bem vindo ao sistema gerador de dashboards</h3>
        <form action="../control/controller.jsp" method="POST" enctype = "multipart/form-data">
            <p>Selecione um arquivo CSV para envio:</p>
            <input type = "file" name = "csvfile" size = "50" />
         <br />
         <input type = "submit" value = "Enviar Arquivo" />
      </form>
   </body>
        </form>
    </body>
</html>
