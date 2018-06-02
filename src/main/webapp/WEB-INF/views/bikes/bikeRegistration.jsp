<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="information">

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="bike">

		<form:input type="hidden" path="id" />

		<p>
			<span>Bike Number* </span>
			<form:input path="vin" autofocus="true" />
			<form:errors path="vin" cssClass="err" />
		</p>

		<p>
			<span class="active2">Image* </span>
			<form:select path="description">
				<option value="">--- Select ---</option>
				<c:forEach items="${bikesImages}" var="img">
					<option value="${img.value}"
						${img.value == bike.description ? 'selected':''}>${img.value}</option>
				</c:forEach>
			</form:select>
			<form:errors path="description" cssClass="err" />
		</p>
		<p>
			<c:choose>
				<c:when test="${edit}">
					<form:hidden path="point.id" />
				</c:when>
				<c:otherwise>
					<span class="select_insert">Point Number* </span>
					<form:select path="point.id">
						<option value="NONE">--- Select ---</option>
						<c:forEach items="${allPoints}" var="point">
							<option value="${point.id}"
								${point.id == bike.point.id ? 'selected':''}>${point.numPoint}</option>
						</c:forEach>
					</form:select>
					<form:errors path="point.id" cssClass="err" />
				</c:otherwise>
			</c:choose>

		</p>

		<form:input type="hidden" path="availableStatus" id="activeStatus" />

		<p>
			<span id="active" class="active"></span>available <input
				type="checkbox" id="activeCheck" onclick="active()" />
		</p>

		<p>
			<span>Condition </span>
			<form:input path="condit" />
			<form:errors path="condit" cssClass="err" />
		</p>

		<p>
			<span class="active2">Price* </span>
			<form:select path="price.id">
				<option value="NONE">--- Select ---</option>
				<c:forEach items="${allPrices}" var="price">
					<option value="${price.id}"
						${price.id == bike.price.id ? 'selected':''}>${price.tarif}</option>
				</c:forEach>
			</form:select>
			<form:errors path="price.id" cssClass="err" />
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

			<a href="<c:url value='/listBikes' />">Cancel</a>
		</p>

	</form:form>
</div>