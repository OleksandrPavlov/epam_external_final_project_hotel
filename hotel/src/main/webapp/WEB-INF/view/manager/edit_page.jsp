<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row gray-light padding-tb-10px">
			<div class="col-sm " style="text-align: left">
				<h4>Мастерская</h4>
			</div>
				<div class="col-sm center ">
					Редактор комнаты
				</div>
		
			<div class="col-sm " style="text-align: right">
				<p>
					<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
					${currentUser.getSurname()} ${ currentUser.getName()}
				</p>

			</div>
		</div>

	<form method="POST" action="${pageContext.request.contextPath}/manager/home/editroom" id='editRoomForm'>
		<p>Цена</p>
		<input type="text" name="price" value="${room.getPrice()}" />
		<p>кол-во мест</p>
		<input type="text" name="seat_number" value="${room.getSeatNumber()}" />
		<p>картинка</p>
		<input type="text" name="pic" value="${room.getpRef()}" />
		<p>площадь</p>
		<input type="text" name="area" value="${room.getArea()}" />


		<p>Номер комнаты</p>
		<input type="text" name="room_number" value="${room.getRoomNumber()}" />
		<p>класс</p>
		<input type="radio" name="class" value="1"
			<c:if test="${room.getRoomClass().getClassId()==1}">checked</c:if> />econom
		<input type="radio" name="class" value="2"
			<c:if test="${room.getRoomClass().getClassId()==2}">checked</c:if> />standard
		<input type="radio" name="class" value="3"
			<c:if test="${room.getRoomClass().getClassId()==3}">checked</c:if> />improved
		<input type="radio" name="class" value="4"
			<c:if test="${room.getRoomClass().getClassId()==4}">checked</c:if> />luxury
		<p>удобства</p>
		<input type="checkbox" name="facility" value="1"
			<c:forEach var="fac" items="${room.getFacilities()}">
			<c:if test="${fac.getFacilityId()==1}">checked</c:if>
		</c:forEach> />wi-fi
		<input type="checkbox" name="facility" value="2"
			<c:forEach var="fac" items="${room.getFacilities()}">
			<c:if test="${fac.getFacilityId()==2}">checked</c:if>
		</c:forEach> />кондиционер
		<input type="checkbox" name="facility" value="3"
			<c:forEach var="fac" items="${room.getFacilities()}">
			<c:if test="${fac.getFacilityId()==3}">checked</c:if>
		</c:forEach> />телевизор
		<input type="checkbox" name="facility" value="4"
			<c:forEach var="fac" items="${room.getFacilities()}">
			<c:if test="${fac.getFacilityId()==4}">checked</c:if>
		</c:forEach> />куллер
		<p>краткое описание(rus)</p>
		<textarea rows="10" cols="45" name="sh_desc_rus">${room.getShortNameRus()}</textarea>
		<p>краткое описание(en)</p>
		<textarea rows="10" cols="45" name="sh_desc_en">${room.getShortNameEn()}</textarea>
		<p>полное описание(rus)</p>
		<textarea rows="
			10" cols="45" name="desc_rus">${room.getDescription().getRusVersion()}</textarea>
		<p>полное описание(en)</p>
		<input type="text" value="${id}" hidden="true" name="id" />
		<textarea rows="10" cols="45" name="desc_en">${room.getDescription().getEnVersion()}</textarea>
		<p>Доступность</p>
		<input type="radio" name="availability" value="1" checked/> Да <input
			type="radio" name="availability" value="0" /> Нет <input
			type="submit" value="подтвердить" />


	</form>
