<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row gray-light padding-tb-10px">
	<div class="col-sm " style="text-align: left">
		<h4>Мастерская</h4>
	</div>

	<div class="col-sm center ">
		<h5>Результаты поиска</h5>
	</div>

	<div class="col-sm " style="text-align: right">
		<p>
			<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
			${currentUser.getSurname()} ${ currentUser.getName()}
		</p>
	</div>
</div>



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


<div class="row ">
	<div class="col-2"></div>
	<div class="col-8 ">

		<div class="card ">
			<div class="card-header gray-medium">
				<h5 style="color: #e8e8e8">Список номеров по диапазону
					(${begin_date} -> ${end_date})</h5>
			</div>
			<div class="card-body gray-light ">
				<c:if test="${currentRooms==null}">
					<div class="border-radius-10 extra-light-gray padding-top-bot-200">
						<h4>По вашему запросу нет комнат</h4>
					</div>
				</c:if>
				<!-- Start Content -->
				<c:forEach var="room" items="${currentRooms}">
					<div class="shadow margin-bottom-10">
						<div class="row room-window-title room-window ">
							<div class="col-3 padding-0 left">
								<img src="${pageContext.request.contextPath}/WebContent/pictures/${room.getpRef() }"
									class="room-window-pic" />
							</div>
							<div class="col-9">
								<div class="row">
									<div class="col-3">
										<p>
											<span class="room-short-desc">Класс: </span><span>${room.getRoomClass().getClassName() }</span>
										</p>
									</div>
									<div class="col-9">
										<p>
											<span class="room-short-desc">Короткое описание: </span><span>${room.getShortNameRus() }
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-4 left padding-0">

										<c:forEach var="fac" items="${room.getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('wi-fi')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/wi-fi.png"
													class="facility" title="wi-fi" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac" items="${room.getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('cooller')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/cool.png"
													class="facility" title="куллер" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac" items="${room.getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('condition')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/cond.png"
													class="facility" title="кондиционер" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac" items="${room.getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('tv')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png" class="facility"
													title="телевизор" />
											</c:if>
										</c:forEach>


									</div>
									<div class="col-8 left">
										<p class="dotted-light-gray-border padding-10"
											style="font-size: 12px">${room.getDescription().getRusVersion()}</p>
									</div>
								</div>
							</div>

						</div>
						<div class="row room-window-title room-window extra-light-gray">
							<div class="col-2 left">
								<p style="line-height: 16px">
									<span class="text-attribute"><strong>цена (за
											ночь):</strong></span><br /> <span class="text-attribute"><strong>мест:</strong></span><br />
									<span class="text-attribute"><strong>площадь:</strong></span><br />
									<span class="text-attribute"><strong>номер:</strong></span><br />
								</p>
							</div>
							<div class="col-10 left">
								<p style="line-height: 16px">
									${room.getPrice()}<br /> ${room.getSeatNumber()}<br />
									${room.getArea()}<br />${room.getRoomNumber()}
								</p>
							</div>
						</div>
						<div class="row room-window-title room-window  padding-10">
							<div class="col-sm"></div>
							<div class="col-sm"></div>
							<div class="col-sm">
								<form action="${pageContext.request.contextPath}/manager/offer" method="POST">
									<input name="begin_date" value="${begin_date}" hidden="true"/><input
										name="end_date" value="${end_date}" hidden="true" /> <input
										name="id" value="${ room.getId()}" hidden="true" /> <input
										type="text" name="client_id"
										value="${currentClientRequest.getClientId()}" hidden="true"><input
										name="client_request_id"
										value="${currentClientRequest.getId()}" hidden="true" />
									<button type="submit" class="btn btn-outline-success">Предложить</button>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- End Content -->
			</div>
		</div>
	</div>


</div>


