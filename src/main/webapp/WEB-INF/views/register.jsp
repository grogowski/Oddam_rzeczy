<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Rejestracja</title>
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
                    <h5 class="card-title text-center">Rejestracja użytkownika</h5>
                    <form:form class="form-signin" modelAttribute="user" method="post" action="/register">
                        <div class="form-label-group">
                            <form:input path="email" type="email" id="email" class="form-control"/>
                            <form:errors cssClass="error" path="email"/>
                            <p class="error">${emailMessage}</p>
                            <label for="email">Adres e-mail</label>
                        </div>
                        <div class="form-label-group">
                            <form:input path="firstName" type="text" id="firstName" class="form-control"/>
                            <form:errors cssClass="error" path="firstName"/>
                            <label for="firstName">Imię</label>
                        </div>
                        <div class="form-label-group">
                            <form:input path="lastName" type="text" id="lastName" class="form-control"/>
                            <form:errors cssClass="error" path="lastName"/>
                            <label for="lastName">Nazwisko</label>
                        </div>
                        <div class="form-label-group">
                            <form:input path="password" type="password" id="password" class="form-control"/>
                            <form:errors cssClass="error" path="password"/>
                            <label for="password">Hasło</label>
                        </div>
                        <div class="form-label-group">
                            <input type="password" id="repeatPassword" name="repeatPassword" class="form-control" required>
                            <p class="error">${passwordsMessage}</p>
                            <label for="repeatPassword">Powtórz hasło</label>
                        </div>
                        <form:hidden path="admin" value="false"/>
                        <input class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" value="Zarejestruj"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
