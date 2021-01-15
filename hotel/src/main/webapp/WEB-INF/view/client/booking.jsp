<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row"
	style="background-color: #339675; padding-top: 13px; margin-bottom: 15px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);">
	<div class="col-4" style="padding-left: 30px;">
		<p>
			<span style="color: #10361f"><strong>Личный кабинет:</strong></span>
			<span style="color: #dedede;">${currentUser.getName()}
				${currentUser.getSurname()}</span>
		</p>
	</div>
	<div class="col-4">
		<h4 class="center">
			<p>
				<span style="color: #10361f"><strong>Форма
						бронирования</strong></span>
			</p>
		</h4>
	</div>
	<div class="col-4"></div>
</div>


<div class="row">
	<div class="col-2"></div>
	<div class="col-8">
		<div class="card ">
			<div class="card-header gray-medium">
				<h5 style="color: #e8e8e8">Форма бронирования номера №
					${currentRoom.getRoomNumber() }</h5>
			</div>
			<div class="card-body gray-light ">

				<!-- Start Content -->
				<div class="shadow margin-bottom-10">
					<div class="row room-window-title room-window ">
						<div class="col-3 padding-0 left">
							<img src="${pageContext.request.contextPath}/WebContent/pictures/${currentRoom.getpRef()}"
								class="room-window-pic" />
						</div>
						<div class="col-9">
							<div class="row">
								<div class="col-3">
									<p>
										<span class="room-short-desc">Класс: </span><span>${currentRoom.getRoomClass().getClassName() }</span>
									</p>
								</div>
								<div class="col-9">
									<p>
										<span class="room-short-desc">Короткое описание: </span><span>${currentRoom.getShortNameRus() }
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-4 left padding-0">

									<c:forEach var="fac" items="${currentRoom.getFacilities()}">
										<c:if test="${fac.getFacilityName().equals('wi-fi')}">
											<img src="${pageContext.request.contextPath}/WebContent/pictures/wi-fi.png"
												class="facility" title="wi-fi" />
										</c:if>
									</c:forEach>

									<c:forEach var="fac" items="${currentRoom.getFacilities()}">
										<c:if test="${fac.getFacilityName().equals('cooller')}">
											<img src="${pageContext.request.contextPath}/WebContent/pictures/cool.png"
												class="facility" title="куллер" />
										</c:if>
									</c:forEach>

									<c:forEach var="fac" items="${currentRoom.getFacilities()}">
										<c:if test="${fac.getFacilityName().equals('condition')}">
											<img src="${pageContext.request.contextPath}/WebContent/pictures/cond.png"
												class="facility" title="кондиционер" />
										</c:if>
									</c:forEach>

									<c:forEach var="fac" items="${currentRoom.getFacilities()}">
										<c:if test="${fac.getFacilityName().equals('tv')}">
											<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png" class="facility"
												title="телевизор" />
										</c:if>
									</c:forEach>


								</div>
								<div class="col-8 left">
									<p class="dotted-light-gray-border padding-10"
										style="font-size: 12px">${currentRoom.getDescription().getRusVersion()}</p>
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
								${currentRoom.getPrice()}<br /> ${currentRoom.getSeatNumber()}<br />
								${currentRoom.getArea()}<br />${currentRoom.getRoomNumber()}
							</p>
						</div>
					</div>
					<div class="row room-window-title room-window  padding-10"
						style="background-color: #e8ffea">
						<div class="col-sm">Дата заселения: ${begin_date}</div>
						<div class="col-sm">Дата выселения: ${end_date }</div>
						<div class="col-sm">Итого: ${total_price }</div>
					</div>
					<div class="row" style="margin-top: 5px; padding-bottom: 5px;">
						<div class="col-sm"></div>
						<div class="col-sm center">
							<form action="${pageContext.request.contextPath}/client/book" method="POST">
								<input type="text" name="begin_date" value="${begin_date}"
									hidden="true"> <input type="text" name="end_date"
									value="${end_date}" hidden="true"> <input type="text"
									name="id" value="${currentRoom.getId()}" hidden="true">
								<input name="total_price" value="${total_price}" hidden="true"/>
								<button type="submit" class="btn btn-outline-success">забронировать</button>
							</form>
						</div>
						<div class="col-sm"></div>
					</div>
				</div>
				<!-- End Content -->
			</div>
		</div>
	</div>
	<div class="col-2"></div>
</div>