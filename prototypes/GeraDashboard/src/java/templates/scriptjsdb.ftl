<#import "macros/ifpagen-macros.ftl" as bp>

<@bp.boilerplate>


var graph1Chart = ${graph1.tipo}("#ifpagen-graph1"),
graph2Chart = ${graph2.tipo}("#ifpagen-graph2"),
graph3Chart = ${graph3.tipo}("#ifpagen-graph3"),
visTable = dc.dataTable("#ifpagen-table");

var url = "data/${dadosUsr.url}";

d3.csv(url, function (err, data) {


var ndx = crossfilter(data);
var all = ndx.groupAll();

var graph1Dim = ndx.dimension(function (d) { return d["${graph1.dimensao}"]; });
var graph2Dim = ndx.dimension(function (d) { return d["${graph2.dimensao}"]; });
var graph3Dim = ndx.dimension(function (d) { return d["${graph3.dimensao}"]; });

var graph1Group = graph1Dim.group();
var graph2Group = graph2Dim.group();
var graph3Group = graph3.group();


graph1Chart
.dimension(graph1Dim)
.group(graph1Group)
.elasticX(true);

gateNameChart
.dimension(graph2Dim)
.group(graph2Group)
.elasticX(true)
.data(function (group) { return group.top(10); });


visCount
.dimension(ndx)
.group(all);

visTable
.dimension(graph1Dim)
// Data table does not use crossfilter group but rather a closure
// as a grouping function
.columns([
"${graph1.dimensao}",
"${graph2.dimensao}",
"${graph3.dimensao}"
]);

dc.renderAll();

});
          
</@bp.boilerplate>