<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="information">

	<h2>Users table</h2>

	<table id="example" class="display">
		<thead>
			<tr class="table_head">
				<td>Login</td>
				<td>Email</td>
				<td>Password</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Tel.</td>
				<td>City</td>
				<td>Country</td>
				<td>Gender</td>
				<td>Birth</td>
				<td>Role</td>
				<td></td>
			</tr>
		</thead>
		<tfoot>
			<tr class="table_head">
				<td>Login</td>
				<td>Email</td>
				<td>Password</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Tel.</td>
				<td>City</td>
				<td>Country</td>
				<td>Gender</td>
				<td>Birth</td>
				<td>Role</td>
				<td></td>
		</tfoot>
		<tbody>
			<c:forEach items="${allUsers}" var="user">
				<tr>
					<td>${user.login}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.tel}</td>
					<td>${user.city}</td>
					<td>${user.country}</td>
					<td>${user.gender}</td>
					<td><fmt:formatDate pattern="dd.MM.yyyy" value="${user.birth}" /></td>
					<td>${user.role}</td>
					<td><a href="<c:url value='/deleteUser-${user.login}-user' />"
						onclick="return confirm('Delete?')">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="<c:url value='/mapView'/>">Back to Map</a>
	<p>
</div>