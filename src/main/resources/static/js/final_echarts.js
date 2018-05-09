$(function () {
    $('.full-height-scroll').slimScroll({
        height: '100%'
    });


    $.post("/rest/query/v1/queryNumDayPositionTypes",function(data){
        if(data.length > 0){
            var positionTrend  = $("#positionTrend");
            for(var i=0;i<data.length;i++){
                var option = $(" <option>"+data[i]+"</option>");
                positionTrend.append(option);
            }
        }
    })
    $.post("/rest/query/v1/queryKeyWordPositionTypes",function(data){
        if(data.length > 0){
            var keyWordSelct  = $("#keyWord");
            for(var i=0;i<data.length;i++){
                var option = $(" <option>"+data[i]+"</option>");
                keyWordSelct.append(option)
            }
        }
    });

    function  positionCurrDate(date) {
        var array = [];
        for(var i=0;i<date.length;i++){
            array.push(date[i].currDate)
        }
        return array;
    }
    function  positionNums(date) {
        var array = [];
        for(var i=0;i<date.length;i++){
            array.push(date[i].positionNum)
        }
        return array;
    }

    var lineChart = echarts.init(document.getElementById("echarts-line-day"));

    $("#positionTrendButton").click(function () {
        var postionTypeName = $('#positionTrend option:selected').text();
        //昨日
        $.post("/rest/query/v1/queryDateRangPositionNumDay?positionType="+postionTypeName,function (data) {
            if(data.length > 0 ){
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
                            data : positionCurrDate(data)
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            axisLabel : {
                                formatter: '{value} '
                            }
                        }
                    ],dataZoom: [
                        {
                            type: 'slider',	//支持鼠标滚轮缩放
                            start: 0,			//默认数据初始缩放范围为10%到90%
                            end: 100
                        },
                        {
                            type: 'inside',	//支持单独的滑动条缩放
                            start: 0,			//默认数据初始缩放范围为10%到90%
                            end: 100
                        }
                    ],
                    series : [
                        {
                            name:'最高职位数',
                            type:'line',
                            data:positionNums(data),
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
            }else{

            }
        })
        //本月
        $.post("/rest/query/v1/queryDateRangPositionNumDaySum?positionType="+postionTypeName,function (data) {
            if(data.length > 0 ){
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
                            data : positionCurrDate(data)
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
                            data:positionNums(data),
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
                var lineChartMonth = echarts.init(document.getElementById("echarts-month"));
                lineChartMonth.setOption(lineoption);
                $(window).resize(lineChartMonth.resize);
            }else{

            }
        })

    })





    $("#keyWordButton").click(function () {
        var postionTypeName = $('#keyWord option:selected').text().trim();
        if(postionTypeName == "C"){
            postionTypeName = "C++";
        }
        //上月
        $.post("/rest/query/v1/queryLastMonthPositionKeyWord?positionType="+postionTypeName,function (data) {
            var keyWordtboday = $("#keyWordtboday");
            if(data.length > 0 ){
                keyWordtboday.html("");
                for(var i=0;i<data.length;i++){
                    var tr =  $("  <tr>\n" +
                        " <td>"+(i+1)+"</td>\n" +
                        " <td> "+data[i].keywordName+"</td>\n" +
                        "<td> "+data[i].keywordNum+"</td>\n" +
                        "<td class=\"client-status\"><span class=\"label label-primary\">有效</span>\n" +
                        "</td>\n" +
                        " </tr>")
                    keyWordtboday.append(tr);
                }
            }else{
                keyWordtboday.html("没有数据！");
            }
        })
        //本月
        $.post("/rest/query/v1/queryDataRangePositionKeyWordNums?positionType="+postionTypeName,function (data) {
            var keyWordtboday = $("#keyWordtbodayMonth");
            if(data.length > 0 ){
                keyWordtboday.html("");
                for(var i=0;i<data.length;i++){
                    var tr =  $("  <tr>\n" +
                        " <td>"+(i+1)+"</td>\n" +
                        " <td> "+data[i].keywordName+"</td>\n" +
                        "<td> "+data[i].keywordNum+"</td>\n" +
                        "<td class=\"client-status\"><span class=\"label label-primary\">有效</span>\n" +
                        "</td>\n" +
                        " </tr>")
                    keyWordtboday.append(tr);
                }
            }else{
                keyWordtboday.html("没有数据！");
            }
        })

    })





});
