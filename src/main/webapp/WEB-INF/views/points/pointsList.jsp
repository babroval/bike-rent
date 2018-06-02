<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="information">

	<h2>Points table</h2>

	<table id="example" class="display">
		<thead>
			<tr class="table_head">
				<td>Point number</td>
				<td>total slots</td>
				<td>free_bikes</td>
				<td>longitude</td>
				<td>latitude</td>
				<td>address mark</td>
				<td>active status</td>
				<td>description</td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<tfoot>
			<tr class="table_head">
				<td>Point number</td>
				<td>total slots</td>
				<td>free_bikes</td>
				<td>longitude</td>
				<td>latitude</td>
				<td>address mark</td>
				<td>active status</td>
				<td>description</td>
				<td></td>
				<td></td>
			</tr>
		<tbody>
			<c:forEach items="${allPoints}" var="point">
				<tr>
					<td>${point.numPoint}</td>
					<td>${point.slots}</td>
					<td>${point.freeBikes}</td>
					<td>${point.longitude}</td>
					<td>${point.latitude}</td>
					<td>${point.addressMark}</td>
					<td>${point.activeStatus}</td>
					<td>${point.description}</td>
					<td><a
						href="<c:url value='/updatePoint-${point.numPoint}-point' />">Update</a></td>
					<td><a
						href="<c:url value='/deletePoint-${point.numPoint}-point' />"
						onclick="return confirm('Delete?')">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="<c:url value='/newPoint' />">New Point</a> <a
			href="<c:url value='/mapView'/>">Back to Map</a>
	</p>

</div>