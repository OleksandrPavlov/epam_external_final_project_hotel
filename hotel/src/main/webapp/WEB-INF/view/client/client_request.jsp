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
				<span style="color: #10361f"><strong>Форма заявки</strong></span>
			</p>
		</h4>
	</div>
	<div class="col-4"></div>
</div>
<c:choose>
	<c:when test="${fail=='req_not_valid'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Заяка не отправлена!</strong> Возможно указаны не верные
			значения!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
</c:choose>
<form action="${pageContext.request.contextPath}/client/home/request" method="POST">
	<div class="card "
		style="background-color: #bf0808; margin-right: 10%; margin-left: 10%">
		<div class="card-header red-text">
			<h5 class="light-gray-font">Форма заявки</h5>
		</div>
		<div class="card-body search">
			<h5 class="card-title light-gray-font">Укажите максимальную цену</h5>
			<div class="row">
				<div class="col-2">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">$ 
						</div>
						<input type="text" class="form-control"
							aria-label="Dollar amount (with dot and two decimal places)"
							name="max_price">
					</div>
				</div>
				<div class="col-2"></div>
				<div class="col-10"></div>
			</div>
			<h5 class="light-gray-font">Количество мест:</h5>
			<div class="row">
				<div class="col-sm">
					<div class="form-group col-md-4"
						style="padding-left: 0px; padding-right: 100px">
						<select id="inputState" class="form-control" name="seat_number">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
				</div>
				<div class="col-sm"></div>
			</div>
			<h5 class="light-gray-font">Класс апартаментов:</h5>
			<div class="row">
				<div class="col-sm">
					<div class="form-group col-md-4" style="padding-left: 0px;">
						<select id="inputState" class="form-control" name="class">
							<option value="1" selected>Econom</option>
							<option value="2">Standard</option>
							<option value="3">Improved</option>
							<option value="4">Luxury</option>
						</select>
					</div>
				</div>
				<div class="col-sm"></div>
			</div>
			<h5 class="light-gray-font" style="margin-top: 5px">Удобства:</h5>
			<div class="row" style="margin-bottom: 20px">
				<div class="col-sm">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox"
							id="inlineCheckbox1" value="2" name="facility"> <label
							class="form-check-label light-gray-font" for="inlineCheckbox1">Кондиционер</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox"
							id="inlineCheckbox2" value="1" name="facility"> <label
							class="form-check-label light-gray-font" for="inlineCheckbox2">wi-fi</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox"
							id="inlineCheckbox2" value="3" name="facility"> <label
							class="form-check-label light-gray-font" for="inlineCheckbox2">Телевизор</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox"
							id="inlineCheckbox2" value="4" name="facility" /> <label
							class="form-check-label light-gray-font" for="inlineCheckbox2">Куллер
							на этаже</label>
					</div>
				</div>
				<div class="col-sm"></div>
			</div>
			<h5 class="light-gray-font">Количество ночей:</h5>
			<div class="row">
				<div class="col-sm">
					<div class="form-group">
						<input type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="night_quantity" /> <small
							id="emailHelp" class="form-text text-muted light-gray-font">Количество
							не может превышать 30</small>
					</div>
				</div>
				<div class="col-sm"></div>
			</div>
			<h5 class="light-gray-font">Дата заселения:</h5>
			<div class="row">
				<div class="col-sm">
					<input type="date" name="settl_date" min="${min_date}"
						max="${max_date}" />
				</div>
				<div class="col-sm"></div>
			</div>

			<h5 class="light-gray-font" style="margin-top: 10px;">Добавить
				комментарий:</h5>
			<div class="row" style="margin-top: 20px">
				<div class="col-sm">
					<div class="form-group">

						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3" name="comment"></textarea>
					</div>
				</div>
				<div class="col-sm"></div>
			</div>
			
			
			
			<div class="row" style="margin-top: 20px">
				<div class="col-sm">
					<button type="submit" class="btn btn-danger "
						style="background-color: #e8c331; color: black">Отправить
						форму</button>
				</div>
				<div class="col-sm"></div>
			</div>
		</div>
	</div>
</form>