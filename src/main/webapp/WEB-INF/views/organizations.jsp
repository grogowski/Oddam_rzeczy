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
<jsp:include page="admin_nav.jsp"/>
<div id="section1" class="jumbotron bg-light my-4 py-5">
    <div class="table-responsive">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>Pełna nazwa</th>
                <th>Adres</th>
                <th>Województwo</th>
                <th>Cel pomocy</th>
                <th>Obecnie aktywna</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="organization" items="${organizations}">
                <tr>
                    <td>${organization.name}</td>
                    <td>${organization.address}</td>
                    <td>${organization.location.name}</td>
                    <td>${organization.target.name}</td>
                    <td>
                        <c:if test="${organization.active == true}">Tak</c:if>
                        <c:if test="${organization.active == false}">Nie</c:if>
                    </td>
                    <td>
                        <a href="/admin/deactivate_organization/${organization.id}" class="${organization.active == true ? 'btn btn-primary m-1' : 'd-none'}">Dezaktywuj</a>
                        <a href="/admin/activate_organization/${organization.id}" class="${organization.active == false ? 'btn btn-primary m-1' : 'd-none'}">Aktywuj</a>
                        <a href="/admin/edit_organization/${organization.id}" class="btn btn-primary m-1">Edytuj</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="/admin/new_organization" class="btn btn-primary btn-block m-2">Dodaj organizację</a>
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
