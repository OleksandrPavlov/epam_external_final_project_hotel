<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
    prefix="fn" %>
<div class="card" >
    <div class="card-header gray-medium">
        <h5 style="color: #e8e8e8">Ожидающее пользователи на регистрцию аккаунта менеджера</h5>
    </div>
    <div class="card-body gray-light ">
        <c:choose>
            <c:when test="${fn:length(waiting_users) > 0}">
                <table class="table table-striped textpadp20 ">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Surname</th>
                                <th scope="col">Patronimic</th>
                                <th scope="col">Login</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Status</th>
                                <th scope="col">Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${waiting_users}">
                                <tr>
                                    <td><c:out value="${user.name}"></c:out></td>
                                    <td><c:out value="${user.surname}"></c:out></td>
                                    <td><c:out value="${user.patronimic}"></c:out></td>
                                    <td><c:out value="${user.login}"></c:out></td>
                                    <td><c:out value="${user.mail}"></c:out></td>
                                    <td><c:out value="${user.phone}"></c:out></td>
                                    <td><c:out value="${user.activeStatus}"></c:out></td>
                                    </td>
                                    <td class="custom-container2">

                                        <form action="${pageContext.request.contextPath}/manager/pended/discard" method="POST">
                                        <input type="text" name="userId" value="${user.id}" hidden="true"/>
                                            <button type="submit" class="btn btn-outline-dark">disable</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/manager/pended/approve" method="POST">
                                             <input type="text" name="userId" value="${user.id}" hidden="true"/>
                                            <button type="submit" class="btn btn-outline-warning"> enable </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </c:when>
            <c:otherwise>
                <div class="border-radius-10 extra-light-gray padding-top-bot-200">
                    <h4 style="text-align: center">Нету ожидающих...</h4>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
