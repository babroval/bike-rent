<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<li><a href="<c:url value='/'/>">Home</a></li>
<li><a href="<c:url value='/news'/>">News</a></li>

<c:choose>
	<c:when test="${loggedUser != null && role != 1}">
		<li><a href="<c:url value='/mapView'/>">Map</a></li>
		<li><a href="<c:url value='/signOutUser'/>">Sign out</a></li>
		<li><a href="<c:url value='/updateUser' />">Personal info</a></li>
	</c:when>

	<c:when test="${role == 1}">
		<li><a href="<c:url value='/mapView'/>">Map</a></li>
		<li><a href="<c:url value='/signOutUser'/>">Sign out</a></li>
		<li><a href="<c:url value='/updateUser' />">Personal info</a></li>
		<li><a href="<c:url value='/listPoints' />">Points</a></li>
		<li><a href="<c:url value='/listUsers' />">Users</a></li>
		<li><a href="<c:url value='/listBikes' />">Bikes</a></li>
		<li><a href="<c:url value='/listOrders' />">Orders</a></li>
	</c:when>

	<c:otherwise>
		<li><a href="<c:url value='/signInUser'/>">Sign-in</a></li>
		<li><a href="<c:url value='/newUser'/>">Register</a></li>
	</c:otherwise>

</c:choose>