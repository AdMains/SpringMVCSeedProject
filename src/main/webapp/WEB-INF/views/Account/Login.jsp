<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <link rel="shortcut icon" href="/assets/images/show.png">

    <title>登录</title>

    <link href="/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/core.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/components.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/menu.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css">

    <script src="/assets/js/modernizr.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/assets/js/html5shiv.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
        function reloadValidateCode(){
            $("#validateCodeImg").attr("src","/Account/validateCode?data=" + new Date() + Math.floor(Math.random()*24));
        }
    </script>

</head>
<body>


<div class="wrapper-page">
    <div class="panel panel-color panel-primary panel-pages">
        <div class="panel-heading bg-img">
            <div class="bg-overlay"></div>
            <h3 class="text-center m-t-10 text-white"> 登录到 <strong>XXXX系统</strong> </h3>
        </div>


        <div class="panel-body">
            <form class="form-horizontal m-t-20" action="/Account/Login" method="post">

                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control input-lg" type="text" required=""  name="UserName" placeholder="用户名">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control input-lg" type="password" required="" name="Password" placeholder="密码">
                    </div>
                </div>

                <div class="row form-group">
                    <div class=" col-xs-6">
                        <div class="">
                            <input class="form-control input-lg" type="text" required="" name="validateCode" placeholder="验证码">
                        </div>
                    </div>

                    <div class=" col-xs-6">
                        <div class="">
                            <div class="">
                                <img id="validateCodeImg" src="/Account/validateCode" />&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <div class="checkbox checkbox-primary">
                            <input id="checkbox-signup" type="checkbox" name="RememberMe">
                            <label for="checkbox-signup">
                                记住我
                            </label>
                        </div>

                    </div>
                </div>

                <div class="form-group text-center m-t-40">
                    <div class="col-xs-12">
                        <button class="btn btn-primary btn-lg w-lg waves-effect waves-light" type="submit">登录</button>
                    </div>
                </div>

                <div class="form-group m-t-30">
                    <div class="col-sm-7">
                        <a href=""><i class="fa fa-lock m-r-5"></i> 忘记密码?</a>
                    </div>
                    <div class="col-sm-5 text-right">
                        <a href="">创建一个账户</a>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>


<script>
    var resizefunc = [];
</script>

<!-- Main  -->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/detect.js"></script>
<script src="/assets/js/fastclick.js"></script>
<script src="/assets/js/jquery.slimscroll.js"></script>
<script src="/assets/js/jquery.blockUI.js"></script>
<script src="/assets/js/waves.js"></script>
<script src="/assets/js/wow.min.js"></script>
<script src="/assets/js/jquery.nicescroll.js"></script>
<script src="/assets/js/jquery.scrollTo.min.js"></script>

<script src="/assets/js/jquery.app.js"></script>

</body>
</html>