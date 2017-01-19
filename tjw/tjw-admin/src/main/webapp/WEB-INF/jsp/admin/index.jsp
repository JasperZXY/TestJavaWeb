<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>Version 1.0</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>

<section class="content">
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">首页</h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                    <i class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                    <i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="box-body">
            欢迎你:${loginUserName}
            <br/>
            使用说明：里面所有的添加界面，上面都有一个返回按钮，返回后，界面是不刷新的，右上角的导航菜单点击才会刷新界面。
            服务器时间:<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
            <br/>
            index_url : "${index_url}"
            <br/>
            ctxPath : "${ctxPath}"
            <br/>
            window_title : "${window_title}"

        </div>
        <div class="box-footer">
        </div>
    </div>

</section>
