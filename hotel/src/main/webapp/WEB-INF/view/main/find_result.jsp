<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${book_forbid!=null}">
	<div class="alert alert-warning alert-dismissible fade show"
		role="alert">
		<strong>Ошибка бронирования!</strong> Для бронирования необходимо
		авторизироваться как клиент!
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<c:if test="${validation_exception!=null}">
	<div class="alert alert-warning alert-dismissible fade show"
		role="alert">
		<strong>Не корректный ввод</strong> ${validation_exception}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<div class="container-fluid center ">
	<form action="${pageContext.request.contextPath}/home/find" method="GET">
		<div class="row search  text-color-white border-radius-25">
			<div class="col-sm padding-top-bottom-20 ">
				<h4>Бронирование номеров</h4>
			</div>

			<div class="col-sm ">
				<div>
					<div>Заезд</div>
					<input type="date" name="begin_date" min="${min_date}"
						max="${max_date}" value="${begin_date }">
				</div>
			</div>
			<div class="col-sm ">
				<div>
					<div>Выезд</div>
					<input type="date" name="end_date" min="${min_date}"
						max="${max_date}" value="${end_date }">
				</div>
			</div>

			<div class="col-sm padding-top-bottom-20 ">
				<button type="submit" class="btn btn-danger ">
					<h6>Найти</h6>
				</button>
			</div>

		</div>
	</form>
	<div class="row search grey-text-color background-white">
		<div class="col-sm padding-top-bottom-20 ">
			<h2>Доступные номера</h2>
		</div>
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
											${language}
											<p>
												<span class="room-short-desc">Короткое описание: </span>
												${room.getShortNameRus()}
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
													<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png"
														class="facility" title="телевизор" />
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
									<form action="${pageContext.request.contextPath}/client/book" method="GET">
										<input type="text" name="begin_date" value="${begin_date}"
											hidden="true"> <input type="text" name="end_date"
											value="${end_date}" hidden="true"> <input type="text"
											name="id" value="${room.getId()}" hidden="true">
										<button type="submit" class="btn btn-outline-success">Забронировать</button>

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
	<div class="col-2"></div>
</div>