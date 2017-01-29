<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<section class="content-header">
    <h1>
        ${window_title}
        <small>指定角色</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/user/list">用户管理</a></li>
        <li><a href="#">分配资源</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">${user.name}【${user.accountId}】</h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <input id="userRoleIds" type="hidden" value="${userRoleIds}" />
                    <input id="userId" value="${user.id}" type="hidden" />
                    <div id="allResources">
                        <c:forEach var="role" items="${allRoles}">
                            <div>
                                <label class="checkbox-inline">
                                    <input class="role_checkbox" type="checkbox" value="${role.id}" onclick="roleCheckboxOnCheck()"> ${role.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- /.box-body -->

                <div class="box-footer">
                    <input id="roleIds" type="hidden" value="${userRoleIds}">
                    <button class="btn btn-info" type="button" onclick="assignRoleForUser()">提交</button>
                </div>
                <!-- /.box-footer -->

            </div>
            <!-- /.box -->
        </div>

        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<script>
jQuery(function(){
    var userRoleIds = $('#userRoleIds').val().split(",");
    var checkboxs = $('.role_checkbox');
    for (var i=0; i<checkboxs.length; i++) {
        var item = $(checkboxs[i]);
        if (findInArray(userRoleIds, item.val())) {
            item.attr('checked', 'checked');
        }
    }
});

function roleCheckboxOnCheck() {
    var checkboxs = $('.role_checkbox');
    var result = '';
    for (var i=0; i<checkboxs.length; i++) {
        var item = $(checkboxs[i]);
        if (item.is(':checked')) {
            result += item.val() + ',';
        }
    }
    if (isHasText(result)) {
        result = result.substr(0, result.length - 1);
    }
    $('#roleIds').val(result);
}

function assignRoleForUser() {
    ajax({
        shortUrl:"/api/permission/role/assign/foruser/" + $('#userId').val(),
        data : {roleIds:$("#roleIds").val()},
        success : function() {
            showTips("成功", 'success');
        },
        error : function() {
            showTips("失败", 'error');
        }
    });
}
</script>