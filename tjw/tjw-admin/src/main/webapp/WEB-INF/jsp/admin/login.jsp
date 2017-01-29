<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <link rel="shortcut icon" href="${ctxPath}/static/img/favicon.ico" >

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
<body class="hold-transition login-page" style="height:500px;">
<div class="login-box">
    <div class="login-box-body">
        <p class="login-box-msg text-red">
            ${msg}${param.msg}
        </p>

        <form action="${ctxPath}/auth/login" method="post">
            <div class="form-group has-feedback">
                <input name="account" type="text" class="form-control" placeholder="账号">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="#">忘记密码</a><br>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="${ctxPath}/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctxPath}/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
