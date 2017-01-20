<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<section class="content-header">
    <h1>
        ${window_title}
        <small>分配资源</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/permission/role/list/all">角色管理</a></li>
        <li><a href="#">分配资源</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">${role.name}【${role.id}】</h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <input id="resourceIdsForRole" type="hidden" value="${resourceIdsForRole}" />
                    <input id="roleId" value="${role.id}" type="hidden" />
                    <div id="allResources">
                        <c:forEach var="resources" items="${allResources}">
                            <div>
                                <c:forEach var="resource" items="${resources}" >
                                    <label class="checkbox-inline">
                                        <input class="resource_checkbox" type="checkbox" value="${resource.id}" onclick="resourceCheckboxOnCheck()"> ${resource.name}
                                    </label>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- /.box-body -->

                <div class="box-footer">
                    <div class="input-group" style="width:600px">
                        <input id="resourceIds" type="text" class="form-control" placeholder="可手动输入" value="${resourceIdsForRole}">
                        <span class="input-group-btn">
                            <button class="btn btn-info" type="button" onclick="allocateResource()">分配</button>
                        </span>
                    </div>
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
    var resourceIdsForRole = $('#resourceIdsForRole').val().split(",");
    var checkboxs = $('.resource_checkbox');
    for (var i=0; i<checkboxs.length; i++) {
        var item = $(checkboxs[i]);
        if (findInArray(resourceIdsForRole, item.val())) {
            item.attr('checked', 'checked');
        }
    }
});

function resourceCheckboxOnCheck() {
    var checkboxs = $('.resource_checkbox');
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
    $('#resourceIds').val(result);
}

function allocateResource() {
    ajax({
        shortUrl:"/api/permission/role/allocate/resource/" + $('#roleId').val(),
        data : {resourceIds:$("#resourceIds").val()},
        success : function() {
            alert('成功');
        },
        error : function() {
            alert('失败');
        }
    });
}
</script>