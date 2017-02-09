<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>应用管理</small>
        <button class="btn btn-default" onclick="history.back();">
            <span class="fa fa-mail-reply">回退</span>
        </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="${ctxPath}/weixin/qyh/manager/list">微信企业号管理</a></li>
        <li class="active">应用管理【${myappid}】</li>
    </ol>
    <input type="hidden" value="${myappid}" id="myappid" />
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">简单的发送一下消息，并不完善</h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <table id="user_table" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>agentID</th>
                            <th>名称</th>
                            <permisssion:pass code="5005">
                                <th>发送消息</th>
                            </permisssion:pass>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list}" varStatus="status">
                            <tr id="tr_item_${item.agentId }">
                                <td>${item.agentId }</td>
                                <td>${item.name }</td>
                                <permisssion:pass code="5005">
                                    <td>
                                        <c:if test="${item.canSendMsg}">
                                            <div class="input-group">
                                                <input id="msg_${item.agentId}" type="text" class="form-control text" placeholder="内容">
                                                <span class="input-group-btn">
                                                      <button class="btn btn-default" type="button" onclick="sendMsg('${item.agentId}', 'text')">纯文本</button>
                                                </span>
                                                <span class="input-group-btn">
                                                      <button class="btn btn-default" type="button" onclick="sendMsg('${item.agentId}', 'news')">图文</button>
                                                </span>
                                            </div>
                                        </c:if>
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
    function sendMsg(agentId, msgType) {
        ajax({
            shortUrl : "/weixin/qyh/manager/send/" + msgType,
            data : {
                myappid : $("#myappid").val(),
                agentId : agentId,
                msg : $("#msg_" + agentId).val()
            }
        })
    }
</script>