<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>微信企业号demo</title>
    <link rel="shortcut icon" href="${ctxPath}/static/img/favicon.ico">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${ctxPath}/static/bootstrap/css/bootstrap.min.css">
    <!-- 这类图标的使用不能单纯直接下载css文件来使用，还需要其他文件 -->
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctxPath}/static/AdminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctxPath}/static/AdminLTE/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition">
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">个人信息</h3>

        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
                <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                <i class="fa fa-times"></i></button>
        </div>
    </div>
    <div class="box-body">
        <table class="table table-bordered">
            <tr>
                <td rowspan="3"><img width="100px" height="100px" id="user_head"/></td>
                <td>姓名</td>
                <td id="user_name"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td id="user_gender"></td>
            </tr>
            <tr>
                <td>手机号</td>
                <td id="user_mobile"></td>
            </tr>
        </table>
    </div>
</div>

<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">测试</h3>

        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
                <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                <i class="fa fa-times"></i></button>
        </div>
    </div>
    <div class="box-body">
        Date:<span id="date_result"></span><br/>
        test1:<span id="test_result1"></span><br/>
        test2:<span id="test_result2"></span><br/>
        test3:<span id="test_result3"></span><br/>
        test4:<span id="test_result4"></span><br/>
    </div>
    <div class="box-footer">
        <button class="btn btn-info btn-block" onclick="getDate()">更新时间</button>
    </div>
</div>

</body>
<script src="${ctxPath}/static/js/weixinqyh.js?version=1"></script>
<script>
    $(document).ready(function () {
        // 测试一次性发送多个请求
        userinfo();
        getDate();
        test(1);
        test(2);
        test(3);
        test(4);
    });

    function userinfo() {
        weixinqyhAjax({
            shortUrl: "/weixin/qyh/client/userinfo",
            success: function (user) {
                $("#user_head").attr("src", user.avatar);
                $("#user_name").html(user.name);
                $("#user_mobile").html(user.mobile);
                if (user.gender == 1) {
                    $("#user_gender").html("男");
                }
                else if (user.gender == 2) {
                    $("#user_gender").html("女");
                }
                else {
                    $("#user_gender").html("未知");
                }
            }
        });
    }
    function getDate() {
        weixinqyhAjax({
            shortUrl: "/weixin/qyh/client/date",
            success: function (data) {
                $("#date_result").html(data);
            }
        });
    }
    function test(num) {
        $("#test_result" + num).html('<span class="fa fa-spinner fa-spin"></span>');
        weixinqyhAjax({
            shortUrl: "/weixin/qyh/client/test/" + num,
            success: function (data) {
                $("#test_result" + num).html(data);
            }
        });
    }
</script>
</html>
