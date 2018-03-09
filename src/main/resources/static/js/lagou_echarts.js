$(function () {

    function  city(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].city)
        }
        return array;
    }

    function  positionNum(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].positionNum)
        }
        return array;
    }
    $.post("/rest/v1/queryCityCompanNum",function(resultData){
        /**
         * 柱状图
         */
        var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
        barChart.showLoading();
        var baroption = {
            title : {
                text: '公司的数量信息'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['公司的数量']
            },
            grid:{
                x:40,
                x2:24,
                y2:24
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : city(resultData)
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true
                }
            ],
            series : [
                {
                    name:'公司的数量',
                    type:'bar',
                    data:positionNum(resultData),
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
        barChart.hideLoading();
        barChart.setOption(baroption);

        window.onresize = barChart.resize;
    });
    function industryName(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].industryField)
        }
        return array;
    }
    /**
     * 全国行业信息
     */
    $.post("/rest/v1/queryIndustryCompanNum",function(resultData){
        /**
         * 柱状图
         */
        var barChart = echarts.init(document.getElementById("echarts-industry-chart"));
        barChart.showLoading();
        var baroption = {
            title : {
                text: '行业中公司数量'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['公司的数量']
            },
            grid:{
                x:40,
                x2:24,
                y2:24
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : industryName(resultData)
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true
                }
            ],
            series : [
                {
                    name:'公司的数量',
                    type:'bar',
                    data:positionNum(resultData),
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
        barChart.hideLoading();
        barChart.setOption(baroption);

        window.onresize = barChart.resize;
    });
    function  financeStageList(result) {
        var arr=[];
        for(var i=0;i<result.length;i++){
            var finance = new Object();
            finance.value = result[i].positionNum;
            finance.name = result[i].financeStage;
            arr.push(finance);
        }
        return arr;
    }
    function  financeStageName(result) {
        var arr=[];
        for(var i=0;i<result.length;i++){
            arr.push(result[i].financeStage);
        }
        return arr;
    }


    $.post("/rest/v1/queryFinanceStageCompanNum",function(resultData){
    /**
     * 饼状图
     */
    var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
    var pieoption = {
        title : {
            text: '融资情况',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:financeStageName(resultData)
        },
        calculable : true,
        series : [
            {
                name:'融资情况',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:financeStageList(resultData)
            }
        ]
    };

    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

        var funnelChart = echarts.init(document.getElementById("echarts-funnel-chart"));
        funnelChart.showLoading();
        var funneloption = {
            title : {
                text: '漏斗图',
                subtext: '',
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c}%"
            },
            legend: {
                data : financeStageName(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'融资情况',
                    type:'funnel',
                    sort : 'ascending',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:financeStageList(resultData)
                }
            ]
        };
        funnelChart.hideLoading();
        funnelChart.setOption(pieoption);
        $(window).resize(pieoption.resize);

    });

    function positionTypeName(result) {
        var arr=[];
        for(var i=0;i<result.length;i++){
            arr.push(result[i].positionName);
        }
        return arr;
    }

    function  positionTypeNum(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].num)
        }
        return array;
    }

    /**
     * 职位情况图
     */
    $.post("/rest/v1/queryPositionTypeNums",function(resultData){
        /**
         * 饼状图
         */
        var pieChart = echarts.init(document.getElementById("echarts-force-chart"));
        pieChart.showLoading();
        var pieoption = {
            title : {
                text: '融资情况',
                subtext: '',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:positionTypeName(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'融资情况',
                    type:'pie',
                    data:positionTypeNum(resultData)
                }
            ]
        };
        pieChart.hideLoading();
        pieChart.setOption(pieoption);
        $(window).resize(pieChart.resize);

    });




    var mapChart = echarts.init(document.getElementById("echarts-map-chart"));
    var mapoption = {
        title : {
            text: '全国职位概况图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            x:'left',
            data:['拉钩','Boss直聘','智联招聘']
        },
        dataRange: {
            min: 0,
            max: 5000,
            x: 'left',
            y: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            x: 'right',
            y: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        roamController: {
            show: true,
            x: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series : [
            {
                name: '拉钩',
                type: 'map',
                mapType: 'china',
                roam: false,
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[
                    {name: '北京',value: Math.round(Math.random()*1000)},
                    {name: '天津',value: Math.round(Math.random()*1000)},
                    {name: '上海',value: Math.round(Math.random()*1000)},
                    {name: '重庆',value: Math.round(Math.random()*1000)},
                    {name: '河北',value: Math.round(Math.random()*1000)},
                    {name: '河南',value: Math.round(Math.random()*1000)},
                    {name: '云南',value: Math.round(Math.random()*1000)},
                    {name: '辽宁',value: Math.round(Math.random()*1000)},
                    {name: '黑龙江',value: Math.round(Math.random()*1000)},
                    {name: '湖南',value: Math.round(Math.random()*1000)},
                    {name: '安徽',value: Math.round(Math.random()*1000)},
                    {name: '山东',value: Math.round(Math.random()*1000)},
                    {name: '新疆',value: Math.round(Math.random()*1000)},
                    {name: '江苏',value: Math.round(Math.random()*1000)},
                    {name: '浙江',value: Math.round(Math.random()*1000)},
                    {name: '江西',value: Math.round(Math.random()*1000)},
                    {name: '湖北',value: Math.round(Math.random()*1000)},
                    {name: '广西',value: Math.round(Math.random()*1000)},
                    {name: '甘肃',value: Math.round(Math.random()*1000)},
                    {name: '山西',value: Math.round(Math.random()*1000)},
                    {name: '内蒙古',value: Math.round(Math.random()*1000)},
                    {name: '陕西',value: Math.round(Math.random()*1000)},
                    {name: '吉林',value: Math.round(Math.random()*1000)},
                    {name: '福建',value: Math.round(Math.random()*1000)},
                    {name: '贵州',value: Math.round(Math.random()*1000)},
                    {name: '广东',value: Math.round(Math.random()*1000)},
                    {name: '青海',value: Math.round(Math.random()*1000)},
                    {name: '西藏',value: Math.round(Math.random()*1000)},
                    {name: '四川',value: Math.round(Math.random()*1000)},
                    {name: '宁夏',value: Math.round(Math.random()*1000)},
                    {name: '海南',value: Math.round(Math.random()*1000)}
                ]
            },
            {
                name: 'Boss直聘',
                type: 'map',
                mapType: 'china',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[
                    {name: '北京',value: Math.round(Math.random()*1000)},
                    {name: '天津',value: Math.round(Math.random()*1000)},
                    {name: '上海',value: Math.round(Math.random()*1000)},
                    {name: '重庆',value: Math.round(Math.random()*1000)},
                    {name: '河北',value: Math.round(Math.random()*1000)},
                    {name: '安徽',value: Math.round(Math.random()*1000)},
                    {name: '新疆',value: Math.round(Math.random()*1000)},
                    {name: '浙江',value: Math.round(Math.random()*1000)},
                    {name: '江西',value: Math.round(Math.random()*1000)},
                    {name: '山西',value: Math.round(Math.random()*1000)},
                    {name: '内蒙古',value: Math.round(Math.random()*1000)},
                    {name: '吉林',value: Math.round(Math.random()*1000)},
                    {name: '福建',value: Math.round(Math.random()*1000)},
                    {name: '广东',value: Math.round(Math.random()*1000)},
                    {name: '西藏',value: Math.round(Math.random()*1000)},
                    {name: '四川',value: Math.round(Math.random()*1000)},
                    {name: '宁夏',value: Math.round(Math.random()*1000)}
                ]
            },
            {
                name: '智联招聘',
                type: 'map',
                mapType: 'china',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[
                    {name: '北京',value: Math.round(Math.random()*1000)},
                    {name: '天津',value: Math.round(Math.random()*1000)},
                    {name: '上海',value: Math.round(Math.random()*1000)},
                    {name: '广东',value: Math.round(Math.random()*1000)}
                ]
            }
        ]
    };
    mapChart.setOption(mapoption);
    $(window).resize(mapChart.resize);


});
