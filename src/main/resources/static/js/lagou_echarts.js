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
    var cityNumBarChart = echarts.init(document.getElementById("echarts-bar-chart"));
    cityNumBarChart.showLoading({
        text: "正在加载中...请稍后"
    });
    /**
     *  1.各城市公司数量信息
     */
    $.post("/rest/v1/queryCityCompanNum",function(resultData){
        /**
         * 柱状图
         */
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
        cityNumBarChart.hideLoading();
        cityNumBarChart.setOption(baroption);
        window.onresize = cityNumBarChart.resize;
    });
    function industryName(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].industryField)
        }
        return array;
    }
    /**
     * 分析各个行业领域公司数量
     */
    var industryCompanNumBarChart = echarts.init(document.getElementById("echarts-industry-chart"));
    industryCompanNumBarChart.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryIndustryCompanNum",function(resultData){
        /**
         * 柱状图
         */
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
        industryCompanNumBarChart.hideLoading();
        industryCompanNumBarChart.setOption(baroption);
        $(window).resize(industryCompanNumBarChart.resize);
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

    /**
     * 职位情况图
     */
    var PositionTypeNumsPieChart = echarts.init(document.getElementById("echarts-funnel-chart"));
    PositionTypeNumsPieChart.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryPositionTypeNums",function(resultData){
        /**
         * 饼状图
         */
        var PositionType = {
            title : {
                text: '职位情况图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:positionTypeName(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'职位情况图',
                    type:'pie',
                    radius : '55%',
                    data:positionTypeNum(resultData)
                }
            ]
        };
        PositionTypeNumsPieChart.hideLoading();
        PositionTypeNumsPieChart.setOption(PositionType);
        $(window).resize(PositionTypeNumsPieChart.resize);
    });

    /**
     * 融资情况
     */
    var FinanceStageCompanNumChart = echarts.init(document.getElementById("echarts-pie-chart"));
    FinanceStageCompanNumChart.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryFinanceStageCompanNum",function(resultData){

    /**
     * 饼状图
     */
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
        FinanceStageCompanNumChart.hideLoading();
        FinanceStageCompanNumChart.setOption(pieoption);
        window.addEventListener("resize",function(){
            FinanceStageCompanNumChart.resize();
        });
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
            var obj = new Object();
            obj.value = result[i].num;
            obj.name = result[i].positionName
            array.push(obj)
        }
        return array;
    }


    function  workYearName(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            if(result[i].workMinYear == 0){
                arr.push("经验无要求")
            }else if(result[i].workMinYear == 1){
                arr.push("1-3年")
            }
            else if(result[i].workMinYear == 3){
                arr.push("3-5年")
            }
            else if(result[i].workMinYear == 5){
                arr.push("5-10年")
            }else if(result[i].workMinYear == 10){
                arr.push("十年以上")
            }

        }

        return arr;
    }

    function  workYearValueName(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            if(result[i].workMinYear == 0){
                var obj = new Object();
                obj.value = 0;
                obj.name = "经验无要求";
                arr.push(obj)
            }else if(result[i].workMinYear == 1){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "1-3年";
                arr.push(obj)
            }
            else if(result[i].workMinYear == 3){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "3-5年";
                arr.push(obj)
            }
            else if(result[i].workMinYear == 5){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "5-10年";
                arr.push(obj)
            }else if(result[i].workMinYear == 10){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "十年以上";
                arr.push(obj)
            }

        }
        return arr;
    }

    /**
     * 职位情况图
     */
    var workYear = echarts.init(document.getElementById("workYear"));
    workYear.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryWorkYearNums",function(resultData){
        /**
         * 饼状图
         */
        var workYearOpeation = {
            title : {
                text: '工作年限',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:workYearName(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'工作年限',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:workYearValueName(resultData)
                }
            ]
        };
        workYear.hideLoading();
        workYear.setOption(workYearOpeation);
        $(window).resize(workYear.resize);
    });

    function  educationNames(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            arr.push(result[i].education)
        }
        return arr;
    }
    function  educationValuesNames(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            var obj = new Object();
            obj.value = result[i].num;
            obj.name = result[i].education;
            arr.push(obj)
        }
        return arr;
    }
    /**
     *
     */
    var educationPieChart = echarts.init(document.getElementById("education"));
    educationPieChart.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryEducationNums",function(resultData){
        /**
         * 饼状图
         */
        var educationOperation = {
            title : {
                text: '职位情况图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:educationNames(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'职位情况图',
                    type:'pie',
                    radius : '55%',
                    data:educationValuesNames(resultData)
                }
            ]
        };
        educationPieChart.hideLoading();
        educationPieChart.setOption(educationOperation);
        $(window).resize(educationPieChart.resize);
    });
    function  jobNatureNames(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            arr.push(result[i].jobNature)
        }
        return arr;
    }
    function  jobNatureValuesNames(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            var obj = new Object();
            obj.value = result[i].num;
            obj.name = result[i].jobNature;
            arr.push(obj)
        }
        return arr;
    }
    /**
     *
     */
    var jobNaturePieChart = echarts.init(document.getElementById("jobNature"));
    jobNaturePieChart.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryJobNatureNums",function(resultData){
        /**
         * 饼状图
         */
        var jobNatureOperation = {
            title : {
                text: '工作类型情况图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:jobNatureNames(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'工作类型',
                    type:'pie',
                    radius : '55%',
                    data:jobNatureValuesNames(resultData)
                }
            ]
        };
        jobNaturePieChart.hideLoading();
        jobNaturePieChart.setOption(jobNatureOperation);
        $(window).resize(jobNaturePieChart.resize);
    });
    function companySizeNames(result){
        var arr = [];
        for(var i=0;i<result.length;i++){
            if(result[i].companyMinSize == 0){
                arr.push("低于10人")
            }else if(result[i].companyMinSize == 10){
                arr.push("10-50人")
            }
            else if(result[i].companyMinSize == 50){
                arr.push("50-150人")
            }
            else if(result[i].companyMinSize == 150){
                arr.push("150-500人")
            }else if(result[i].companyMinSize == 500){
                arr.push("500-2000人")
            }else{
                arr.push("2000人以上")
            }

        }

        return arr;
    }

    function companySizeValueNames(result) {
        var arr = [];
        for(var i=0;i<result.length;i++){
            if(result[i].companyMinSize == 0){
                var obj = new Object();
                obj.value = 0;
                obj.name = "低于10人";
                arr.push(obj)
            }else if(result[i].companyMinSize == 10){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "10-50人";
                arr.push(obj)
            }
            else if(result[i].companyMinSize == 50){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "50-150人";
                arr.push(obj)
            }
            else if(result[i].companyMinSize == 150){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "150-500人";
                arr.push(obj)
            }else if(result[i].companyMinSize == 500){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "500-2000人";
                arr.push(obj)
            }else if(result[i].companyMinSize == 2000){
                var obj = new Object();
                obj.value = result[i].num;
                obj.name = "2000人以上";
                arr.push(obj)
            }

        }
        return arr;
    }
    /**
     *不同公司规模的职位数量图
     */
    var companySize = echarts.init(document.getElementById("companySize"));
    companySize.showLoading({
        text: "正在加载中...请稍后"
    });
    $.post("/rest/v1/queryCompanySize",function(resultData){
        /**
         * 饼状图
         */
        var companySizeOperation = {
            title : {
                text: '不同公司规模的职位数量图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:companySizeNames(resultData)
            },
            calculable : true,
            series : [
                {
                    name:'公司规模的职位数量',
                    type:'pie',
                    radius : '55%',
                    data:companySizeValueNames(resultData)
                }
            ]
        };
        companySize.hideLoading();
        companySize.setOption(companySizeOperation);
        $(window).resize(companySize.resize);
    });


    function  thirdType(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].thirdType)
        }
        return array;
    }

    function  thirdTypeNum(result) {
        var array = [];
        for(var i=0;i<result.length;i++){
            array.push(result[i].num)
        }
        return array;
    }

    var positionDetailNums = echarts.init(document.getElementById("positionDetailsByJS"));
    positionDetailNums.showLoading({
        text: "正在加载中...请稍后"
    });
    /**
     *  1.各城市公司数量信息
     */
    $.post("/rest/v1/queryPositionDetailsByJS",function(resultData){
        /**
         * 柱状图
         */
        var baroption = {
            title : {
                text: '技术类型下职位数量信息'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['职位的数量']
            },
            grid:{
                x2:24,
                y2:24
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : thirdType(resultData)
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
                    name:'职位的数量',
                    type:'bar',
                    data:thirdTypeNum(resultData),
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
        positionDetailNums.hideLoading();
        positionDetailNums.setOption(baroption);
        window.onresize = positionDetailNums.resize;
    });
    $.get("/json/china_city.json",function(resultData){

    })
    $.get("/json/china_province.json",function(resultData){

    })
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
