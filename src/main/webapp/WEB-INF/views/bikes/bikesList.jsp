<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="information">

	<h2>Bikes table</h2>

	<table id="example" class="display">
		<thead>
			<tr class="table_head">
				<td>VIN Number</td>
				<td>Type</td>
				<td>Image</td>
				<td>Point number</td>
				<td>Available status</td>
				<td>Condition</td>
				<td>Tarif per minute</td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<tfoot>
			<tr class="table_head">
				<td>VIN Number</td>
				<td>Type</td>
				<td>Image</td>
				<td>Point number</td>
				<td>Available status</td>
				<td>Condition</td>
				<td>Tarif per minute</td>
				<td></td>
				<td></td>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${allBikes}" var="bike">
				<tr>
					<td>${bike.vin}</td>
					<td>${bike.price.description}</td>
					<td><img src="resources/img/bikes/${bike.description}"></td>
					<td>${bike.point.numPoint}</td>

					<c:choose>
						<c:when test="${bike.availableStatus==1}">
							<td>available</td>
						</c:when>
						<c:otherwise>
							<td>not available</td>
						</c:otherwise>
					</c:choose>

					<td>${bike.condit}</td>
					<td>${bike.price.tarif}</td>

					<td><a href="<c:url value='/updateBike-${bike.vin}-bike' />">Update</a></td>
					<td><a href="<c:url value='/deleteBike-${bike.vin}-bike' />"
						onclick="return confirm('Delete?')">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="<c:url value='/newBike' />">New Bike</a> <a
			href="<c:url value='/mapView'/>">Back to Map</a>
	</p>

</div>