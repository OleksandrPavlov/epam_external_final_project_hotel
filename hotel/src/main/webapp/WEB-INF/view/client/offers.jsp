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
				<span style="color: #10361f"><strong>Предложения</strong></span>
			</p>
		</h4>
	</div>
	<div class="col-4"></div>
</div>

<c:choose>
	<c:when test="${success=='rej_off_suc'}">

		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Предложение отвергнуто!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
</c:choose>


<div class="row" style="margin-bottom: 200px;">
	<div class="col-2"></div>
	<div class="col-8">
		<div class="card ">
			<div class="card-header gray-medium">
				<h5 style="color: #e8e8e8" class="center">Текущие предложения</h5>
			</div>
			<div class="card-body gray-light ">
				<c:if test="${offers==null}">
					<h3 style="margin-top: 150px; margin-bottom: 150px" class="center">У вас нет ни одного предложения</h3>
					<div class="center">
						<a href="${pageContext.request.contextPath}/client/home/request"><button type="button"
								class="btn btn-info center">Заполнить заявку</button></a>
					</div>
				</c:if>


				<!-- Start Content -->
				<c:forEach var="offer" items="${offers}">
					<div class="shadow margin-bottom-10">
						<div class="row room-window-title room-window ">
							<div class="col-3 padding-0 left">
								<img
									src="${pageContext.request.contextPath}/WebContent/pictures/${offer.getRoom().getpRef() }"
									class="room-window-pic" />
							</div>
							<div class="col-9">
								<div class="row">
									<div class="col-3">
										<p>
											<span class="room-short-desc">Класс: </span><span>${offer.getRoom().getRoomClass().getClassName() }</span>
										</p>
									</div>
									<div class="col-9">
										<p>
											<span class="room-short-desc">Короткое описание: </span><span>${offer.getRoom().getShortNameRus() }
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-4 left padding-0">

										<c:forEach var="fac"
											items="${offer.getRoom().getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('wi-fi')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/wi-fi.png"
													class="facility" title="wi-fi" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac"
											items="${offer.getRoom().getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('cooller')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/cool.png"
													class="facility" title="куллер" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac"
											items="${offer.getRoom().getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('condition')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/cond.png"
													class="facility" title="кондиционер" />
											</c:if>
										</c:forEach>

										<c:forEach var="fac"
											items="${offer.getRoom().getFacilities()}">
											<c:if test="${fac.getFacilityName().equals('tv')}">
												<img src="${pageContext.request.contextPath}/WebContent/pictures/tv.png"
													class="facility" title="телевизор" />
											</c:if>
										</c:forEach>


									</div>
									<div class="col-8 left">
										<p class="dotted-light-gray-border padding-10"
											style="font-size: 12px">${offer.getRoom().getDescription().getRusVersion()}</p>
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
							<div class="col-7 left">
								<p style="line-height: 16px">

									${offer.getRoom().getPrice()}<br />
									${offer.getRoom().getSeatNumber()}<br />
									${offer.getRoom().getArea()}<br />${offer.getRoom().getRoomNumber()}
								</p>
							</div>
							<div class="col-3">
								<h5>Итого: ${offer.getTotal()}$</h5>
							</div>
						</div>
						<div class="row room-window-title room-window  padding-10">
							<div class="col-sm center">
								<h4>${offer.getDateRange().getBegin()}</h4>
							</div>
							<div class="col-sm">>>>>>>>>>>>>>>>>></div>
							<div class="col-sm center">
								<h4>${offer.getDateRange().getEnd()}</h4>
							</div>
							<div class="col-sm">
								<form action="${pageContext.request.contextPath}/client/book" method="POST">
									<input type="text" name="begin_date"
										value="${offer.getDateRange().getBegin()}" hidden="true">
									<input type="text" name="end_date"
										value="${offer.getDateRange().getEnd()}" hidden="true">
									<input type="text" name="id" value="${offer.getRoom().getId()}"
										hidden="true"> <input name="from" value="offer"
										hidden="true" /> <input name="total_price"
										value="${offer.getTotal() }" hidden="true" />
									<button type="submit" class="btn btn-outline-success">забронировать</button>
								</form>
							</div>
							<div class="col-sm">
								<form action="${pageContext.request.contextPath}/offer/reject" method="POST">
									<input type="text" name="begin_date"
										value="${offer.getDateRange().getBegin()}" hidden="true">
									<input type="text" name="end_date"
										value="${offer.getDateRange().getEnd()}" hidden="true">
									<input type="text" name="id" value="${offer.getRoom().getId()}"
										hidden="true"> <input name="from" value="offer"
										hidden="true" />
									<button type="submit" class="btn btn-outline-danger">отказаться</button>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- End Content -->
			</div>
		</div>
	</div>
	<div class="col-2"></div>
</div>