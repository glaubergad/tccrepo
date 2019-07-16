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
</head>

<body>

    <div class="container">


        <h1>${titulo}</h1>

        <div class="row">
            <#list graficos as grafico>
                <div id="grafico${grafico_index +1}" class='col-xs-12 col-md-4 col-lg-4'>
                    <strong>${grafico.nome}</strong>
                    <span class="reset" style="display: none;">Selected: <span class="filter"></span></span>
                    <a class="reset" href="javascript:grafico${grafico_index +1}.filterAll();dc.redrawAll();"
                       style="display: none;">reset</a>

                    <div class="clearfix"></div>

                </div>


            </#list>

        </div>

        <div class="row">
            <div class="dc-data-count">
                <span class="filter-count"></span> selected out of <span class="total-count"></span> records | <a
                    href="javascript:dc.filterAll(); dc.renderAll();">Reset All</a>
            </div>
        </div>
        <table class="table table-striped dc-data-table">
        </table>


    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/crossfilter.js"></script>
    <script src="js/d3.js"></script>
    <script src="js/dc.js"></script>
    <script>

    <#list graficos as grafico>
    var grafico${grafico_index+1} = ${grafico.tipo}("grafico${grafico_index+1}");
    </#list>

    /*
    var carTypeChart = dc.rowChart("#carType"),
    gateNameChart = dc.rowChart("#gateName"),
    carPieChart = dc.pieChart("#carTypePie"),
    visCount = dc.dataCount(".dc-data-count"),
    visTable = dc.dataTable(".dc-data-table");
*/
// Full dataset could give issues because of gzip
// var url = "Lekagul Sensor Data.csv.gz";
var url = "../TemplateBasico/data/${arquivo}";

d3.csv(url, function (err, data) {
    if (err) throw err;

    /*
    data.forEach(function (d) {
        d.Timestamp = new Date(d.Timestamp);
    });
    */

    var ndx = crossfilter(data);
    var all = ndx.groupAll();

    <#list graficos as grafico>
    var  grafico${grafico_index+1}Dim = ndx.dimension(function (d) {
        return d["${grafico.atributoX}"];
    });
    var grafico${grafico_index+1}Group = grafico${grafico_index+1}Dim.group();
    grafico${grafico_index+1}
        .dimension(grafico${grafico_index+1}Dim)
        .group(grafico${grafico_index+1}Group)
        .elasticX(true);

    </#list>


    /*
    carTypeChart
        .dimension(carTypeDim)
        .group(carTypeGroup)
        .elasticX(true);

    gateNameChart
        .dimension(gateNameDim)
        .group(gateNameGroup)
        .elasticX(true)
        .data(function (group) {
            return group.top(10);
        });

    carPieChart   
    .dimension(carPieDim)
    .group(carPieGroup)



*/
    visCount
        .dimension(ndx)
        .group(all);

    visTable
        .dimension(grafico1)
        // Data table does not use crossfilter group but rather a closure
        // as a grouping function
        .group(grafico1)
        .columns([
            <#list graficos as grafico>
            "${grafico.atributoX}",
            </#list>

        ]);

    dc.renderAll();

});
	</script>


</body>

</html>