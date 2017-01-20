<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="share.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${window_title}</title>
    <link rel="shortcut icon" href="${ctxPath}/static/img/favicon.ico" >

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
    <!-- 自定义JS -->
    <script src="${ctxPath}/static/js/common.js?version=4"></script>
    <script>
        var ctxPath = '${ctx}';
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">

        <!-- Logo -->
        <a href="${index_url}" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">管理</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg">${window_title}</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${ctxPath}/static/AdminLTE/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${loginUserName}</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctxPath}/auth/logout"><span class="ion-android-exit">退出</span></a>
                    </li>
                </ul>
            </div>

        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${ctxPath}/static/AdminLTE/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${loginUserName}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- search form -->
            <%@include file="menu.jsp" %>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- 引入其他页面，参数“template_page_name”在Constants.JspConfig常量中进行配置的 -->
        <jsp:include page="${template_page_name}.jsp" />
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2014-2016 <a href="${index_url}">Z.X.Y</a></strong> All rights
        reserved.
    </footer>

    <!-- Control Sidebar -->

    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>

</div>
</body>
</html>
