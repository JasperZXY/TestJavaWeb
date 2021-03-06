<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>新增角色</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">返回角色管理</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/permission/role/list/all">角色管理</a></li>
        <li class="active">添加角色</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增角色</h3>
                </div>

                <div class="box-body">
                    <form id="formForAdd" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2"></label>
                            <div class="col-sm-6">
                                <button class="btn btn-info col-sm-6" onclick="addRole()" type="button">提交</button>
                                <button class="btn btn-info col-sm-6" type="reset">重置</button>
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
    function addRole() {
        $('#resultTip').html('处理中。。。');

        ajaxForm({
            formId: "formForAdd",
            shortUrl: '/api/permission/role/add',
            error: function (msg) {
                $('#resultTip').html('失败：' + msg);
            },
            success: function (data) {
                $('#resultTip').html('成功');
            }
        });
    }
</script>
