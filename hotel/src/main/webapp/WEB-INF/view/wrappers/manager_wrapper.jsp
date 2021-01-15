<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/WebContent/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/WebContent/css/mystyle.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/WebContent/pictures/icon.ico" type="image/x-icon">

<script type="text/javascript">
	var isClicked = false;
var KEY_PICTURE_SRC;
	function touchKey() {
	 KEY_PICTURE_SRC = document.querySelector('#key');
        if (isClicked == false) {
            KEY_PICTURE_SRC.src = '${pageContext.request.contextPath}/WebContent/pictures/key-on.png';
            KEY_PICTURE_SRC.style.opacity = 1.0;
             KEY_PICTURE_SRC.style.width = '65px';
            document.getElementById("key-c").style.opacity = 1.0;
		    isClicked = true;
		} else {
			document.getElementById("key-c").style.opacity = 0;
			KEY_PICTURE_SRC.style.width = "55px";
			KEY_PICTURE_SRC.src = '${pageContext.request.contextPath}/WebContent/pictures/key.png';
			isClicked = false;
		}
	}
</script>
</head>
<body>
<dialog>
    <input type= 'button' name = 'yes'/>
    <input type= 'button' name = 'no'/>
</dialog>
	<img src="${pageContext.request.contextPath}/WebContent/pictures/key.png" id="key"
		onclick="touchKey()" />
	<div id="key-c">
		<div id="key-bar" class="border-radius-10"></div>
		<div id="menu-content">
			<a href="${pageContext.request.contextPath}/home/login" class="menu-ref">Главная</a><br> <a
				href="${pageContext.request.contextPath}/manager/home/addroom" class="menu-ref">Добавить номер</a><br> <a href="${pageContext.request.contextPath}/manager/home/requestList"
				class="menu-ref">Заявки</a><br> <a href="${pageContext.request.contextPath}/logout"
				class="menu-ref">Выйти</a><br>
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
			<div class="col-sm"></div>
			<div class="col-sm"></div>
		</div>

		<div class="row">
			<div class="col-sm">
				<div id="content">
					<jsp:include page="${innerPage}" />
				</div>
			</div>
		</div>
		<div class="row " id="footer">
			<div class="col-sm text-color-laght-gray">This footer here some info about service.</div>
			<div class="col-sm text-color-laght-gray">This footer here some info about service.</div>
			<div class="col-sm text-color-laght-gray">This footer here some info about service.</div>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="${pageContext.request.contextPath}/WebContent/js/jquery-3.3.1.js"></script>
	<script src="${pageContext.request.contextPath}/WebContent/js/popper.js"></script>
	<script src="${pageContext.request.contextPath}/WebContent/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/WebContent/js/custom.js"></script>
</body>
</html>