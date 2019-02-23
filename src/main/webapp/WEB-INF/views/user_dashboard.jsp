<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Oddam rzeczy</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="stylesheet" href="/css/dashboard.css"/>
</head>
<body id="home">
<jsp:include page="logged_in_nav.jsp"/>
<div id="section1" class="jumbotron bg-light mt-4 text-primary text-center">
    <div class="row justify-content-center">
        <p>
            Zacznij pomagać!
        </p>
    </div>
    <div class="row">
        <div class="col-4">
            <div class="card border-0">
                <div class="card-body bg-light">
                    <img src="/images/blank.jpg" alt="" class="rounded-circle w-100">
                    <div class="caption">
                        <p class="display-4">5</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card border-0">
                <div class="card-body bg-light">
                    <img src="/images/blank.jpg" alt="" class="rounded-circle w-100">
                    <div class="caption">
                        <p class="display-4">5</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card border-0">
                <div class="card-body bg-light">
                    <img src="/images/blank.jpg" alt="" class="rounded-circle w-100">
                    <div class="caption">
                        <p class="display-4">5</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-4"><p class="text-dark">oddanych worków</p></div>
        <div class="col-4"><p class="text-dark">wspartych organizacji</p></div>
        <div class="col-4"><p class="text-dark">zorganizowanych zbórek</p></div>
    </div>
    <div class="row justify-content-around">
        <a href="" class="btn btn-primary my-2">ODDAJ RZECZY</a>
        <a href="" class="btn btn-primary my-2">ZORGANIZUJ ZBIÓRKĘ</a>
    </div>

</div>
<div>
    <div class="jumbotron mt-0 mb-5 py-0 bg-white text-center">
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
