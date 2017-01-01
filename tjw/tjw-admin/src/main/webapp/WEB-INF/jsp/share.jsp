<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="jstl.jsp"%>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:if test="${window_title == null}">
    <c:set var="window_title" value="<%=zxy.constants.JspConfig.SYSTEM_NAME %>"></c:set>
</c:if>

<script>
    var ctxPath = '${ctx}';
</script>
