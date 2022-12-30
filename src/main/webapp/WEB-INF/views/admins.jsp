<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <li><a href="/indexLogin" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="/institutions" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/form" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <section class="help">
                <div class="help--slides active" data-id="1">
                    <p><h2>Lista administratorów</h2></p>

                    <div class="element">
                        <ul class="help--slides-items">


                            <c:forEach items="${users}"  var="user" varStatus="status">

                                <li>
                                    <div class="col">

                                        <div class="title">${user.username}</div>
                                        <div class="subtitle">${user.email}</div>
                                        <br>
                                        <br>
                                        <a href="/admin/editAdmin/${user.id}"> <button class="btn" > Edytuj dane</button></a>

                                       <c:if test="${user.id!=current.id}" >

                                        <a href="/admin/deleteAdmin/${user.id}"> <button class="btn" > Usuń</button></a>
                                        <a href="/admin/removerole/${user.id}"> <button class="btn" > Cofnij uprawnienia</button></a>
                                        </c:if>

                                    </div>



                                </li>



                            </c:forEach>

                        </ul>
                    </div>
                </div>
                <a href="/admin/users"> <button class="btn" > Wróć</button></a>
            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

