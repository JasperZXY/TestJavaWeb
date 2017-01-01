<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul id="sidebar-menus" class="sidebar-menu">
    <li class="treeview">
        <a href="#">
            <i class="fa fa-dashboard"></i> <span>Demo</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
        <ul class="treeview-menu">
            <li class=""><a href="${ctxPath}/static/page/index2.html" target="_blank"><i class="fa fa-circle-o"></i> index2.html</a></li>
            <li class=""><a href="${ctxPath}/admin/demo/1"><i class="fa fa-circle-o"></i> demo1</a></li>
            <li class=""><a href="${ctxPath}/admin/demo/2"><i class="fa fa-circle-o"></i> demo2</a></li>
        </ul>
    </li>
    <li class=""><a href="${ctxPath}/admin/demo/sub"><i class="fa fa-circle-o"></i> 单独子项</a></li>
    <li class="treeview">
        <a href="#">
            <i class="fa fa-files-o"></i>
            <span>内容管理</span>
            <span class="pull-right-container">
                <span class="label label-primary pull-left">2</span>
                <span class="pull-right-container">
                    <small class="label pull-right bg-red">开发中</small>
                </span>
            </span>
        </a>
        <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> 帖子</a></li>
            <li><a href="#"><i class="fa fa-circle-o"></i> 消息</a></li>
        </ul>
    </li>
    <li>
        <a href="${ctxPath}/user/list"><i class="fa fa-circle-o text-green"></i><span>用户管理</span></a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-circle-o text-red"></i>
            <span>系统配置</span>
            <span class="pull-right-container">
                <small class="label pull-right bg-green">开发中</small>
            </span>
        </a>
    </li>
</ul>