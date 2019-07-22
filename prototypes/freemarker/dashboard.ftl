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
    <script src=js/crossfilter.js> </script> <script src=js/d3.js> </script> <script src=js/dc.js> </script> </head>
        <body>

    <div class="container">


        <h1>${titulo}</h1>

        <div class="row">
                <div id="grafico1" class='col-xs-12 col-md-12 col-lg-4'>
                    <strong>Sexo Entrevistados</strong>
                    <span class="reset" style="display: none;">Selected: <span class="filter"></span></span>
                    <a class="reset" href="javascript:grafico1.filterAll();dc.redrawAll();"
                       style="display: none;">reset</a>

                    <div class="clearfix"></div>

                </div>
                <div id="grafico2" class='col-xs-12 col-md-12 col-lg-4'>
                    <strong>Categoria</strong>
                    <span class="reset" style="display: none;">Selected: <span class="filter"></span></span>
                    <a class="reset" href="javascript:grafico2.filterAll();dc.redrawAll();"
                        style="display: none;">reset</a>
    
                    <div class="clearfix"></div>
                </div>

                <div id="grafico3" class='col-xs-12 col-md-12 col-lg-4'>
                    <strong>Classe Funcional</strong>
                    <span class="reset" style="display: none;">Selected: <span class="filter"></span></span>
                    <a class="reset" href="javascript:grafico3.filterAll();dc.redrawAll();"
                        style="display: none;">reset</a>
    
                    <div class="clearfix"></div>
                </div>
        </div>

    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery-3.4.1.min.js"></script>

    <!-- Aqui começa a  geração do gráfico -->

    <script>
        var grafico1 = dc.pieChart("#grafico1");
        var grafico2 = dc.rowChart("#grafico2");
        var grafico3 = dc.barChart("#grafico3");

        // Full dataset could give issues because of gzip
        // var url = "Lekagul Sensor Data.csv.gz";
        var url = "../proto5/data/aposentadoria.csv";

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

            var grafico1Dim = ndx.dimension(function (d) {
                return d["categoria"];
            });

            var grafico2Dim = ndx.dimension(function (d) {
                return d["sexo"];
            });

            var grafico3Dim = ndx.dimension(function (d) {
                return d["previsao_idade"];
            });

            var grafico1Group = grafico1Dim.group().reduceCount();
            var grafico2Group = grafico2Dim.group().reduceCount();
            var grafico3Group = grafico3Dim.group();

            var graf3minX = grafico3Dim.bottom(1)[0]["previsao_idade"];
            var graf3maxX = grafico3Dim.top(1)[0]["previsao_idade"];
            
            
            grafico1
                .dimension(grafico1Dim)
                .group(grafico1Group)
                .on('renderlet', function(chart) {
                  chart.selectAll('rect').on('click', function(d) {
                     console.log('click!', d);
                  });
               });

            grafico2
                .dimension(grafico2Dim)
                .group(grafico2Group)
                .elasticX(true);
            
            grafico3
                .x(d3.scale.linear().domain([graf3minX,graf3maxX]))
                .dimension(grafico3Dim)
                .group(grafico3Group);

            dc.renderAll();

        });
    </script>

    </body>

</html>