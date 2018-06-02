<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="login">

	<h2>Authorize</h2>

	<form:form method="POST" modelAttribute="user">

		<p>
			<label>Login</label>
			<form:input path="login" value="admin" autofocus="true" />
			<form:errors path="login" cssClass="err" />
		</p>

		<form:input type="hidden" path="email" value="_" />

		<p>
			<label>Password</label>
			<form:password path="password" value="admin" />
			<form:errors path="password" cssClass="err" />
		</p>
		<form:input type="hidden" path="retypePassword" value="_" />
		<form:input type="hidden" path="firstName" value="_" />
		<form:input type="hidden" path="lastName" value="_" />
		<form:input type="hidden" path="city" value="_" />
		<form:input type="hidden" path="country" value="_" />

		<p>
			<input type="submit" value="Sign-in">
		</p>
	</form:form>

</div>