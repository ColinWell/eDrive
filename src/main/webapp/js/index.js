/**
 * Created by Colin on 2018/5/19.
 */

$(function () {
    var jsonData = $.ajax({
        url: '/user/getRelationUncheckNum.do',
        dataType: 'json',
        method:'GET'
    }).done(function (results) {
       $('#uncheckNum').html(results);
    });
});
$(function() {
    //使用Ajax获取json数据
    var jsonData = $.ajax({
        url: '/payment/getCurrentMonthInfo.do?paymentType=2',
        dataType: 'json',
        method:'GET'
    }).done(function (results) {
        var ctx, data, myPolarAreaChart, option_bars;
        Chart.defaults.global.responsive = true;
        ctx = $('#polar-area-chart').get(0).getContext('2d');
        option_bars = {
            scaleShowLabelBackdrop: true,
            scaleBackdropColor: "rgba(255,255,255,0.75)",
            scaleBeginAtZero: true,
            scaleBackdropPaddingY: 2,
            scaleBackdropPaddingX: 2,
            scaleShowLine: true,
            segmentShowStroke: true,
            segmentStrokeColor: "#fff",
            segmentStrokeWidth: 2,
            animationSteps: 100,
            animationEasing: "easeOutBounce",
            animateRotate: true,
            animateScale: false,
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
        };
        data = [
            {
                value: results['paid'],
                color: "#22A7F0",
                highlight: "#22A7F0",
                label: "已缴费"
            },
            {
                value: results['unPaid'],
                color: "#FA2A00",
                highlight: "#FA2A00",
                label: "未缴费"
            }

        ];
        myPolarAreaChart = new Chart(ctx).PolarArea(data, option_bars);
    });
});

// 饼图，缴费数量比例
$(function(){
    //使用Ajax获取json数据
    var jsonData = $.ajax({
        url: '/payment/getCurrentMonthInfo.do?paymentType=1',
        dataType: 'json',
        method:'GET'
    }).done(function (results) {

        var ctx, data, myLineChart, options;
        Chart.defaults.global.responsive = true;
        ctx = $('#pie-chart').get(0).getContext('2d');
        options = {
            showScale: false,
            scaleShowGridLines: false,
            scaleGridLineColor: "rgba(0,0,0,.05)",
            scaleGridLineWidth: 0,
            scaleShowHorizontalLines: false,
            scaleShowVerticalLines: false,
            bezierCurve: false,
            bezierCurveTension: 0.4,
            pointDot: false,
            pointDotRadius: 0,
            pointDotStrokeWidth: 2,
            pointHitDetectionRadius: 20,
            datasetStroke: true,
            datasetStrokeWidth: 4,
            datasetFill: true,
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };

        data = [
            {
            value: results['paid'],
            color: "#1ABC9C",
            highlight: "#1ABC9C",
            label: "已缴费"
            },
            {
                value: results['unPaid'],
                color: "#FA2A00",
                highlight: "#FA2A00",
                label: "未缴费"
            }
        ];
        myLineChart = new Chart(ctx).Pie(data, options);
        // Chart.defaults.global.responsive = true;
        // var data = {
        //     labels: [
        //         "未缴费",
        //         "已缴费"
        //     ],
        //     datasets: [
        //         {
        //             data: [results['unPaid'],results['paid']],
        //             backgroundColor: [
        //                 "#FF6384",
        //                 "#36A2EB"
        //             ],
        //             hoverBackgroundColor: [
        //                 "#FF6384",
        //                 "#36A2EB"
        //             ]
        //         }]
        // };
        //
        // var parameters={
        //     type:"pie",
        //     data:data,
        //     options:{
        //         showScale: false,
        //         scaleShowGridLines: false,
        //         scaleGridLineColor: "rgba(0,0,0,.05)",
        //         scaleGridLineWidth: 0,
        //         scaleShowHorizontalLines: false,
        //         scaleShowVerticalLines: false,
        //         bezierCurve: false,
        //         bezierCurveTension: 0.4,
        //         pointDot: false,
        //         pointDotRadius: 0,
        //         pointDotStrokeWidth: 2,
        //         pointHitDetectionRadius: 20,
        //         datasetStroke: true,
        //         datasetStrokeWidth: 4,
        //         datasetFill: true,
        //         legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        //     }
        // };
        //
        // var ctx = $('#pie-chart').get(0).getContext('2d');
        // var doughnut=new Chart(ctx,parameters);
    })
});


