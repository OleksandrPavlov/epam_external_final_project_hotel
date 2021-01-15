<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row gray-light padding-tb-10px">
	<div class="col-sm " style="text-align: left">
		<h4>Мастерская</h4>
	</div>
	<form action="${pageContext.request.contextPath}/manager/home/requestList" method="GET">
		<div class="col-sm center ">
			<button type="submit" class="btn btn-primary">
				Заявки <span class="badge badge-light">${canreq}</span>
			</button>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/manager/manager-wait-list" method="GET">
    		<div class="col-sm center ">
    			<button type="submit" class="btn btn-primary" style="background-color: #406e85">
    				Ожидающие регистрации <span class="badge badge-light">${penManCount}</span>
    			</button>
    		</div>
    </form>
	<div class="col-sm " style="text-align: right">
		<p>
			<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
			${currentUser.getSurname()} ${ currentUser.getName()}
		</p>

	</div>
</div>


<c:choose>
	<c:when test="${success=='add_room_success'}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Комната успешно добавлена!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
</c:choose>



<div class="row ">
	<div class="col-sm" style="margin-top: 20px; margin-bottom: 20px">

		<!-- Sorting bar start -->
		<form action="${pageContext.request.contextPath}/manager/home" method="GET">
			<div class="row sort-form ">
				<div class="col-8">
					<div
						class="btn-group btn-group-toggle  ${sort=='price_sort' ? 'active' :''} "
						data-toggle="buttons">
						<label class="btn btn-secondary "> <input type="radio"
							name="sort" autocomplete="on" value="price_sort" checked>
							Цена
						</label> <label class="btn btn-secondary "> <input type="radio"
							name="sort" autocomplete="off" value="status_sort">
							Статус
						</label> <label class="btn btn-secondary" value="status_sort"> <input
							type="radio" name="sort" autocomplete="off" value="class_sort">
							Класс
						</label> <label class="btn btn-secondary"> <input type="radio"
							name="sort" autocomplete="off" value="seat_number_sort">
							Места
						</label>
					</div>
				</div>
				<div class="col-4">
					<button type="submit" class="btn btn-outline-dark">Сортировать</button>
				</div>
			</div>
		</form>
	</div>

	<!-- Sorting bar end -->

	<div class="col-sm">
		<form action="${pageContext.request.contextPath}/manager/home/addroom">
			<button type="submit" class="btn btn-outline-success"
				style="margin-top: 20px; margin-bottom: 20p">Добавить
				комнату</button>
		</form>
	</div>

	<div class="col-sm"></div>
</div>


<c:forEach var="room" items="${currentRooms}">
	<div class="row search thin-light-gray-border"
		style="margin-bottom: 10px;">
		<div class="col-md-2">
			<img src="${pageContext.request.contextPath}/WebContent/pictures/${room.getpRef()}"
				class="card-img vertical-align-card-picture borger-radius-5"
				alt="..." style="width: 200px;">
		</div>
		<div class="col-md-8">
			<div class="row">
				<table class="table table-bordered table-dark search">
					<thead>
						<tr>
							<th scope="col">цена $</th>
							<th scope="col">класс</th>
							<th scope="col">удобства</th>
							<th scope="col">кол-во мест</th>
							<th scope="col">площадь</th>
							<th scope="col">картинка</th>
							<th scope="col">статус</th>
							<th scope="col">номер</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<td>${room.getPrice()}</td>
							<td>${room.getRoomClass().getClassName()}</td>
							<td><c:forEach var="fac" items="${room.getFacilities()}">
							${fac.getFacilityName()}
						</c:forEach></td>
							<td>${room.getSeatNumber()}</td>
							<td>${room.getArea()}</td>
							<td>${room.getpRef()}</td>
							<td>${room.getStatus().getStatusName()}</td>
							<td>${room.getRoomNumber()}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row margin-bottom ">
				<div
					class="col-sm border-radius-10 thin-light-gray-border margin-right">
					<h6 class="text-color-light-grey center">Короткое
						описание(Rus)</h6>
					<p class="description-text">${room.getShortNameRus()}</p>
					<h6 class="text-color-light-grey center">Полное описание(Rus)</h6>
					<p class="description-text">${room.getDescription().getRusVersion()}</p>
				</div>
				<div class="col-sm border-radius-10 thin-light-gray-border">
					<h6 class="text-color-light-grey center"">Короткое
						описание(En)</h6>
					<p class="description-text">${room.getShortNameEn()}</p>
					<h6 class="text-color-light-grey center">Полное описание(En)</h6>
					<p class="description-text">${room.getDescription().getEnVersion()}</p>
				</div>
			</div>

			<c:if test="${room.getBooks().size()>0 }">
				<div class="row">
					<div class="col-4">
						<h6 style="text-align: center; color: #f5f5f5">Текущие брони
							на номере</h6>
						<div class="books">
							<table class="table table-dark " style="font-size: 10px;">
								<thead>
									<tr>
										<th scope="col ">Дата заезда</th>
										<th scope="col ">Дата выезда</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${room.getBooks()}">
										<tr
											class="<c:if test="${book.getPaid().equals('y')}">paid</c:if><c:if test="${book.getPaid().equals('n')}">no-paid</c:if>">
											<td>${book.getDateRange().getBegin()}</td>
											<td>${book.getDateRange().getEnd()}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-8">
						<img src="${pageContext.request.contextPath}/WebContent/pictures/reserved.png"
							style="width: 150px; margin-top: 20px; margin-left: 20px;">
					</div>
				</div>
			</c:if>

		</div>
		<div class="col-md-2">

			<c:if
				test="${room.getStatus().getStatusName()!='booked'&&room.getStatus().getStatusName()!='occupied'}">
				<form action="${pageContext.request.contextPath}/manager/home/editroom" method="GET">
					<input type="text" name="id" value="${room.getId() }" hidden="true" />
					<button type="submit"
						class="btn btn-info  vertical-align-btn">
						<strong>редактировать</strong>
					</button>
				</form>
				<form action="${pageContext.request.contextPath}/manager/home/deleteroom" method="POST" id = 'deleteRoomForm'>
					<input type="text" name="id" value="${room.getId()}" hidden="true" />
					<button type="submit"
						class="btn btn-danger  vertical-align-btn">
						<strong>удалить</strong>
					</button>
				</form>
			</c:if>
		</div>
	</div>

</c:forEach>
</div>