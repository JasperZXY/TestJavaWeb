<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<section class="content-header">
    <h1>
        ${window_title}
        <small>角色管理</small>
        <button class="btn btn-sm btn-info" onclick="selfOpen('/permission/role/to_add')">新增</button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">角色管理</a></li>
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
                            <th>ID</th>
                            <th>名称</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="role" items="${list}" varStatus="status">
                            <tr id="tr_role_${role.id }">
                                <td>${role.id }</td>
                                <td>${role.name }</td>
                                <td>
                                    <c:if test="${role.status == 0}">
                                        <span class="text-green">有效</span>
                                    </c:if>
                                    <c:if test="${role.status == 1}">
                                        <span class="text-red">已删</span>
                                    </c:if>
                                    <c:if test="${role.status == 2}">
                                        <span class="text-warning">禁用</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${role.status == 0}">
                                        <permisssion:pass code="2006">
                                            <a class="btn btn-sm btn-info" href="${ctxPath}/permission/role/to_allocate/resource/${role.id }">分配资源</a>
                                        </permisssion:pass>
                                        <permisssion:pass code="2003">
                                            <a class="btn btn-sm btn-warning" href="${ctxPath}/permission/role/to_update/${role.id }">编辑</a>
                                        </permisssion:pass>
                                        <permisssion:pass code="2005">
                                            <a class="btn btn-sm btn-danger" onclick="optionRole(${role.id }, 'lock')">禁用</a>
                                        </permisssion:pass>
                                    </c:if>
                                    <c:if test="${role.status != 0}">
                                        <permisssion:pass code="2005">
                                            <a class="btn btn-sm btn-danger" onclick="optionRole(${role.id }, 'unlock')">解禁</a>
                                        </permisssion:pass>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </tfoot>
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
    function optionRole(id, option) {
        ajax({
            shortUrl: '/api/permission/role/' + option + '/' + id,
            success: function () {
                location.reload();  // 成功后直接刷新界面，简单处理
            },
            error: function (msg) {
                showTips('操作失败：' + msg, 'error');
            }
        });
    }
</script>