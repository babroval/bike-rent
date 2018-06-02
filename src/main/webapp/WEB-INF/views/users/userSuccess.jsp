<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="error">

	<h2>${success}</h2>

	<c:choose>
		<c:when test="${loggedUser != null}">
			<a href="<c:url value='/mapView'/>">Go to Map</a>
			<a href="<c:url value='/updateUser' />">Personal info</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/signInUser'/>">Sign-in</a>
		</c:otherwise>
	</c:choose>

</div>