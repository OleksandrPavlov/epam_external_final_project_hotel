<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row gray-light padding-tb-10px">
	<div class="col-sm " style="text-align: left">
		<h4>Мастерская</h4>
	</div>

	<div class="col-sm center ">
		<h5>Список клиентских заявок</h5>
	</div>

	<div class="col-sm " style="text-align: right">
		<p>
			<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
			${currentUser.getSurname()} ${ currentUser.getName()}
		</p>

	</div>
</div>


<c:choose>
	<c:when test="${success=='offer_success'}">

		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Предложение успешно отправлено!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
	<c:when test="${fail=='offer_fail'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Предложение не отправлено!</strong> Возникла ошибка при
			попытке отправки. Возможно ответ с такой же начальной датой уже
			отправлен пользователю!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>

</c:choose>

<c:if test="${clientRequests==null}">

	<div class="alert alert-success margin-top-bot-10 center" role="alert"
		style="margin-top: 200px; margin-bottom: 200px">
		<h3>Список запросов пуст</h3>
	</div>

</c:if>
<!-- Content -->
<c:forEach var="clientRequest" items="${clientRequests}">

	<div class="row req non-answered">
		<div class="col-2"></div>
		<div class="col-8">
			<div class="alert alert-success margin-top-bot-10" role="alert">
				<div class="row" style="margin-bottom: 0px;">
					<div class="col-sm">
						<h4 class="alert-heading">Заявка от
							${clientRequest.getClientName()}
							${clientRequest.getClientSurname()}</h4>
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
						<p>${clientRequest.getMaxPrice()}$</p>
						<p>${clientRequest.getSeatsNumber()}</p>
						<p>${clientRequest.getRoomClass().getClassName()}</p>


						<c:forEach var="fac" items="${clientRequest.getFacilities()}">
							<c:if test="${fac.getFacilityName().equals('wi-fi')}">
								<img src="${pageContext.request.contextPath}/WebContent/pictures/wi-fi.png" class="facility"
									title="wi-fi" />
							</c:if>
						</c:forEach>

						<c:forEach var="fac" items="${clientRequest.getFacilities()}">
							<c:if test="${fac.getFacilityName().equals('cooller')}">
								<img src="${pageContext.request.contextPath}/WebContent/pictures/cool.png" class="facility"
									title="куллер" />
							</c:if>
						</c:forEach>

						<c:forEach var="fac" items="${clientRequest.getFacilities()}">
							<c:if test="${fac.getFacilityName().equals('condition')}">
								<img src="${pageContext.request.contextPath}/WebContent/pictures/cond.png" class="facility"
									title="кондиционер" />
							</c:if>
						</c:forEach>

						<c:forEach var="fac" items="${clientRequest.getFacilities()}">
							<c:if test="${fac.getFacilityName().equals('tv')}">
								<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png" class="facility"
									title="телевизор" />
							</c:if>
						</c:forEach>


						<p>${clientRequest.getNightNumber()}</p>
						<p>${clientRequest.getSettlementDate()}</p>
						<div class="comment">
							<p>${clientRequest.getComment()}</p>
						</div>
					</div>
				</div>
				<hr>
				<form action="${pageContext.request.contextPath}/manager/home/requestList/processing" method="GET">
					<input name="client_request_id" value="${clientRequest.getId()}" hidden="true"/>
					<button type="submit" class="btn btn-info">Ответить</button>
				</form>
			</div>
		</div>
		<div class="col-2"></div>
	</div>
</c:forEach>
<!-- Contentent -->