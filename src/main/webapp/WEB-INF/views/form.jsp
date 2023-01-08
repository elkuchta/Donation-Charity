<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



  <!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <title>Document</title>
  <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
      <h1>
        Oddaj rzeczy, których już nie chcesz<br />
        <span class="uppercase">potrzebującym</span>
      </h1>

      <div class="slogan--steps">
        <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
        <ul class="slogan--steps-boxes">
          <li>
            <div><em>1</em><span>Wybierz rzeczy</span></div>
          </li>
          <li>
            <div><em>2</em><span>Spakuj je w worki</span></div>
          </li>
          <li>
            <div><em>3</em><span>Wybierz fundację</span></div>
          </li>
          <li>
            <div><em>4</em><span>Zamów kuriera</span></div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</header>
<section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>
<form:form modelAttribute="donation" method="post"  >
  <form:hidden path="user" value="${user.id}" />





          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>




            <c:forEach items="${category}" var="cat">
              <div class="form-group form-group--checkbox">
                <label>
                  <input id="${cat.name}"
                          type="checkbox"
                          name="categories"
                          value="${cat.id}"
                         class="Checkbox"
                  />
                  <span class="checkbox"></span>
                  <span  class ="description"
                  >${cat.name}</span
                  >
                </label>
              </div>
            </c:forEach>


            <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step" id="nextcategory">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
          <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label >
                Liczba 60l worków:
                <form:input path="quantity" id="quantity" min="1" step="1"   />
              </label>

            </div>

            <div class="form-group form-group--buttons">

              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step"id="dalejquantity">Dalej</button>
            </div>
          </div>



          <!-- STEP 3 -->
          <div data-step="3">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>
            <c:forEach items="${institution}" var="inst">
            <div class="form-group form-group--checkbox">
              <label>
                <input type="radio" name="institution"  value="${inst.id}" id="${inst.name}" class="radio" />
                <span class="checkbox radio"></span>
                <span class="description">
                  <div class="title">${inst.name}</div>
                  <div class="subtitle">
                    ${inst.description}
                  </div>
                </span>
              </label>
            </div>
            </c:forEach>



            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step" id="nextInstitution">Dalej</button>
            </div>
          </div>

          <!-- STEP 4 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                  <label > Ulica<form:input path="street" id="street"/></label>
                </div> <span ></span>

                <div class="form-group form-group--inline">
                  <label> Miasto   <form:input path="city" id="city"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy  <form:input path="zipCode" id="zipCode"/>
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu <form:input path="phoneNumber" id="phoneNumber"/>
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data  <form:input type="date" path="pickUpDate" id="pickUpDate"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina <form:input type="time" path="pickUpTime" id="pickUpTime"/> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                     <form:textarea rows="5" path="pickUpComment" id="pickUpComment"  />
                  </label>
                 <span  ></span>

                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step" id="summary">Dalej</button>
            </div>
          </div>

          <!-- STEP 6 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text"
                        id="bagsquantityOUT" ></span
                    >
                  </li>

<li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text" id="categoryOUT"  ></span>


                  </li>
                  <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text" id="InstitutionOUT" ></span></li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li id="streetOUT">Prosta 51</li>
                    <li id="cityOUT">Warszawa</li>
                    <li id="zipCodeOUT">99-098</li>
                    <li id="phoneNumberOUT">123 456 789</li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li id="pickUpDateOUT">13/12/2018</li>
                    <li id="pickUpTimeOut">15:40</li>
                    <li id="textout" ></li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>
          </div>
</form:form>



      </div>
    </section>

<%@ include file="footer.jsp" %>