<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>Version 1.0</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> Home</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">
                        服务器时间:<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <br/>
                        index_url : "${index_url}"
                        <br/>
                        ctxPath : "${ctxPath}"
                    </h3>
                </div>
            </div>
        </div>
    </div>
</section>
