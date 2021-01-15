<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<div class="row"
		style="background-color: #339675; padding-top: 13px; margin-bottom: 15px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);">
		<div class="col-4" style="padding-left: 30px;">
			<p>
				<span style="color: #10361f"><strong>Личный кабинет:</strong></span>
				<span style="color: #dedede;">${currentUser.getName()}&nbsp;
					${currentUser.getSurname()}</span>
			</p>
		</div>
		<div class="col-4">
			<h4 class="center">
				<p>
					<span style="color: #10361f"><strong>Брони</strong></span>
				</p>
			</h4>
		</div>


		<div class="col-4">
			<p class="right" style="padding-right: 30px;">
				<span style="color: #10361f"><strong> Предложения:<span
						style="color: #dedede;">${offer_count}</span>
				</strong></span>
			</p>
		</div>
	</div>


	<c:choose>
		<c:when test="${success=='book_success'}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<strong>Комната успешно забронирована!!</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:when test="${success=='req_sended'}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<strong>Заяка отправлена!</strong> Ваша заявка будет обработана
				менеджером!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:when test="${fail=='book_fail'}">

			<div
				class="alert alert-danger alert-dismissible fade show notification-margin "
				role="alert">
				<strong>Не удалось выполнить бронирование!</strong>попробуйте
				повторить
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>

		<c:when test="${success=='pay_success'}">
			<div
				class="alert alert-success alert-dismissible fade show notification-margin "
				role="alert">
				<strong>Бронь успешно оплачена!</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
	</c:choose>
<c:if test="${offer_count>0}">
<div class = "custom-container">
		<div></div>
		<div style = "text-align: center">
		<a href = "${pageContext.request.contextPath}/client/home/offers" ><button type="submit" class="btn btn-primary">
        				Заявки <span class="badge badge-light">${offer_count}</span>
        			</button></a>
        			</div>
        				<div></div>
        			</div>
        			</c:if>

	<div class="row ">
		<div class="col-2"></div>
		<div class="col-8 ">

			<div class="card ">
				<div class="card-header gray-medium">
					<h5 style="color: #e8e8e8">Мои забронированые комнаты</h5>
				</div>
				<div class="card-body gray-light ">
					<c:if test="${current_books==null}">
						<div class="border-radius-10 extra-light-gray padding-top-bot-200">
							<h4 style="text-align: center">У вас пока нет броней</h4>
						</div>
                        <div class= "custom-container">
                        <div style = "text-align: center">
                        						    <a href="${pageContext.request.contextPath}/home"><button type="button" class="btn btn-info">Забронировать!</button></a>
                        						    </div>
                        <div></div>

						    <div style = "text-align: center">
                                                   						    <a href="${pageContext.request.contextPath}/client/home/request">
                                                   						        <button type="button" class="btn btn-info">Создать заявку!</button>
                                                   						    </a>
                                                   						    </div>
						</div>
					</c:if>
					<!-- Start Content -->

					<c:forEach var="room" items="${current_books}">
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
										<span class="text-attribute"><strong>количество
												броней:</strong></span><br />
									</p>
								</div>
								<div class="col-10 left">
									<p style="line-height: 16px">
										${room.getPrice()}<br /> ${room.getSeatNumber()}<br />
										${room.getArea()}<br />${room.getRoomNumber()}<br />
									</p>
								</div>
							</div>
							<div class="row room-window-title room-window  padding-10">
								<div class="col-sm">
									<div class="center">
										<h6>Текущие брони</h6>
									</div>
									<c:forEach var="book" items="${room.getBooks()}">
										<div
											class="row books <c:if test="${book.getPaid()=='y'}">paid-book</c:if><c:if test="${book.getPaid()=='n'}">no-paid-book</c:if>">
											<div class="col-4 ">(${book.getDateRange().getBegin()})
												--> (${book.getDateRange().getEnd()})</div>
											<div class="col-3">
												<c:if test="${book.getPaid()=='n'}">
													<div id="toPaid">
														<form action="${pageContext.request.contextPath}/client/pay" method="POST">
															<input name="begin_date"
																value="${book.getDateRange().getBegin()}" hidden="true" />
															<input name="id" value="${book.getRoomId()}"
																hidden="true" />
															<button type="submit" class="btn btn-danger">
																<strong>Оплатить</strong> <strong>${book.getTotal()}$</strong>
															</button>

														</form>
													</div>
												</c:if>
											</div>
											<c:if test="${book.getPaid()=='n'}">
												<div class="col-5">Срок действия до:
													${book.getReserveDate().toLocalDateTime().toLocalDate().plusDays(2).toString()}</div>
											</c:if>
											<c:if test="${book.getPaid()=='y'}">
												<div class="col-5 right">Оплачено</div>
											</c:if>
										</div>

									</c:forEach>
								</div>

							</div>
						</div>
					</c:forEach>

					<!-- End Content -->
				</div>
			</div>
		</div>


	</div>
</div>