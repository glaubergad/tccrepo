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


    //var grafico1 = dc.pieChart("#grafico1");
    //var grafico2 = dc.rowChart("#grafico2");
    //var grafico3 = dc.barChart("#grafico3");

    // Full dataset could give issues because of gzip
    // var url = "Lekagul Sensor Data.csv.gz";
    var url = "data/${arquivo}";

    d3.csv(url, function (err, data) {
        /*
        siape,nome_oficial,sexo,categoria,classe_funcional,descricao_cargo,unidade,locacao,data_previsao_aposentadoria,previsao_idade,regra_aposentadoria
        345783,ABMAEL BEZERRA DE OLIVEIRA,M,Docente,Classe C - Adjunto,PROFESSOR DO MAGISTERIO SUPERIOR,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,20/01/2003,53,Voluntária com proventos proporcionais
        345783,ABMAEL BEZERRA DE OLIVEIRA,M,Docente,Classe C - Adjunto,PROFESSOR DO MAGISTERIO SUPERIOR,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,12/05/2005,55,Voluntária com proventos reduzidos - Art. 2º da EC41/2003
        345783,ABMAEL BEZERRA DE OLIVEIRA,M,Docente,Classe C - Adjunto,PROFESSOR DO MAGISTERIO SUPERIOR,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,DEPARTAMENTO DE ENGENHARIA ELÉTRICA,17/04/2007,57,Voluntária com proventos integrais e paridade - Art 3º da EC47/2005

        */
        if (err) throw err;
        /*
        data.forEach(function (d) {
            d.Timestamp = new Date(d.Timestamp);
        });
        */

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