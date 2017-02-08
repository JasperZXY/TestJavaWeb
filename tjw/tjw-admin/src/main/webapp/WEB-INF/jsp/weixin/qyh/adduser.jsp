<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>添加用户</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">回退</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/weixin/qyh/manager/list">微信企业号管理</a></li>
        <li class="active">添加用户</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增用户，然后关注二维码即可使用。建议在微信后台上添加，这里只是简单的操作。</h3>
                </div>

                <div class="box-body">
                    <form id="formForAdd" class="form-horizontal" role="form">
                        <input type="hidden" name="myappid" value="${myappid}" />
                        <div class="form-group">
                            <label for="userid" class="col-sm-2 control-label">userid</label>
                            <div class="col-sm-6">
                                <input id="userid" name="userid" type="text" class="form-control" placeholder="自定义一个唯一标识">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control" placeholder="姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-6">
                                <input id="mobile" name="mobile" type="text" class="form-control" placeholder="手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2"></label>
                            <div class="col-sm-6">
                                <button class="btn btn-info col-sm-6" onclick="addUser()" type="button">提交</button>
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
    function addUser() {
        $('#resultTip').html('处理中。。。');

        ajaxForm({
            formId: "formForAdd",
            shortUrl: '/weixin/qyh/manager/adduser',
            error: function (msg) {
                $('#resultTip').html('失败：' + msg);
            },
            success: function (data) {
                $('#resultTip').html('成功');
            }
        });
    }
</script>
