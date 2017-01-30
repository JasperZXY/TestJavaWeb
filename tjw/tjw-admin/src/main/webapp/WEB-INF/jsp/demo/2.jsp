<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../share.jsp" %>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        ${window_title}
        <small>toast</small>
            <button class="btn btn-default" onclick="history.back();">
                <span class="fa fa-mail-reply">回退</span>
            </button>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${index_url}"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">Demo</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <button class="btn btn-default" onclick="toast1()">来一个</button>
                </div>
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

<script>
    function toast1() {
        $.toast({
            text: "成功", // Text that is to be shown in the toast
            heading: '', // Optional heading to be shown on the toast
            icon: 'info', // Type of toast icon
            showHideTransition: 'fade', // fade, slide or plain
            allowToastClose: true, // Boolean value true or false
            hideAfter: 2000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
            stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
            position: 'mid-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
            textAlign: 'left',  // Text alignment i.e. left, right or center
            loader: false,  // Whether to show loader or not. True by default
            loaderBg: '#000000',  // Background color of the toast loader
        });
    }
</script>