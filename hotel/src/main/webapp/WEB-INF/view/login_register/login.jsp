<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${autentificationException==true}">
	<div class="alert alert-warning alert-dismissible fade show ots"
		role="alert">
		<strong>Autentification error!</strong> Wrong password or username
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>


<c:choose>

	<c:when test="${fail=='access_book_fail'}">
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>Для возможности бронирования необходимо войти в
				личный кабинет!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
	<c:when test="${success=='managerRegistrationSuccess'}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Запрос на регистрацию менеджера подан в обработку!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

	</c:when>
	<c:when test="${success=='clientRegistrationSuccess'}">
    		<div class="alert alert-success alert-dismissible fade show"
    			role="alert">
    			         <strong>Регистрация прошла успешно</strong>
    			<button type="button" class="close" data-dismiss="alert"
    				aria-label="Close">
    				<span aria-hidden="true">&times;</span>
    			</button>
    		</div>

    	</c:when>
	<c:when test="${fail=='authentification_fail'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Не удалось ввойти в личный кабинет!</strong>Введены не верные
			данные!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
</c:choose>

<div class="container" style="margin-bottom: 100px;">
	<div id="login-container">
		<div class="card" id="login-form">
			<div class="card-header background-dark-gray" style="color: #e6e6e6">Вход
				в личный кабинет</div>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/home/login" method="POST">
					<h5 class="card-title">Логин</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="login" placeholder="Логин:">
						</div>
					</div>
					<h5 class="card-title">Пароль</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPassword"
								name="password" placeholder="Пароль:">
						</div>
					</div>
					<p>
						<a href="${pageContext.request.contextPath}/register">Зарегестрироваться</a>
					</p>
					<div class="form-check" style="margin-bottom: 10px">
						<input class="form-check-input" type="checkbox" id="gridCheck"
							name="remember"> <label class="form-check-label"
							for="gridCheck"> Запомнить меня </label>
					</div>
					<div class="form-group row" id="login-button">
						<div class="col-sm-10">
							<button type="submit" class="btn btn-danger ">Подтвердить</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>