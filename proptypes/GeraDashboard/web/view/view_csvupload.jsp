<%-- 
    Document   : view_csvupload
    Created on : 24 de fev de 2019, 12:24:43
    Author     : glaubergad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GeraDashboard - Informe o Arquivo de Dados</title>
    </head>
    <body>
        <h1>Submeta o arquivo de dados!</h1>
        <form name="submit-file" method="POST" enctype="multipart/form-data">
            <p>Selecione o arquivo e clique em submit</p>
            <input type="file" name="arquivo" />
            <input type="submit" name="Enviar" />
        </form>
    </body>
</html>
