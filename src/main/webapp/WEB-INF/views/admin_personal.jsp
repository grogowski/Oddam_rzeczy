<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Oddam rzeczy - edycja danych osobowych</title>
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
                    <h5 class="card-title text-center">Edycja danych osobowych</h5>
                    <form class="form-signin" method="post" action="/admin/edit/personal">
                        <div class="form-label-group">
                            <input name="firstName" type="text" id="firstName"id="password" class="form-control" value="${user.firstName}" required>
                            <label for="firstName">Imię</label>
                        </div>
                        <div class="form-label-group">
                            <input name="lastName" type="text" id="lastName" class="form-control" value="${user.lastName}" required>
                            <label for="lastName">Nazwisko</label>
                        </div>
                        <div class="form-label-group">
                            <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
                            <p class="error">${emailMessage}</p>
                            <label for="email">Adres e-mail</label>
                        </div>
                        <input class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" value="Aktualizuj dane"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
