<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row gray-light padding-tb-10px">
	<div class="col-sm " style="text-align: left">
		<h4>Мастерская</h4>
	</div>

	<div class="col-sm center ">
		<h4>Добавление комнаты</h4>
	</div>

	<div class="col-sm " style="text-align: right">
		<p>
			<span class="font-dark-blue" style=""><strong>Менеджер:</strong></span>
			${currentUser.getSurname()} ${ currentUser.getName()}
		</p>

	</div>
</div>
<c:choose>
	<c:when test="${fail=='add_room_fail'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Комната не добавлена!</strong>Возник системный сбой
			попробуйте еще раз
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
	<c:when test="${fail=='add_room_validation_fail'}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Комната не добавлена!</strong>Введены не верные значения!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:when>
</c:choose>

<div class="row" style="margin-top: 20px;">
	<div class="col-3"></div>
	<div class="col-8">
		<form method="POST" action="${pageContext.request.contextPath}/manager/home/addroom">
			<div class="card "
				style="background-color: #bf0808; margin-right: 10%; margin-left: 10%">
				<div class="card-header red-text">
					<h5 class="light-gray-font center">Форма добавления комнаты</h5>
				</div>
				<div class="card-body search">
					<div class="row">
						<div class="col-2"></div>
						<div class="col-8 ">
							<h5 class="card-title light-gray-font">Цена (за ночь)</h5>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text">$ 
								</div>
								<input type="text" class="form-control"
									aria-label="Dollar amount (with dot and two decimal places)"
									name="price">
							</div>

							<h5 class="card-title light-gray-font">Номер комнаты</h5>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text">$ 
								</div>
								<input type="text" class="form-control"
									aria-label="Dollar amount (with dot and two decimal places)"
									name="room_number">
							</div>

							<h5 class="light-gray-font">Количество мест:</h5>
							<div class="form-group col-md-12"
								style="padding-left: 0px; padding-right: 100px">
								<select id="inputState" class="form-control" name="seat_number">
									<option value="1" selected>1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
							</div>


							<h5 class="card-title light-gray-font">Картинка:</h5>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text">
								</div>
								<input type="text" class="form-control"
									aria-label="Dollar amount (with dot and two decimal places)"
									name="pic">
							</div>

							<h5 class="card-title light-gray-font">Площадь:</h5>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text">
								</div>
								<input type="text" class="form-control"
									aria-label="Dollar amount (with dot and two decimal places)"
									name="area">
							</div>

							<h5 class="light-gray-font">Класс апартаментов:</h5>

							<div class="form-group col-md-4" style="padding-left: 0px;">
								<select id="inputState" class="form-control" name="class">
									<option value="1" selected>Econom</option>
									<option value="2">Standard</option>
									<option value="3">Improved</option>
									<option value="4">Luxury</option>
								</select>
							</div>

							<h5 class="light-gray-font" style="margin-top: 5px">Удобства:</h5>
							<div class="row" style="margin-bottom: 20px">

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

							<h5 class="light-gray-font" style="margin-top: 10px;">Краткое
								описание (RUS):</h5>

							<div class="form-group">
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3" name="sh_desc_rus"></textarea>
							</div>


							<h5 class="light-gray-font" style="margin-top: 10px;">Краткое
								описание (EN):</h5>

							<div class="form-group">
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3" name="sh_desc_en"></textarea>
							</div>


							<h5 class="light-gray-font" style="margin-top: 10px;">Полное
								описание (RUS):</h5>

							<div class="form-group">
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="5" name="desc_rus"></textarea>
							</div>



							<h5 class="light-gray-font" style="margin-top: 10px;">Полное
								описание (EN):</h5>
							<div class="form-group">
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="5" name="desc_en"></textarea>
							</div>
							<h5 class="light-gray-font">Доступность:</h5>
							<div class="form-group col-md-4" style="padding-left: 0px;">
								<select id="inputState" class="form-control" name="availability">
									<option value="1" selected>Да</option>
									<option value="0">Нет</option>
								</select>
							</div>
							<div class="row" style="margin-top: 20px">
								<div class="col-sm">
									<button type="submit" class="btn btn-danger "
										style="background-color: #e8c331; color: black">Добавить
										комнату</button>
								</div>
								<div class="col-sm"></div>
							</div>
						</div>
						<div class="col-2"></div>
					</div>
				</div>
			</div>
		</form>

	</div>
</div>

<div class="col-3"></div>
</div>
