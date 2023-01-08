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
                    <p><h2>Twoje zbiórki</h2></p>

<div class="element">
                    <ul class="help--slides-items">

                        <div style="overflow: auto; height:600px; padding-right: 20px">
                        <c:forEach items="${donations}"  var="donation" varStatus="status">
                            <li>
                                <div class="col">

                                    <div class="title">${donation.institution.name}</div>
                                    <div class="subtitle"><b>Ilość worków:</b> ${donation.quantity}</div>
                                    <div class="subtitle"><b>Data odbioru:</b> ${donation.pickUpDate}</div>
                                    <div class="subtitle"><b>Status:</b><c:choose><c:when test="${donation.status==false}">Nie odebrana</c:when><c:otherwise>Odebrana</c:otherwise></c:choose> </div>


                                    <button class="btn" ><a href="/donation/details/${donation.id}"> Pokaż szczegóły</a></button>


                                </div>



                            </li>



                        </c:forEach>
                        </div>
                    </ul>
</div>
                </div>

            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

