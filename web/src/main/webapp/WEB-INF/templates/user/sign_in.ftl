<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign in</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/landing/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="/resources/landing/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="/resources/landing/dist/css/timeline.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/resources/landing/dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="/resources/landing/bower_components/morrisjs/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/resources/landing/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/login/process" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input id="email" class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input id="pass" class="form-control" placeholder="Password" name="password" type="password" value="">
                                    <#if error??>
                                        <p class="help-block">Bad credentials or you are banned. Try again.</p>
                                    </#if>
                                </div>
                                <#--<div class="checkbox">-->
                                    <#--<label>-->
                                        <#--Remember me-->
                                        <#--<input name="remember-me" type="checkbox">-->
                                    <#--</label>-->
                                <#--</div>-->
                                <div class="form-group">
                                    <input id="sign_in" class="btn btn-block btn-success" type="submit" value="Sign in">
                                </div>
                                <div class="form-group">
                                    <a href="/sign_up" class="btn btn-block btn-info">Sign up</a>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/resources/landing/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/landing/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/landing/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="/resources/landing/bower_components/raphael/raphael-min.js"></script>
    <script src="/resources/landing/bower_components/morrisjs/morris.min.js"></script>
    <script src="/resources/landing/js/morris-data.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="/resources/landing/dist/js/sb-admin-2.js"></script>
</body>

</html>
