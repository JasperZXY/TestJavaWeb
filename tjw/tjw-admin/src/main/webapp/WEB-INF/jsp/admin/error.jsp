<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<section class="content">
    <div class="error-page">
        <h2 class="headline text-red"></h2>

        <div class="error-content">
            <h3><i class="fa fa-warning text-red"></i> 发生错误了。</h3>

            <p>
                ${msg}
                <br/><a href="${index_url}">返回首页</a>
            </p>

        </div>
    </div>
</section>