var carTypeChart = dc.rowChart("#carType"),
    gateNameChart = dc.rowChart("#gateName"),
    carPieChart = dc.pieChart("#carTypePie"),
    visCount = dc.dataCount(".dc-data-count"),
    visTable = dc.dataTable(".dc-data-table");

// Full dataset could give issues because of gzip
// var url = "Lekagul Sensor Data.csv.gz";
var url = "../TemplateBasico/data/lekagul_slice.csv";

d3.csv(url, function (err, data) {
    // Timestamp,car-id,car-type,gate-name
    // 2015-05-01 00:43:28,20154301124328-262,4,entrance3
    // 2015-05-01 01:03:48,20154301124328-262,4,general-gate1
    // 2015-05-01 01:06:24,20154301124328-262,4,ranger-stop2
    // 2015-05-01 01:09:25,20154301124328-262,4,ranger-stop0
    if (err) throw err;

    data.forEach(function (d) {
        d.Timestamp = new Date(d.Timestamp);
    });

    var ndx = crossfilter(data);
    var all = ndx.groupAll();

    var carTypeDim = ndx.dimension(function (d) {
        return d["car-type"];
    });

    var carPieDim = ndx.dimension(function (d) {
        return d["car-type"];
    });

    var gateNameDim = ndx.dimension(function (d) {
        return d["gate-name"];
    });
    var dateDim = ndx.dimension(function (d) {
        return d.Timestamp;
    });

    var carPieGroup = carPieDim.group();
    var carTypeGroup = carTypeDim.group();
    var gateNameGroup = gateNameDim.group();
    var dateGroup = dateDim.group();


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




    visCount
        .dimension(ndx)
        .group(all);

    visTable
        .dimension(dateDim)
        // Data table does not use crossfilter group but rather a closure
        // as a grouping function
        .group(function (d) {
            var format = d3.format('02d');
            return d.Timestamp.getFullYear() + '/' + format((d.Timestamp.getMonth() + 1));
        })
        .columns([
            "Timestamp",
            "car-id",
            "car-type",
            "gate-name"
        ]);

    dc.renderAll();

});