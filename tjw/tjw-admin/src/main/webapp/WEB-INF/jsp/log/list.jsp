<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>操作日志</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">操作日志</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>操作人UID</th>
                            <th>操作人</th>
                            <th>操作编码</th>
                            <th>操作</th>
                            <th>IP</th>
                            <th>时间</th>
                            <th>目标</th>
                            <th>额外数据</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="log" items="${page.result }" varStatus="status">
                            <tr id="tr_log_${log.id }">
                                <td>${log.id }</td>
                                <td>${log.uid }</td>
                                <td>${log.userName }</td>
                                <td>${log.code }</td>
                                <td>${log.operation }</td>
                                <td>${log.ip }</td>
                                <td><fmt:formatDate value="${log.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>${log.target }</td>
                                <td>${log.extra }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>操作人UID</th>
                            <th>操作人</th>
                            <th>操作编码</th>
                            <th>操作</th>
                            <th>IP</th>
                            <th>时间</th>
                            <th>目标</th>
                            <th>额外数据</th>
                        </tr>
                        </tfoot>
                    </table>
                    <%@include file="../paging.jsp" %>
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
    function topage(page) {
        window.open(toPageUrl('/log/list', page) , '_self');
    }
</script>