<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="/resources/css/style.css"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                <sec:authorize access="hasRole('ADMIN')"><span style="color: brown"><b>KONTO ADMINISTRATORA </b></span></sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    Hi! <sec:authentication property="principal.username"/>
                </sec:authorize>

                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/admin/users">Użytkownicy</a></li>
                        <li><a href="#">Administratorzy</a></li>
                        <li><a href="#">Zebrane dary</a></li>
                        <li><a href="/admin/institutions">Instytucje</a></li>
                    </sec:authorize>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="/institutions" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/donate" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <section class="help">
                <div class="help--slides active" data-id="1">



                    <ul class="help--slides-items">


                        <section class="login-page">
                            <h2>Edytuj fundację</h2>
                            <form:form modelAttribute="institution" method="post">
                                <form:hidden path="id"/>
                                <div class="form-group">
                                    <form:input path="name" name="name" placeholder="Nazwa fundacji" />
                                    Nazwa fundacji
                                    <form:errors cssClass="error" path="name"  />
                                </div>

                                <div class="form-group">
                                    <form:textarea path="description" rows="2" name="description" placeholder="Opis fundacji" />
                                    Opis Fundacji
                                    <form:errors cssClass="error" path="description"  />
                                </div>



                                <div class="form-group form-group--buttons">
                                    <a href="/admin/institutions" class="btn btn--without-border">Wróć do listy</a>
                                    <button class="btn" type="submit">Zatwierdź zmiany</button>
                                </div>
                            </form:form>
                        </section>

                    </ul>

                </div>

            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

