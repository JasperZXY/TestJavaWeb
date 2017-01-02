<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="showpages" class="row" style="padding-top: 5px">

    <div class="col-md-10">
        <span>共${page.totalPageCount}页/${page.count}条</span>
        <span>每页<input style="width:30px;" class="paging-input" id="pageSize" value="${page.pageSize}" />条</span>
        <select class="paging-input" onchange="javascript:topage(this.options[this.selectedIndex].value);">
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

    <div class="col-md-10">
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

<style>
.paging-input {
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
</style>