<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row gray-light padding-tb-10px">
	<div class="col-sm " style="text-align: left">
		<h4>Мастерская</h4>
	</div>

	<div class="col-sm center ">
		<h5>Обработка заявки</h5>
	</div>

	<div class="col-sm " style="text-align: right">
		<p>
			<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
			${currentUser.getSurname()} ${ currentUser.getName()}
		</p>
	</div>
</div>

<form action="${pageContext.request.contextPath}/manager/result" method="GET">
	<div class="row search  text-color-white border-radius-25">
		<div class="col-sm padding-top-bottom-20 ">
			<h4>Поиск номеров</h4>
		</div>

		<div class="col-sm ">
			<div>
				<div>Заезд</div>
				<input type="date" name="begin_date" min="${min_date}"
					max="${max_date}">
			</div>
		</div>
		<div class="col-sm ">
			<div>
				<div>Выезд</div>
				<input type="date" name="end_date" min="${min_date}"
					max="${max_date}">
			</div>
		</div>

		<div class="col-sm padding-top-bottom-20 ">
			<button type="submit "
				style="padding-left: 40px; padding-right: 40px;"
				class="btn btn-danger ">
				<h6>
					<strong>Поиск</strong>
				</h6>
			</button>
		</div>
	</div>
</form>



<div class="row req non-answered">
	<div class="col-2"></div>
	<div class="col-8">
		<div class="alert alert-success margin-top-bot-10" role="alert">
			<div class="row" style="margin-bottom: 0px;">
				<div class="col-sm">
					<h4 class="alert-heading">Заявка от
						${currentClientRequest.getClientName()}
						${currentClientRequest.getClientSurname()}</h4>
				</div>


			</div>


			<div class="row" style="margin-left: 20px">
				<div class="col-4">
					<p>Максимальная сумма:</p>
					<p>Количество мест:</p>
					<p>Класс апартаментов:</p>
					<p>Удобства:</p>
					<p>Количество ночей:</p>
					<p>Дата заселения:</p>
					<p>Комментарий:</p>
				</div>

				<div class="col-8">
					<p>${currentClientRequest.getMaxPrice()}$</p>
					<p>${currentClientRequest.getSeatsNumber()}</p>
					<p>${currentClientRequest.getRoomClass().getClassName()}</p>


					<c:forEach var="fac"
						items="${currentClientRequest.getFacilities()}">
						<c:if test="${fac.getFacilityName().equals('wi-fi')}">
							<img src="${pageContext.request.contextPath}/WebContent/pictures/wi-fi.png"
								class="facility" title="wi-fi" />
						</c:if>
					</c:forEach>

					<c:forEach var="fac"
						items="${currentClientRequest.getFacilities()}">
						<c:if test="${fac.getFacilityName().equals('cooller')}">
							<img src="${pageContext.request.contextPath}/WebContent/pictures/cool.png" class="facility"
								title="куллер" />
						</c:if>
					</c:forEach>

					<c:forEach var="fac"
						items="${currentClientRequest.getFacilities()}">
						<c:if test="${fac.getFacilityName().equals('condition')}">
							<img src="${pageContext.request.contextPath}/WebContent/pictures/cond.png" class="facility"
								title="кондиционер" />
						</c:if>
					</c:forEach>

					<c:forEach var="fac"
						items="${currentClientRequest.getFacilities()}">
						<c:if test="${fac.getFacilityName().equals('tv')}">
							<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png" class="facility"
								title="телевизор" />
						</c:if>
					</c:forEach>


					<p>${currentClientRequest.getNightNumber()}</p>
					<p>${currentClientRequest.getSettlementDate()}</p>
					<div class="comment">
						<p>${currentClientRequest.getComment()}</p>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="col-2"></div>
</div>


