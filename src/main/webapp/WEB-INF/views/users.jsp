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
                    <li><a href="/profile">Profil</a></li>
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
            <li><a href="/form" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <section class="help">
                <div class="help--slides active" data-id="1">
                    <p><h2>Lista użytkowników</h2></p>

<div class="element">
                    <ul class="help--slides-items">


                        <c:forEach items="${users}"  var="user" varStatus="status">

                            <li>
                                <div class="col">

                                    <div class="title">${user.username}</div>
                                    <div class="subtitle">${user.email}</div>
                                    <br>
                                    <br>

                                    <a href="/admin/new/${user.id}">  <button class="btn" >Zrób administratorem</button></a>
                                    <a href="/admin/deleteuser/${user.id}"> <button class="btn" > Usuń</button></a>
                                    <a href="/admin/editAdmin/${user.id}"> <button class="btn" > Edytuj dane</button></a>
                                    <c:choose>
                                        <c:when test="${user.enabled==1}">
                                        <a href="/admin/userblock/${user.id}">  <button class="btn" > Zablokuj</button></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/admin/userblock/${user.id}">  <button class="btn" > Odblokuj</button></a>
                                    </c:otherwise>


                                    </c:choose>


                                </div>



                            </li>



                        </c:forEach>

                    </ul>
</div>
                </div>
                <a href="/admin/list"><button class="btn" > Administratorzy</button></a>
            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

