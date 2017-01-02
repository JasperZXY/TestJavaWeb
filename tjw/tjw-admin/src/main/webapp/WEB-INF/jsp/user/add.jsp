<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>新增用户</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">返回用户管理</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="/user/list">用户管理</a></li>
        <li class="active">添加用户</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增用户</h3>
                </div>

                <div class="box-body">
                    <form id="formForAdd" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="account" class="col-sm-2 control-label">账号</label>
                            <div class="col-sm-6">
                                <input id="account" name="account" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-6">
                                <input id="password" name="password" type="password" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passwordConfirm" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-6">
                                <input id="passwordConfirm" name="passwordConfirm" type="password" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birthday" class="col-sm-2 control-label">生日</label>
                            <div class="col-sm-6">
                                <input id="birthday" name="birthday" type="text" class="form-control"
                                       data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2"></label>
                            <div class="col-sm-6">
                                <button class="btn btn-info btn-block" onclick="addUser()" type="button">提交</button>
                                <button style="display:none" type="submit"></button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">结果</label>
                            <span class="help-block" id="resultTip"></span>
                        </div>
                    </form>
                </div>
                <!--- /.box-body -->
            </div>
            <!--- /.box -->
        </div>
        <!--- /.col-xs-12 -->
    </div>
    <!--- /.row -->
</section>

<script>
    function addUser() {
        $('#resultTip').html('处理中。。。');
        if ($('#password').val() != $('#passwordConfirm').val()) {
            $('#resultTip').html('两次密码不一致');
            return;
        }

        new MyAjaxForm({
            formId: "formForAdd",
            shortUrl: '/api/user/add',
            error: function (msg) {
                $('#resultTip').html('失败：' + msg);
            },
            success: function (data) {
                $('#resultTip').html('成功');
            }
        }).run();
    }
</script>
