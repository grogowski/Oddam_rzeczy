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
</head>
<body id="home">
<jsp:include page="logged_in_nav.jsp"/>
<div id="section1" class="jumbotron bg-light my-4 py-5">
    <div class="table-responsive">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>Organizacja</th>
                <th>Liczba worków</th>
                <th>Przedmiot darowizny</th>
                <th>Data przekazania kurierowi</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donation" items="${donations}">
                <tr>
                    <td>${donation.organization.name}</td>
                    <td>${donation.bags}</td>
                    <td><c:forEach var="category" items="${donation.categories}">${category.name}, </c:forEach></td>
                    <td>
                        <c:if test="${donation.wasCollected == true}">${donation.stringCollected}</c:if>
                        <c:if test="${donation.wasCollected == false}">${donation.stringCollected} (oczekuje na przekazanie)</c:if>
                    </td>
                    <td>
                        <c:if test="${donation.wasCollected == false}">
                            <a href="/user/collect/${donation.id}" class="btn btn-primary">Oznacz jako przekazane</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
