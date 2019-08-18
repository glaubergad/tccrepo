<!doctype html>
<html lang="pt-br">

<head>
    <title>${titulo}</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap e DC CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/dc.css">
    <link rel="stylesheet" href="css/dashgen.css">
    <script src=js/crossfilter.js></script>
    <script src=js/d3.js></script>
    <script src=js/dc.js></script>
</head>

<body>

<div class="container">

    <div class="row dashgen-header" >
    <img src="img/ifpa_navlogo.png"width="64px" height="69px" alt="Logo IFPA"/>
    <h1>${titulo}</h1>
    </div>
    <div class="row">
        <#list graficos as grafico>
            <div id="grafico${grafico_index +1}" class='col-xs-12 col-md-12 col-lg-4'>
                <strong>${grafico.nome}</strong>
                <span class="reset" style="display: none;">Selected: <span class="filter"></span></span>
                <a class="reset" href="javascript:grafico${grafico_index +1}.filterAll();dc.redrawAll();"
                   style="display: none;">reset</a>
                <div class="clearfix"></div>
            </div>
        </#list>
    </div>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>

<!-- Aqui começa a  geração do gráfico -->

<script>

    <#list graficos as grafico>
    var grafico${grafico_index+1} = ${grafico.tipo}("#grafico${grafico_index+1}");
    </#list>


    var url = "data/${arquivo}";

    d3.csv(url, function (err, data) {

        if (err) throw err;

        var ndx = crossfilter(data);
        var all = ndx.groupAll();

        <#list graficos as grafico>
        var grafico${grafico_index+1}Dim = ndx.dimension(function (d) {
            return d["${grafico.atributoX}"];
        });
        <#if grafico.tipo == "dc.barChart">
            var grafico${grafico_index+1}minX = grafico${grafico_index+1}Dim.bottom(1)[0]["${grafico.atributoX}"];
            var grafico${grafico_index+1}maxX = grafico${grafico_index+1}Dim.top(1)[0]["${grafico.atributoX}"];
        </#if>
        </#list>

        //Agrupadores
        <#list graficos as grafico>
        var grafico${grafico_index+1}Group = grafico${grafico_index+1}Dim.group();
        </#list>

        <#list graficos as grafico>
        grafico${grafico_index+1}
            .dimension(grafico${grafico_index+1}Dim)
            .group(grafico${grafico_index+1}Group)
            <#if grafico.tipo == "dc.barChart">
            .x(d3.scale.linear().domain([grafico${grafico_index+1}minX, grafico${grafico_index+1}maxX]))

            </#if>
            <#if grafico.tipo == "dc.rowChart">
                .elasticX(true)
            </#if>
            ;
        </#list>
        dc.renderAll();
    });
</script>

</body>

</html>