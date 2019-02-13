<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Logowanie</h5>
                    <form class="form-signin" method="post" action="/login">
                        <div class="form-label-group">
                            <input type="email" id="inputEmail" name="email" class="form-control" required autofocus>
                            <label for="inputEmail">Adres e-mail</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" name="password" class="form-control" required>
                            <p class="error">${errorText}</p>
                            <label for="inputPassword">Hasło</label>
                        </div>
                        <input class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" value="Zaloguj"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
