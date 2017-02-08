<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="permisssion" uri="http://permission.demo.zxy/taglib" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="window_title" value="<%=zxy.commons.JspConfig.SYSTEM_NAME %>"/>
<c:set var="index_url" value="${ctxPath}/"/>
<c:if test="${LOGIN_USER != null}">
    <c:set var="loginUserName" value="${LOGIN_USER.name}"/>
</c:if>

<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${ctxPath}/static/bootstrap/css/bootstrap.min.css">
<!-- 这类图标的使用不能单纯直接下载css文件来使用，还需要其他文件 -->
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxPath}/static/AdminLTE/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctxPath}/static/AdminLTE/css/skins/_all-skins.min.css">
<!-- jQuery toast -->
<link rel="stylesheet" href="${ctxPath}/static/plugins/jQueryToast/css/jquery.toast.css">

<!-- JS -->
<!-- jQuery 2.2.3 -->
<script src="${ctxPath}/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap -->
<script src="${ctxPath}/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctxPath}/static/AdminLTE/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxPath}/static/AdminLTE/js/demo.js"></script>
<!-- InputMask -->
<script src="${ctxPath}/static/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${ctxPath}/static/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${ctxPath}/static/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- jQuery toast -->
<script src="${ctxPath}/static/plugins/jQueryToast/js/jquery.toast.js"></script>
<!-- 自定义JS -->
<script src="${ctxPath}/static/js/common.js?version=6"></script>
<script>
    var ctxPath = '${ctx}';
</script>
