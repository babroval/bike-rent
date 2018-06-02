<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="information">

	<h2>Orders table</h2>

	<table id="example" class="display">
		<thead>
			<tr class="table_head">
				<td>Order Num</td>
				<td>User login</td>
				<td>Bike VIN</td>
				<td>Start point Num</td>
				<td>Start time</td>
				<td>Finish point Num</td>
				<td>Finish time</td>
				<td>Total cost</td>
				<td>Status</td>
				<td>Description</td>
			</tr>
		</thead>
		<tfoot>
			<tr class="table_head">
				<td>Order Num</td>
				<td>User login</td>
				<td>Bike VIN</td>
				<td>Start point Num</td>
				<td>Start time</td>
				<td>Finish point Num</td>
				<td>Finish time</td>
				<td>Total cost</td>
				<td>Status</td>
				<td>Description</td>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${allOrders}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.user.login}</td>
					<td>${order.bike.vin}</td>
					<td>${order.startPoint.numPoint}</td>
					<td><fmt:formatDate pattern="dd.MM.yyyy hh:mm:ss"
							value="${order.startTime}" /></td>
					<td>${order.finishPoint.numPoint}</td>
					<td><fmt:formatDate pattern="dd.MM.yyyy hh:mm:ss"
							value="${order.finishTime}" /></td>
					<td>${order.totalCost}</td>
					<td>${order.status}</td>
					<td>${order.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="<c:url value='/mapView'/>">Back to Map</a>
	<p>
</div>