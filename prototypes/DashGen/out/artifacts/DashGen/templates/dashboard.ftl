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
        <img src="img/ifpa_navlogo.png" width="120px" height="120px" alt="Logo IFPA"/>
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
    <div class="row dashgen-footer text-right">Gerado Automaticamente pelo Dashgen. IPFA 2020</div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- Aqui começa a  geração dos gráficos -->
<script>

    //instanciacao e identificacao do local de exibicao no DOM
    <#list graficos as grafico>
    var grafico${grafico_index+1} = ${grafico.tipo}("#grafico${grafico_index+1}");
    </#list>

    //Especificacao da fonte de dados
    var url = "data/${arquivo}";

    d3.csv(url, function (err, data) {
        if (err) throw err;
        var ndx = crossfilter(data);

        //Estabelecimento da dimensao principal do grafico
        <#list graficos as grafico>
        var grafico${grafico_index+1}Dim = ndx.dimension(d => d.${grafico.atributoX});
        </#list>

        //Redutores
        <#list graficos as grafico>
        <#if grafico.grouping == 1>
        var grafico${grafico_index+1}Group = grafico${grafico_index+1}Dim.group().reduceSum(d => d.${grafico.atributoY});
        var grafico${grafico_index+1}minX = grafico${grafico_index+1}Dim.bottom(1)[0]["${grafico.atributoY}"];
        var grafico${grafico_index+1}maxX = grafico${grafico_index+1}Group.top(1).value;
        <#else>
        var grafico${grafico_index+1}Group = grafico${grafico_index+1}Dim.group();
        var grafico${grafico_index+1}minX = grafico${grafico_index+1}Dim.bottom(1)[0]["${grafico.atributoX}"];
        var grafico${grafico_index+1}maxX = grafico${grafico_index+1}Dim.top(1)[0]["${grafico.atributoX}"];
        </#if>
        </#list>

        //Parametros especificos de cada grafico
        <#list graficos as grafico>
        grafico${grafico_index+1}
        <#if grafico.tipo != "dc.lineChart">
            //Exibe os quatro atributos com valores mais altos, agrupando os demais em Others
            .cap(4)
            </#if>
            .dimension(grafico${grafico_index+1}Dim)
            .group(grafico${grafico_index+1}Group)
            <#if grafico.tipo == "dc.lineChart">
            //Define a escala do eixo X baseado nos valores minimo e maximo do agrupamento de reducao.
            .x(d3.scale.linear().domain([grafico${grafico_index+1}minX, grafico${grafico_index+1}maxX]))
            </#if>
            <#if grafico.tipo == "dc.rowChart">
            //Garante o reajuste automatico do eixo X
            .elasticX(true)
        </#if>
        ;
        </#list>
        dc.renderAll();
    });
</script>
</body>
</html>