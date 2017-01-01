<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="showpages" class="row">
    <div class="col-md-4" style="padding-left:20px;line-height:40px; font-size:16px">
        <label style="display:inline;">共${page.count}条 每页${page.pageSize} 条
            页次${page.currentPageNo}/${page.totalPageCount}</label>
        <select
                style="width: 80px;"
                onchange="javascript:topage(this.options[this.selectedIndex].value);">
            <c:forEach var="id" begin="1" end="${page.totalPageCount}">
                <c:choose>
                    <c:when test="${page.currentPageNo != id}">
                        <option value="${id}">第${id}页</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${id}" selected="selected">第${id}页</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

    </div>
    <div class="col-md-8 pull-right">
        <nav>
            <ul class="pagination" style="margin-top:5px;">
                <li class="pbutton"><a href="javascript:topage(1);">首页</a></li>
                <c:if test="${page.currentPageNo>1}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo-1});">上一页</a></li>
                </c:if>

                <c:if test="${page.currentPageNo-4>=1}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo-4});">${page.currentPageNo-4}</a></li>
                </c:if>
                <c:if test="${page.currentPageNo-3>=1}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo-3});">${page.currentPageNo-3}</a></li>
                </c:if>
                <c:if test="${page.currentPageNo-2>=1}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo-2});">${page.currentPageNo-2}</a></li>
                </c:if>
                <c:if test="${page.currentPageNo-1>=1}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo-1});">${page.currentPageNo-1}</a></li>
                </c:if>
                <c:choose>
                    <c:when test="${page.totalPageCount!=page.currentPageNo}">
                        <c:if test="${page.currentPageNo+1<=page.totalPageCount}">
                            <li class="pbutton"><a
                                    href="javascript:topage(${page.currentPageNo+1});">${page.currentPageNo+1}</a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPageNo+2<=page.totalPageCount}">
                            <li class="pbutton"><a
                                    href="javascript:topage(${page.currentPageNo+2});">${page.currentPageNo+2}</a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPageNo+3<=page.totalPageCount}">
                            <li class="pbutton"><a
                                    href="javascript:topage(${page.currentPageNo+3});">${page.currentPageNo+3}</a>
                            </li>
                        </c:if>

                        <c:if test="${page.currentPageNo+4<=page.totalPageCount}">
                            <li class="pbutton"><a
                                    href="javascript:topage(${page.currentPageNo+4});">${page.currentPageNo+4}</a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPageNo+5<=page.totalPageCount}">
                            <li class="pbutton"><a
                                    href="javascript:topage(${page.currentPageNo+5});">${page.currentPageNo+5}</a>
                            </li>
                        </c:if>

                    </c:when>
                </c:choose>
                <c:if
                        test="${page.totalPageCount>1&&page.currentPageNo!=page.totalPageCount}">
                    <li class="pbutton"><a
                            href="javascript:topage(${page.currentPageNo+1});">下一页</a></li>
                </c:if>
                <li class="pbutton"><a
                        href="javascript:topage(${page.totalPageCount});">尾页</a></li>
            </ul>
        </nav>
    </div>
</div>