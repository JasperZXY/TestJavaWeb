<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>微信企业号管理</small>
        </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">微信企业号管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"></h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>自定义ID</th>
                            <th>CropID</th>
                            <th>二维码</th>
                            <permisssion:pass code="5005">
                                <th>操作</th>
                            </permisssion:pass>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list}" varStatus="status">
                            <tr id="tr_item_${item.myappid }">
                                <td>${item.myappid }</td>
                                <td>${item.corpId }</td>
                                <td>
                                    <img height="100px" src="${item.qrCodeUrl}" />
                                </td>
                                <permisssion:pass code="5005">
                                    <td>
                                        <a class="btn btn-sm btn-info" href="${ctxPath}/weixin/qyh/manager/to_adduser?myappid=${item.myappid }">添加用户</a>
                                        <a class="btn btn-sm btn-info" href="${ctxPath}/weixin/qyh/manager/agent/list/${item.myappid }">应用列表</a>
                                    </td>
                                </permisssion:pass>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<script>
</script>