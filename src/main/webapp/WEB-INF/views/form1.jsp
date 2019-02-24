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
                <div class="card-body bg-primary text-center">
                    <h3>2</h3>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-primary text-center">
                    <h3>3</h3>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card mt-4 border-dark">
                <div class="card-body bg-primary text-center">
                    <h3>4</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <p class="display-4">Zaznacz co chcesz oddać.</p>
    </div>
</div>
<div class="jumbotron bg-primary text-white py-3">
    <h5>Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy wiedzieć komu najlepiej je przekazać.</h5>
</div>
<div id="form-section" class="container">
    <form action="/user/form1" method="post">
        <div class="form-group">
            <c:forEach var="category" items="${categories}">
                <div class="form-check">
                    <label class="form-check-label mr-2">
                        <input class="form-check-input" type="checkbox" name="categories"
                               value="${category.id}">${category.name}
                    </label>
                </div>
            </c:forEach>
            <input class="btn btn-primary btn-lg float-right float-md-left mt-3 px-5" type="submit" value="Dalej"/>
        </div>
    </form>
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
