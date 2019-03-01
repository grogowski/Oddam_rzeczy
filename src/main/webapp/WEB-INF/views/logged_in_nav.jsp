<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="main-nav" class="navbar navbar-expand-sm bg-primary navbar-dark fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Witaj ${userName}</a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <i class="fas fa-bars"></i>
        </button>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/user/main">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/edit">Edytuj profil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/logout">Wyloguj</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
