$(function () {
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title : {
            text: '职位发布数量的变化'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['职位数']
        },
        grid:{
            x:40,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} '
                }
            }
        ],
        series : [
            {
                name:'最高职位数',
                type:'line',
                data:[11, 11, 15, 13, 12, 13, 10],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    lineChart.setOption(lineoption);
    $(window).resize(lineChart.resize);

});
