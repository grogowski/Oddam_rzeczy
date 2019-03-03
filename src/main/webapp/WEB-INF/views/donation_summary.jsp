<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Oddam rzeczy</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="stylesheet" href="/css/form.css"/>
</head>
<body id="home">
<jsp:include page="logged_in_nav.jsp"/>
<div id="section1" class="container ml-1 text-center">
    <div class="row mt-5 mb-3">
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-success text-center">
                    <h3>1</h3>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-success text-center">
                    <h3>2</h3>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-success text-center">
                    <h3>3</h3>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-success text-center">
                    <h3>4</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <p class="display-4">Podsumowanie Twojej darowizny</p>
    </div>
</div>
<div class="jumbotron pt-3">
    <h5>Oddajesz</h5>
    <p>${donated}</p>
    <c:forEach var="category" items="${donatedCategories}">
        <p>- ${category}</p>
    </c:forEach>
    <h5>Odbiorca Twojej darowizny</h5>
    <p>${organization.name}</p>
    <h5>Adres odbioru</h5>
    <p>${address}</p>
    <h5>Termin odbioru</h5>
    <p>${datetime}</p>
    <p>Uwagi dla kuriera: ${remarks}</p>
    <a href="/user/form4" class="btn btn-secondary btn-lg float-left mt-3 px-5">Cofnij</a>
    <a href="/user/donate" class="btn btn-primary btn-lg float-right mt-3 px-5">Wyślij</a>
</div>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>
</html>