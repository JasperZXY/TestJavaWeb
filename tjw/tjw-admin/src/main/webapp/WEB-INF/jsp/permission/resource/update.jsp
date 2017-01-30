<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>新增资源</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">回退</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/permission/resource/list/all">资源管理</a></li>
        <li class="active">更新资源</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">更新资源</h3>
                </div>

                <div class="box-body">
                    <form id="formForUpdate" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="id" class="col-sm-2 control-label">ID</label>
                            <div class="col-sm-6">
                                <input readonly id="id" name="id" type="text" class="form-control" value="${resource.id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="parentId" class="col-sm-2 control-label">父ID</label>
                            <div class="col-sm-6">
                                <input id="parentId" name="parentId" type="text" class="form-control" value="${resource.parentId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-6">
                                <input id="name" name="name" type="text" class="form-control" value="${resource.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-6">
                                <select id="type" name="type" class="form-control" init-value="${resource.type}">
                                    <option value="nav">nav</option>
                                    <option value="menu">menu</option>
                                    <option value="button">button</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2"></label>
                            <div class="col-sm-6">
                                <button class="btn btn-info btn-block" onclick="updateResource()" type="button">提交</button>
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
    function updateResource() {
        $('#resultTip').html('处理中。。。');

        ajaxForm({
            formId: "formForUpdate",
            shortUrl: '/api/permission/resource/update',
            error: function (msg) {
                $('#resultTip').html('失败：' + msg);
            },
            success: function (data) {
                $('#resultTip').html('成功');
            }
        });
    }
</script>
