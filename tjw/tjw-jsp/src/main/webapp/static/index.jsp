<%@ page import="com.jasper.domain.User" %>
<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>JSP</title>
</head>
<style>
    h1{padding-top: 30px;}
    h3{padding-top: 20px;}
</style>
<body>

<h1>路径相关</h1>
<div>
    虚拟路径：<%= request.getServletPath()%><br/>
    物理路径：<%= request.getRealPath("/static/index.jsp")%><br/>
    根目录所对应的绝对路径：<%= request.getRequestURI()%><br/>
    文件的绝对路径：<%= application.getRealPath(request.getRequestURI())%><br/>
    根目录所对应的绝对路径：<%= request.getServletPath()%><br/>
    文件的绝对路径：<%= request.getSession().getServletContext().getRealPath(request.getRequestURI()) %><br/>
</div>

<h1>JSP</h1>
<jsp:useBean id="user" scope="page" class="com.jasper.domain.User">
</jsp:useBean>
<jsp:setProperty name="user" property="id" value="1"></jsp:setProperty>
<jsp:setProperty name="user" property="name" value="Jasper"></jsp:setProperty>
<div>
    id:${user.id}<br/>
    name:${user.name}<br/>
</div>

<h1>JSTL</h1>
<h3>choose</h3>
<div>
    <c:set var="score">85</c:set>
    <c:choose>
        <c:when test="${score>=90}">
            你的成绩为优秀！
        </c:when>
        <c:when test="${score>=70&&score<90}">
            您的成绩为良好!
        </c:when>
        <c:when test="${score>60&&score<70}">
            您的成绩为及格
        </c:when>
        <c:otherwise>
            对不起，您没有通过考试！
        </c:otherwise>
    </c:choose>
</div>

<h3>foreach</h3>
<div>
    <%
        User user1 = new User();
        user1.setId(1);
        user1.setName("name1");
        User user2 = new User();
        user2.setId(2);
        user2.setName("name2");
        User user3 = new User();
        user3.setId(3);
        user3.setName("name3");
        request.setAttribute("users", Arrays.asList(user1, user2, user3));
    %>
    <table border='1'cellspacing="0" cellpadding="0">
        <thead>
            <tr>
                <th>编号</th>
                <th>ID</th>
                <th>name</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <td>${status.index}</td>
                <td>${user.id}</td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<h3>fmt函数</h3>
<jsp:useBean id="curTime" class="java.util.Date" scope="page"></jsp:useBean>
<fmt:formatDate value="${curTime }" pattern="yyyy-MM-dd HH:mm:s"/>


<h3>set标签</h3>
<c:set var="stringSet" value="abc"></c:set>
stringSet:${stringSet}<br/>

<!--定义对象不是很方便，无法为具体字段赋值，用jsp:setProperty-->
<c:set var="userSet" value="<%=new User()%>"></c:set>
<jsp:setProperty name="userSet" property="name" value="new user"></jsp:setProperty>
userSet:${userSet.name}<br/>

</body>
</html>
