<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign up</title>

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
                    <h3 class="panel-title">Please Sign Up</h3>
                </div>
                <div class="panel-body">
                    <@sf.form role="form" action="/sign_up" method="post" modelAttribute="user">
                        <fieldset>
                            <div class="form-group">
                                <@sf.input path="name" cssClass="form-control" placeholder="Name" type="text"/>
                                <@sf.errors path="name" cssClass="help-block"/>
                            </div>
                            <div class="form-group">
                                <@sf.input path="surname" cssClass="form-control" placeholder="Surname" type="text"/>
                                <@sf.errors path="surname" cssClass="help-block"/>
                            </div>
                            <div class="form-group">
                                <@sf.input path="email" cssClass="form-control" placeholder="Email" type="email"/>
                                <@sf.errors path="email" cssClass="help-block"/>
                            </div>
                            <div class="form-group">
                                <@sf.input path="password" cssClass="form-control" placeholder="Password" type="password"/>
                                <@sf.errors path="password" cssClass="help-block"/>
                            </div>
                            <div class="form-group">
                                <@sf.input path="passwordConfirmation" cssClass="form-control" placeholder="Confirm" type="password"/>
                                <@sf.errors path="passwordConfirmation" cssClass="help-block"/>
                            </div>
                            <div class="form-group">
                                <input class="btn btn-block btn-info" type="submit" value="Sign up">
                            </div>
                            <div class="form-group">
                                <a href="/sign_in" class="btn btn-block btn-success">Sign in</a>
                            </div>
                        </fieldset>
                    </@sf.form>
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