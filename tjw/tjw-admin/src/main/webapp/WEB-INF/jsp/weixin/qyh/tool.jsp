<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>辅助工具</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">回退</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/weixin/qyh/manager/list">微信企业号管理</a></li>
        <li class="active">辅助工具</li>
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

                <div class="box-body">
                    <input id="myappid" type="hidden" value="${myappid}" />
                    <input id="cropId" type="hidden" value="${cropId}" />
                    <div class="input-group col-md-6">
                        <input id="url" type="text" class="form-control text" placeholder="原始url">
                        <span class="input-group-btn">
                              <button class="btn btn-default" type="button" onclick="createWeixinRedirect()">生成可带微信登录态的url</button>
                        </span>
                    </div>
                    <div id="result"></div>
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
    function createWeixinRedirect() {
        $("#result").html("处理中。。。");
        var url = $("#url").val();
        if (url.indexOf("?") < 0) {
            url += "?";
        }
        else {
            url += "&appid=" + $("#cropId").val();
        }
        url = url + "myappid=" + $("#myappid").val() + "&"
        var redirectUrl = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + $("#cropId").val() + '&redirect_uri='
                + encodeURIComponent(url)
                + '&response_type=code&scope=snsapi_base&state=state#wechat_redirect';
        $("#result").html(redirectUrl);
    }

</script>
