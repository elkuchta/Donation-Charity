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
                        <li><a href="#">Użytkownicy</a></li>
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
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <section class="help">
                <p class="help--slides active" data-id="1">
                    <p><h2>Szczegóły Twojej darowizny</h2></p>








                <!-- STEP 6 -->
           <h3>     <div data-step="5">


                    <div class="summary">
                        <div class="form-section">
                            <h4>Oddajesz:</h4>
                            <ul>
                                <li>
                                    <span class="icon icon-bag"></span>
                                    <span class="summary--text"
                                          id="bagsquantityOUT" >Ilość worków: ${details.quantity}</span
                                    >
                                </li>

                                <li>
                                    <span class="icon icon-hand"></span>
                                    <span class="summary--text" id="categoryOUT"  >Kategorie: <c:forEach items="${details.categories}" var="category"> ${category.name}</c:forEach></span>


                                </li>
                                <li>
                                    <span class="icon icon-hand"></span>
                                    <span class="summary--text" id="InstitutionOUT" >Nazwa instytucji: ${details.institution.name}</span></li>
                            </ul>
                        </div>

                        <div class="form-section form-section--columns">
                            <div class="form-section--column">
                                <h4>Adres odbioru:</h4>
                                <ul>
                                    <li id="streetOUT"> ${details.street}</li>
                                    <li id="cityOUT"> ${details.city}</li>
                                    <li id="zipCodeOUT"> ${details.zipCode}</li>
                                    <li id="phoneNumberOUT">${details.phoneNumber}</li>
                                </ul>
                            </div>

                            <div class="form-section--column">
                                <h4>Termin odbioru:</h4>
                                <ul>
                                    <li id="pickUpDateOUT"> ${details.pickUpDate}</li>
                                    <li id="pickUpTimeOut">${details.pickUpTime}</li>
                                    <li id="textout" >Uwagi: ${details.pickUpComment}</li>
                                </ul>
                            </div>

                            <div class="form-section--column">
                                <h4>Status</h4>
                                <ul>

                                    <li id="status" ><c:choose><c:when test="${details.status==false}">Nie odebrana</c:when><c:otherwise>Odebrana</c:otherwise></c:choose></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="form-group form-group--buttons">
                        <a href="/user/donations">   <button type="button" class="btn prev-step">Wstecz</button></a>

                    </div>
           </div></h3>


</section>
                </div>

            </section>
        </div>
    </div>

</header>


<%@ include file="footer.jsp" %>

