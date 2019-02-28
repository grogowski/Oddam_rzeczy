<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div id="form-section" class="container my-5 pt-5">
    <form:form modelAttribute="organization" method="post" action="/admin/new_organization">
        <div class="form-group">
            <div class="form-group">
                <label for="name">Pełna nazwa</label>
                <form:input path="name" type="text" id="name" class="form-control"
                            placeholder="np. Fundacja Dzieciom 'Zdążyć z pomocą' w Warszawie"/>
                <form:errors cssClass="text-danger" path="name"/>
            </div>
            <div class="form-group">
                <label for="name">Adres</label>
                <form:input path="address" type="text" id="address" class="form-control"
                            placeholder="np. ul. Pańska 98 lok. 201 00-837 Warszawa"/>
                <form:errors cssClass="text-danger" path="address"/>
            </div>
            <div class="form-group">
                <label for="location">Lokalizacja (województwo)</label>
                <form:select path="location" id="location" class="form-control">
                    <form:option value="" label="--- Wybierz województwo ---"/>
                    <form:options items="${locations}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors cssClass="text-danger" path="location"/>
            </div>
            <div class="form-group">
                <label for="target">Cel pomocy</label>
                <form:select path="target" id="target" class="form-control">
                    <form:option value="" label="--- Wybierz komu pomaga organizacja ---"/>
                    <form:options items="${targets}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors cssClass="text-danger" path="target"/>
            </div>
            <div class="form-group">
                <form:checkbox path="active" label="aktywna"/>
            </div>
            <input class="btn btn-primary btn-block" type="submit" value="Dodaj organizację"/>
        </div>
    </form:form>
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
