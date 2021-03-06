<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>用户管理</small>
        <permisssion:pass code="3002">
            <button class="btn btn-sm btn-info" onclick="selfOpen('/user/to_add')">新增</button>
        </permisssion:pass>
        </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">用户管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools col-sm-1 col-md-11">
                        <div class="col-md-2">
                            <input id="search_email" class="form-control" type="text" name="table_search"
                                   placeholder="Email" value="${email}">
                        </div>
                        <div class="col-md-2">
                            <input id="search_name" class="form-control" type="text" name="table_search"
                                   placeholder="昵称" value="${name}">
                        </div>
                        <div class="col-md-2">
                            <select id="search_status" class="form-control" init-value="${status}">
                                <option value="">所有</option>
                                <option value="0">有效</option>
                                <option value="1">已删</option>
                                <option value="2">冻结</option>
                            </select>
                        </div>
                        <div class="col-md-1">
                            <button type="button" class="btn btn-default form-control" onclick="topage(1)">搜索</button>
                        </div>
                    </div>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>登录账户</th>
                            <th>昵称</th>
                            <th>生日</th>
                            <th>状态</th>
                            <th>操作</th>
                            <permisssion:pass code="3006">
                                <th>协助修改密码</th>
                            </permisssion:pass>
                            <permisssion:pass code="3007">
                                <th>协助修改邮箱</th>
                            </permisssion:pass>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${page.result }" varStatus="status">
                            <tr id="tr_user_${user.id }">
                                <td>${user.id }</td>
                                <td>${user.accountId }</td>
                                <td>${user.name }</td>
                                <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${user.status == 0}">
                                        <span class="text-green">有效</span>
                                    </c:if>
                                    <c:if test="${user.status == 1}">
                                        <span class="text-red">已删</span>
                                    </c:if>
                                    <c:if test="${user.status == 2}">
                                        <span class="text-red">冻结</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${user.status != 1}">
                                        <permisssion:pass code="2007">
                                            <a class="btn btn-sm btn-info" href="${ctxPath}/permission/role/to_assign/foruser/${user.id }">指定角色</a>
                                        </permisssion:pass>
                                        <permisssion:pass code="3003">
                                            <a class="btn btn-sm btn-info" href="${ctxPath}/user/to_update/${user.id }">编辑</a>
                                        </permisssion:pass>
                                        <permisssion:pass code="3004">
                                            <a class="btn btn-sm btn-danger" onclick="optionUser(${user.id }, 'delete')">删除</a>
                                        </permisssion:pass>
                                        <c:if test="${user.status == 0}">
                                            <permisssion:pass code="3005">
                                                <a class="btn btn-sm btn-warning" onclick="optionUser(${user.id }, 'lock')">冻结</a>
                                            </permisssion:pass>
                                        </c:if>
                                        <c:if test="${user.status == 2}">
                                            <permisssion:pass code="3005">
                                                <a class="btn btn-sm btn-danger" onclick="optionUser(${user.id }, 'unlock')">解冻</a>
                                            </permisssion:pass>
                                        </c:if>
                                    </c:if>
                                </td>
                                <permisssion:pass code="3006">
                                    <td>
                                        <c:if test="${user.status != 1}">
                                            <div id="change_password_${user.id}" class="input-group">
                                                <input type="hidden" class="accountId" value="${user.accountId}" />
                                                <input type="password" class="form-control password" placeholder="密码" value="">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-danger" type="button" onclick="changePassword(${user.id})">提交</button>
                                                </span>
                                            </div>
                                        </c:if>
                                    </td>
                                </permisssion:pass>
                                <permisssion:pass code="3007">
                                    <td>
                                        <div id="change_email_${user.id}" class="input-group">
                                            <input type="hidden" class="accountId" value="${user.accountId}" />
                                            <input type="email" class="form-control email" placeholder="Email" value="${user.email}">
                                            <span class="input-group-btn">
                                                <button class="btn btn-danger" type="button" onclick="changeEmail(${user.id})">提交</button>
                                            </span>
                                        </div>
                                    </td>
                                </permisssion:pass>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>编号</th>
                            <th>登录账户</th>
                            <th>昵称</th>
                            <th>生日</th>
                            <th>状态</th>
                            <th>操作</th>
                            <permisssion:pass code="3006">
                                <th>协助修改密码</th>
                            </permisssion:pass>
                            <permisssion:pass code="3007">
                                <th>协助修改邮箱</th>
                            </permisssion:pass>
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
        window.open(toPageUrl('/user/list', page)
                + '&name=' + encodeURIComponent($('#search_name').val())
                + '&email=' + encodeURIComponent($('#search_email').val())
                + '&status=' + $('#search_status option:selected').val()
                , '_self');
    }
    function optionUser(id, option) {
        ajax({
            shortUrl: '/api/user/' + option + '/' + id,
            success: function () {
                location.reload();
            },
            error: function (msg) {
                showTips('操作失败：' + msg, 'error');
            }
        });
    }
    function changePassword(uid) {
        ajax({
            shortUrl: '/api/user/help/change/password',
            data : {
                accountId : $('#change_password_' + uid).children('.accountId').val(),
                password : $('#change_password_' + uid).children('.password').val(),
            },
            success: function () {
                $('#change_password_' + uid).children('.password').val('');
                showTips('成功', 'success');
            },
            error: function (msg) {
                showTips('操作失败：' + msg, 'error');
            }
        });
    }

    function changeEmail(uid) {
        ajax({
            shortUrl: '/api/user/help/change/email',
            data : {
                accountId : $('#change_email_' + uid).children('.accountId').val(),
                email : $('#change_email_' + uid).children('.email').val(),
            },
            success: function () {
                showTips('成功', 'success');
            },
            error: function (msg) {
                showTips('失败：' + msg, 'error');
            }
        });
    }
</script>