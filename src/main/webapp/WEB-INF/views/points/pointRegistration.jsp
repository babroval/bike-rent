<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="information">

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="point">

		<form:input type="hidden" path="id" />

		<p>
			<span>Point Number* </span>
			<form:input path="numPoint" autofocus="true" />
			<form:errors path="numPoint" cssClass="err" />
		</p>
		<p>
			<span>Total Slots* </span>
			<form:input path="slots" />
			<form:errors path="slots" cssClass="err" />
		</p>

		<form:input type="hidden" path="freeBikes" value="0" />

		<p>
			<span>Longitude* </span>
			<form:input path="longitude" />
			<form:errors path="longitude" cssClass="err" />
		</p>
		<p>
			<span>Latitude* </span>
			<form:input path="latitude" />
			<form:errors path="latitude" cssClass="err" />
		</p>
		<p>
			<span>Address Mark* </span>
			<form:input path="addressMark" />
			<form:errors path="addressMark" cssClass="err" />
		</p>

		<form:input type="hidden" path="activeStatus" id="activeStatus" />

		<p>
			<span id="active" class="active"></span>active <input type="checkbox"
				id="activeCheck" onclick="active()" />
		</p>
		<p>
			<span>Description </span>
			<form:input path="description" />
		</p>

		<p>
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="Register" />
				</c:otherwise>
			</c:choose>

			<a href="<c:url value='/listPoints' />">Cancel</a>
		</p>

	</form:form>
</div>