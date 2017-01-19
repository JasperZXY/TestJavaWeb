<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="permisssion" uri="http://permission.demo.zxy/taglib" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="window_title" value="<%=zxy.constants.JspConfig.SYSTEM_NAME %>"/>
<c:set var="index_url" value="${ctxPath}/"/>
<c:if test="${LOGIN_USER != null}">
    <c:set var="loginUserName" value="${LOGIN_USER.name}"/>
</c:if>
