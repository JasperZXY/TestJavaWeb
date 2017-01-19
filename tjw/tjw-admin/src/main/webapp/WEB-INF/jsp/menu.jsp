<%@ page import="zxy.common.PrivilegeCode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul id="sidebar-menus" class="sidebar-menu">
    <li class="treeview">
        <a href="#">
            <i class="fa fa-dashboard text-green"></i> <span>Demo</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
        <ul class="treeview-menu">
            <li class=""><a href="${ctxPath}/static/page/index2.html" target="_blank"><i class="fa fa-circle-o"></i> index2.html</a></li>
            <li class=""><a href="${ctxPath}/demo/1"><i class="fa fa-circle-o"></i> demo1</a></li>
            <li class=""><a href="${ctxPath}/demo/2"><i class="fa fa-circle-o"></i> demo2</a></li>
        </ul>
    </li>

    <li class="treeview">
        <a href="#">
            <i class="fa fa-files-o text-green"></i>
            <span>内容管理</span>
            <span class="pull-right-container">
                <small class="label label-primary pull-left">2</small>
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
        <a href="${ctxPath}/user/list"><i class="fa fa-user text-green"></i><span>用户管理</span></a>
    </li>
    <li>
        <a href="#">
            <i class="fa fa-android text-green"></i>
            <span>系统配置</span>
            <span class="pull-right-container">
                <small class="label pull-right bg-green">开发中</small>
            </span>
        </a>
    </li>

    <permisssion:pass code="1">
    <li class="treeview">
        <a href="#">
            <i class="fa fa-gear text-green"></i>
            <span>权限相关</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
        <ul class="treeview-menu">
            <permisssion:pass code="1001">
                <li><a href="${ctxPath}/permission/resource/list/all"><i class="fa fa-circle-o"></i> 资源管理</a></li>
            </permisssion:pass>
            <permisssion:pass code="2001">
                <li><a href="#"><i class="fa fa-circle-o"></i> 角色管理</a></li>
            </permisssion:pass>
        </ul>
    </li>
    </permisssion:pass>
</ul>