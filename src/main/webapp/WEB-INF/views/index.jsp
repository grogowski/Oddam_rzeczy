<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Oddam rzeczy</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>
<body id="home">
<nav id="main-nav" class="navbar navbar-expand-sm bg-primary navbar-dark fixed-top">
    <div class="container">
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <i class="fas fa-bars"></i>
        </button>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#home">Strona Główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Zaloguj</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">Załóż Konto</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div>
    <div class="jumbotron mt-4 text-primary text-center">
        <p>
            Masz w domu rzeczy, z którymi nie wiesz co zrobić?
        </p>
        <h3 class="heading">ODDAJ JE POTRZEBUJĄCYM</h3>
        <h3 class="heading">-szybko i w zaufane ręce</h3>
        <a href="/register" class="btn btn-primary">ZAŁÓŻ KONTO</a>
    </div>
</div>
<div>
    <div class="jumbotron my-0 py-0 bg-white text-center">
        <h3 class="heading mb-4">Wystarczą 4 proste kroki</h3>
        <div class="row">
            <div class="col-3">
                <i class="fas fa-3x fa-tshirt"></i>
                <p>
                    Wybierz rzeczy do oddania
                </p>
            </div>
            <div class="col-3">
                <i class="fas fa-3x fa-box-open"></i>
                <p>
                    Spakuj je
                </p>
            </div>
            <div class="col-3">
                <i class="fas fa-3x fa-hand-holding-heart"></i>
                <p>
                    Zdecyduj komu chcesz pomóc
                </p>
            </div>
            <div class="col-3">
                <i class="far fa-3x fa-calendar-check"></i>
                <p>
                    Zamów kuriera
                </p>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="jumbotron my-0 py-4 text-center">
        <h3 class="heading mb-4">Komu pomagamy?</h3>
        <div class="row">
            <div class="col-md-4">
                <div class="p-5 mb-2 border rounded-circle text-center bg-primary text-white">FUNDACJOM</div>
            </div>
            <div class="col-md-4">
                <div class="p-5 mb-2 border rounded-circle text-center bg-primary text-white">ORGANIZACJOM POZARZĄDOWYM</div>
            </div>
            <div class="col-md-4">
                <div class="p-5 mb-2 border rounded-circle text-center bg-primary text-white">LOKALNYM ZBIÓRKOM</div>
            </div>
        </div>
    </div>
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
