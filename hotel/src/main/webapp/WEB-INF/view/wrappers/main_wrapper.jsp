<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/WebContent/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/WebContent/css/mystyle.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/WebContent/pictures/icon.ico" type="image/x-icon">
<script type="text/javascript">
	var isClicked = false;
	function touchMenu() {
		if (isClicked == false) {
			document.getElementById("menu-c").style.opacity = 1.0;
			document.getElementById("menu").style.width = "60px";
			isClicked = true;
		} else {
			document.getElementById("menu-c").style.opacity = 0;
			document.getElementById("menu").style.width = "55px";
			isClicked = false;
		}
	}
</script>
</head>
<body>
	<img src="${pageContext.request.contextPath}/WebContent/pictures/menu.png" id="menu"
		onclick="touchMenu()"/>
	<div id="menu-c">
		<div id="menu-bar" class="border-radius-10"></div>
		<div id="menu-content">
			<a href="${pageContext.request.contextPath}/home/login" class="menu-ref">Личный кабинет</a><br> <a
				href="${pageContext.request.contextPath}/home" class="menu-ref">Главная</a><br>

			<c:if test="${currentUser.getRole().equals('client')}">
				<a href="${pageContext.request.contextPath}/client/home/offers" class="menu-ref">Мои предложения</a>
				<br />

				<a href="${pageContext.request.contextPath}/client/home/request" class="menu-ref">Новая заявка</a>
				<br />

				<a href="${pageContext.request.contextPath}/logout" class="menu-ref">Выйти</a>
				<br />
			</c:if>


		</div>
	</div>

	<div class="container-fluid">
		<div class="row header center">
			<div class="col-sm " style="padding-top: 10px">
				<p>
					<span style="color: #d10f0f">АДРЕС:</span> <br /> г.Харьков Ул.
					Харьковская дом 34/Б
				</p>
			</div>
			<div class="col-sm " style="padding-top: 10px">
				<img src="${pageContext.request.contextPath}/WebContent/pictures/logo.png">
			</div>
			<div class="col-sm" style="padding-top: 10px">
				<p>
					<span style="color: #d10f0f">Телефон:</span> <br />
					+380-99-2365-546
				</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm">
				<div id="content">
					<jsp:include page="${innerPage}"/>
				</div>
			</div>
		</div>
		<div class="row " id="footer">
			<div class="col-sm text-color-laght-gray">This footer here some
				info about service.</div>
			<div class="col-sm text-color-laght-gray">
				
			</div>
			<div class="col-sm text-color-laght-gray"></div>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="${pageContext.request.contextPath}/WebContent/js/jquery-3.3.1.js"></script>
	<script src="${pageContext.request.contextPath}/WebContent/js/popper.js"></script>
	<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/WebContent/js/bootstrap.js"></script>
</body>
</html>
