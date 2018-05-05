// ajax分页
function sendAjax(flag, dataParam, url, callback) {//封装的ajax；
    var shus = document.getElementById("ssq-" + flag).value;
    if (shus != "") {
        var pageNumber = document.getElementById("ssq-" + flag).value;
    } else {
        var pageNumber = 1;
    }
    $.ajax({
        type: "POST",
        url: "/rest/query/v1/queryPostionDetail" + "?pageNum=" + pageNumber,
        data: dataParam,
        datatype: "json",
        success: function (data) {

            //当前页
            var pagenum = data.current;
            document.getElementById("ssq-" + flag).value = pagenum;
            document.getElementById("ff-" + flag).innerHTML = pagenum;
            //总页数
            var pages = data.pages;
            document.getElementById("zys-" + flag).value = pages;
            document.getElementById("fffs-" + flag).innerHTML = pages;

            callback(data);
        },
        error: function () {
            //请求出错处理
            alert("服务器忙碌，稍后再试")
        }
    });
}

//分页上一个
function syy(flag) {
    var shu = document.getElementById("ssq-" + flag).value;
//                判断是不是第一页
    if (shu == 1) {
        $.alert({
            title: '提示',
            content: '已经是首页了！',
            buttons: {"好的": {btnClass: 'btn-blue'}}
        });
        return false;
    }
    document.getElementById("ssq-" + flag).value = parseInt(shu) - 1;
    ajaxpage(flag)
}

//分页下一页
function xyy(flag) {
    var shu = document.getElementById("ssq-" + flag + "").value;
    var zye = document.getElementById("zys-" + flag).value;

    if (shu == zye) {
        $.alert({
            title: '提示',
            content: '已经是最后一页了！',
            buttons: {"好的": {btnClass: 'btn-blue'}}
        });
        return false;
    }
    document.getElementById("ssq-" + flag).value = parseInt(shu) + 1;
    ajaxpage(flag)
}

//分页 首页
function sy(flag) {
    document.getElementById("ssq" + flag).value = 1;
    ajaxpage(flag)
}

//尾页
function wy(flag) {
    var zye = document.getElementById("zys-" + flag).value;
    document.getElementById("ssq-" + flag).value = zye;
    ajaxpage(flag)
}

// 这个是查询 的方法，如果用这个方法的话，就得吧对应的弹框 div 设置成对应的 eg:ID= detailModel-factory
function selects(flag) {
    $("#detailModel-" + flag).modal('show');
    $('#ssq-' + flag).val('');
    ajaxpage(flag);
}