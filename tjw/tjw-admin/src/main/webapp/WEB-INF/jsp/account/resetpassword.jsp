<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>重置密码</title>
    <link rel="shortcut icon" href="${ctxPath}/static/img/favicon.ico">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition login-page" style="height:600px;">

<div class="row center-block" style="width:450px;padding-top:200px">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">

            </div>

            <div class="box-body">
                <form action="${ctxPath}/account/password/reset" method="post">
                    <div class="form-group">
                        <label for="account" class="col-sm-3 control-label">账号</label>
                        <div class="col-sm-9">
                            <input id="account" name="account" type="text" class="form-control" value="${account}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">Email</label>
                        <div class="col-sm-9">
                            <input id="email" name="email" type="text" class="form-control" value="${email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="code_div" class="col-sm-3 control-label">验证码</label>
                        <div id="code_div" class="col-sm-9">
                            <div class="input-group">
                                <input id="code" type="text" name="code" class="form-control" placeholder="验证码" value="">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" onclick="getCode(this)">生成验证码</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-9">
                            <input id="password" name="password" type="password" class="form-control" value=" ">
                        </div>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-info btn-block" type="submit">提交</button>
                    </div>
                </form>
            </div>

            <div class="box-footer">
                <span class="text-red">${msg}</span>
            </div>
        </div>
    </div>
</div>

<script>
    function getCode(btn) {
        var email = $("#email").val();
        var account = $("#account").val();
        if (!isHasText(email)) {
            showTips("邮箱不能为空", "error");
            return;
        }
        if (!isHasText(account)) {
            showTips("账号不能为空", "error");
            return;
        }
        ajax({
            shortUrl: "/account/password/code_for_reset",
            data: {
                email: $("#email").val(),
                account: $("#account").val()
            },
            success: function () {
                //showSuccessTips();
                $(btn).disable();
            },
            error: function (data) {
                showTips(data, "error");
            }
        });
    }
</script>
</body>
</html>
