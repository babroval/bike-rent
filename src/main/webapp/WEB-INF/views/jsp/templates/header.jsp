<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
	<div class="logo">
		<a href="<c:url value='/'/>"> <img
			src="resources/img/dif/logo.png" alt="logo"></a>
		<h1>City Bike</h1>
	</div>
	<div class="strip_menu">
		<div class="nav clearfix">
			<ul class="main">
				<jsp:include page="menu.jsp" />
			</ul>
		</div>
	</div>
</div>