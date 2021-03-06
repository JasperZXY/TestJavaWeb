<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<section class="content-header">
    <h1>
        ${window_title}
        <small>资源管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">资源管理</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">说明：目前权限对应的资源层级关系只实现了nav-->menu-->button，只要有对应的menu权限，nav即可展示。</h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>父ID</th>
                            <th>名称</th>
                            <th>类型</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="resource" items="${list}" varStatus="status">
                            <tr id="tr_resource_${resource.id }">
                                <td>${resource.id }</td>
                                <td>${resource.parentId }</td>
                                <td>${resource.name }</td>
                                <td>${resource.type }</td>
                                <td>
                                    <c:if test="${resource.status == 0}">
                                        <span class="text-green">有效</span>
                                    </c:if>
                                    <c:if test="${resource.status == 1}">
                                        <span class="text-red">已删</span>
                                    </c:if>
                                    <c:if test="${resource.status == 2}">
                                        <span class="text-warning">禁用</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>父ID</th>
                            <th>名称</th>
                            <th>类型</th>
                            <th>状态</th>
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
</script>