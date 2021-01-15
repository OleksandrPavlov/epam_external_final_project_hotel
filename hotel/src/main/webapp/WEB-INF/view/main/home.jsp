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
						max="${max_date}">
				</div>
			</div>
			<div class="col-sm ">
				<div>
					<div>Выезд</div>
					<input type="date" name="end_date" min="${min_date}"
						max="${max_date}">
				</div>
			</div>
			<div class="col-sm padding-top-bottom-20 ">
				<button type="submit "
					style="padding-left: 40px; padding-right: 40px;"
					class="btn btn-danger ">
					<h6>
						<strong>Поиск</strong>
					</h6>
				</button>
			</div>home
		</div>
	</form>
	<img src="${pageContext.request.contextPath}/WebContent/pictures/hotel.jpg" style="width: 1500px;">
</div>