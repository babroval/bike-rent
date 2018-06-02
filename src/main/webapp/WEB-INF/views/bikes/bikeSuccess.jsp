<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="error">
	<h2>${success}</h2>

	<a href="<c:url value='/listBikes' />">Points Table</a> <a
		href="<c:url value='/mapView'/>">Go to Map</a>
</div>