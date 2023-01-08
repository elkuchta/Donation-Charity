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
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                <sec:authorize access="hasRole('ADMIN')"><span style="color: brown"><b>KONTO ADMINISTRATORA </b></span></sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    Hi! <sec:authentication property="principal.username"/>
                </sec:authorize>

                <ul class="dropdown">
                    <li><a href="/profile">Profil</a></li>
                    <li><a href="/user/donations">Moje zbiórki</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/admin/users">Użytkownicy</a></li>
                        <li><a href="/admin/list">Administratorzy</a></li>
                        <li><a href="/admin/donations">Zebrane dary</a></li>
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

                 <div   style="border-bottom: 4px solid black;">
                    <section class="login-page">
                  <form:form method="post" modelAttribute="user">

                      <form:hidden path="id"/>
                      <form:hidden path="password" />
                      <form:hidden path="roles"/>
                      <form:hidden path="enabled"/>

                    <div class="form-group">

                      <form:input itemValue="id" itemLabel="name" path="username" items="${user}"/>
                        Nazwa użytkownika
                    </div>

                    <div class="form-group">

                      <form:input itemValue="id" itemLabel="email" path="email" items="${user}"/>
                        Email
                    </div>



                    <div class="form-group form-group--buttons">
                      <input class="btn" type="submit" value="Zapisz zmiany">

                        <a href="/admin/changePassword/${id}"> <input class="btn"  value="Zmień hasło" ></a>
                    </div></div><br>
                      <div class="form-group form-group--buttons">
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="/admin/users"> <input class="btn"  value="Użytkownicy" ></a>
                            <a href="/admin/institutions"> <input class="btn"  value="Fundacje" ></a>
                        </sec:authorize>
                      </div>

                  </form:form>
                    </section>
                    </div>


            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

