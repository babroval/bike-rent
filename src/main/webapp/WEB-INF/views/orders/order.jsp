<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
a {
	margin-top: 20px;
}
</style>

<div class="information">

	<form:form method="POST" modelAttribute="order">

		<form:input type="hidden" path="id" />

		<h2>Point number "${point.numPoint}"</h2>
		<table id="example" class="display">
			<thead>
				<tr class="table_head">

					<td>Bike</td>
					<td>Condition</td>
					<td>Price per one minute</td>

					<td></td>
				</tr>
			</thead>
			<tfoot>
				<tr class="table_head">

					<td>Bike</td>
					<td>Condition</td>
					<td>Price per one minute</td>

					<td></td>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach items="${bikes}" var="bike">
					<tr>

						<td><img src="resources/img/bikes/${bike.description}"></td>
						<td>${bike.condit}</td>
						<td>${bike.price.tarif}</td>

						<c:choose>
							<c:when test="${order.status == 'using'}">
								<td><input type="button" value="Blocked" disabled></td>
							</c:when>
							<c:otherwise>
								<td><input id="${bike.vin}" type="submit" value="Take bike"
									onclick="Select1()"></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				<c:forEach items="${vacantSlots}" var="vacantSlot">
					<tr>
						<td colspan="3">${vacantSlot}</td>

						<c:choose>
							<c:when test="${order.status == 'using'}">
								<td><input type="submit" value="Park bike"
									onclick="Select2()"></td>
							</c:when>
							<c:otherwise>
								<td><input type="button" value="Blocked" disabled></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form:input type="hidden" path="bike.vin" id="bike" />

		<a href="<c:url value='/mapView'/>">Back to Map</a>

	</form:form>
</div>

<script type="text/javascript">
	function Select1() {
		event = window.event;
		var pointId = document.getElementById("bike");
		pointId.setAttribute("value", event.target.id);
		document.getElementById("send").submit();
	}
</script>
<script type="text/javascript">
	function Select2() {
		event = window.event;
		document.getElementById("send").submit();
	}
</script>