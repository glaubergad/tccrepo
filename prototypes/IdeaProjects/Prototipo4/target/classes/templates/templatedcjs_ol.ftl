<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<p>Hello ${titulo}</p>
<table border="1" align="left" >
    <thead>
    <th>Nome</th>
    <th>Tipo</th>
    <th>Atributo</th>
    </thead>
    <tbody>
    <#list graficos as grafico>
        <tr>
            <td>${grafico.nome}</td>
            <td>${grafico.tipo}</td>
            <td>${grafico.atributoX}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>