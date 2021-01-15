<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:choose>
	<c:when test="${fail=='registration_fail'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Ошибка регистрации!</strong>Возможно введены не корректные
			данные или такой логин и пароль есть в базе!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

	</c:when>



</c:choose>
<div class="container">
	<div id="login-container">
		<div class="card" id="login-form">
			<div class="card-header background-dark-gray" style="color: #e6e6e6">Регистрационная
				форма</div>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/register" method="POST">
					<h5 class="card-title">Имя</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="user_name" placeholder="Имя:">
								<small id="inputPassword" class="form-text text-muted">Только буквы</small>
						</div>
					</div>
					<h5 class="card-title">Фамилия</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="user_surname" placeholder="Фамилия:">
								<small id="inputPassword" class="form-text text-muted">Только буквы</small>
						</div>
					</div>
					<h5 class="card-title">Отчество</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="user_patronimic" placeholder="Отчество:">
								<small id="inputPassword" class="form-text text-muted">Только буквы</small>
						</div>
					</div>

					<h5 class="card-title">Почта</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="user_mail" placeholder="Почта:">
						</div>
					</div>
					<h5 class="card-title">Телефон:</h5>
					<div class="form-group row" style="margin-bottom: 10px;">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword"
								name="user_phone" placeholder="Телефон:">
						</div>
					</div>
					<h5 class="card-title">Логин:</h5>
					<div class="form-group row" style="margin-bottom: 10px;">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="Login"
								name="user_login" placeholder="Логин:">
								<small id="Login" class="form-text text-muted">Минимум 7 символов и минимум одна цифра</small>
						</div>
						
					</div>
					<h5 class="card-title">Пароль</h5>
					<div class="form-group row">
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPassword"
								name="user_password" placeholder="Пароль:">
								<small id="Login" class="form-text text-muted">Минимум 7 символов и минимум одна цифра</small>
						</div>
					</div>
					<div class="col-auto" style="margin-bottom: 10px;">
						<label class="mr-sm-2" for="inlineFormCustomSelect"><h5 class="card-title">Роль</h5></label> <select
							class="custom-select mr-sm-2" id="inlineFormCustomSelect"
							name="role_id">
							<option selected value="2">Клиент</option>
							<option value="1">Менеджер</option>
						</select>
					</div>
					<div class="form-group row" id="login-button">
						<div class="col-sm-10">
							<button type="submit" class="btn btn-danger "
								style="background-color: #c7271c">Регистрация</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>